package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader;

import java.util.List;
import java.util.Objects;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.EventAuditRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.IvansMessageAttachmentRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.IvansMessageRepository;


@Service
public class IvansMessageItemReader implements ItemReader<IvansMessage>{
	

	@Autowired
	ApplicationConfig applicationConfig;
	
	    @Autowired
	    IvansMessageRepository ivansMessageRepository;
	    
	    @Autowired
	    IvansMessageAttachmentRepository ivansMessageAttachmentRepository;
	    
	    private ItemReader<IvansMessage> delegate;
		
		@Override
		public IvansMessage read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if (delegate == null) {
	            delegate = new IteratorItemReader<>(readTable());
	        }
	        return  delegate.read();
		}
		
		@Autowired
	    EventAuditRepository eventAuditRepository;
	 	
		/*
	 	 * Read table will have list of item as the records are retrived based on the last execution job time
	 	 * So we are collecting all the records which are created after the last execution time
	 	 */
	 	public List<IvansMessage> readTable() throws IvansBatchItemException{
	 		 
	 		 List<IvansMessage> ivansMessages = null;
	         List<IvansMessageAttachment> ivansMessageAttachments = null;
	         try {
	         if (Objects.isNull(applicationConfig.getLastExecutedJobTimeStamp())) {
	         	ivansMessages = ivansMessageRepository.findAll();
	         
	         } else {
	         	ivansMessages = ivansMessageRepository.findAllByTimeStamp(applicationConfig.getLastExecutedJobTimeStamp());
	         }
	         for (IvansMessage ivansMessage : ivansMessages) {
	          	ivansMessageAttachments = ivansMessageAttachmentRepository.findByIvansMessageKey(ivansMessage.getIvansMessageKey());
	          	ivansMessage.setIvansMessageAttaments(ivansMessageAttachments);
	         }
	         }catch(Exception e){
	 			
	 			 throw new IvansBatchItemException("Error in IvansMessageItemReader",e);
	 		 }
	         return ivansMessages;
	 	}
 	
 	

}
