package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader;

import java.util.List;
import java.util.Objects;

import javax.batch.runtime.StepExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.builder.StepBuilder;
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
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class EventAuditItemReader implements ItemReader<EventAudit> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ApplicationConfig applicationConfig;
	
	
	
	private ItemReader<EventAudit> delegate;// why use delegate 
	@SuppressWarnings("unchecked")
	@Override
	public EventAudit read() throws  UnexpectedInputException, NonTransientResourceException, Exception {
		
		if (delegate == null) {
            delegate = new IteratorItemReader<>(readTable());
        }
		
        return  delegate.read();
	}
	
	@Autowired
    EventAuditRepository eventAuditRepository;
 	
 	public List<EventAudit> readTable() throws IvansBatchItemException{
 		 List<EventAudit> eventAudits = null;
 		 try {
 			 
 			if (Objects.isNull(applicationConfig.getLastExecutedJobTimeStamp())) {
                eventAudits = eventAuditRepository.findAll();
            } else {
                eventAudits = eventAuditRepository.findAllByTimeStamp(applicationConfig.getLastExecutedJobTimeStamp());
                throw new Exception();
            }
 		 }catch(Exception e){ 			
 			 throw new IvansBatchItemException("Error in EventAuditItemReader",e);
 		 }
 		 
 		 return eventAudits;
 	}
	

}
