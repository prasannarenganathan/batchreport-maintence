package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class IvansMessageItemReader implements ItemReader<IvansMessage>{



	private final Logger log = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	ApplicationConfig applicationConfig;
	
	    @Autowired
	    IvansMessageRepository ivansMessageRepository;
	    
	    @Autowired
	    IvansMessageAttachmentRepository ivansMessageAttachmentRepository;
	    
	    private ItemReader<IvansMessage> delegate;
		@SuppressWarnings("unchecked")
		@Override
		public IvansMessage read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if (delegate == null) {
	            delegate = new IteratorItemReader<>(readTable());
	        }
	        return  delegate.read();
		}
		
		@Autowired
	    EventAuditRepository eventAuditRepository;
	 	
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
