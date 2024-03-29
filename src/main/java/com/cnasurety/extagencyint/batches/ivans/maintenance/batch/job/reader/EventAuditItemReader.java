package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader;

import java.util.List;
import java.util.Objects;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.EventAuditRepository;


@Service
public class EventAuditItemReader implements ItemReader<EventAudit> {
	
	@Autowired
	ApplicationConfig applicationConfig;
	
	
	
	private ItemReader<EventAudit> delegate; 

	@Override
	public EventAudit read() throws  UnexpectedInputException, NonTransientResourceException, Exception {
		
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
 	public List<EventAudit> readTable() throws IvansBatchItemException{
 		 List<EventAudit> eventAudits = null;
 		 try {
 			 
 			if (Objects.isNull(applicationConfig.getLastExecutedJobTimeStamp())) {
                eventAudits = eventAuditRepository.findAll();
            } else {
                eventAudits = eventAuditRepository.findAllByTimeStamp(applicationConfig.getLastExecutedJobTimeStamp());
               
            }
 		 }catch(Exception e){ 	
 			 throw new IvansBatchItemException("Error in EventAuditItemReader",e);
 		 }
 		 return eventAudits;
 	}
	

}
