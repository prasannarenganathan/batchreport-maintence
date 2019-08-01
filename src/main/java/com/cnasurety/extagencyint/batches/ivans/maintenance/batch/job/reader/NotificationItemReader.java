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
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationAgencyExtensionRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.PackageRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;



@Service
public class NotificationItemReader implements ItemReader<Notification>{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
    NotificationRepository notificationRepository;
	
	@Autowired
	ApplicationConfig applicationConfig;
	
	 @Autowired
	    NotificationAgencyExtensionRepository notificationAgencyExtensionRepository;
	    
	    @Autowired
	    DocumentRepository documentRepository;

	    @Autowired
	    PackageRepository packageRepository;
 	
	    private ItemReader<Notification> delegate;
	    
		@SuppressWarnings("unchecked")
		@Override
		public Notification read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
			if (delegate == null) {
	            delegate = new IteratorItemReader<>(readTable());
	        }
	        return  delegate.read();
		}
		
	 	public List<Notification> readTable() throws IvansBatchItemException{
	 		List<Notification> notifications = null;

	        try {
	 		if (Objects.isNull(applicationConfig.getLastExecutedJobTimeStamp())) {
	        	notifications = notificationRepository.findAll();
	        } else {
	        	notifications = notificationRepository.findAllByTimeStamp(applicationConfig.getLastExecutedJobTimeStamp());
	        }
	        }catch(Exception e){
				
				 throw new IvansBatchItemException("Error in NotificationItemReader",e);
			 }
	        
	 		return notifications;
	 	}
	
 
}
