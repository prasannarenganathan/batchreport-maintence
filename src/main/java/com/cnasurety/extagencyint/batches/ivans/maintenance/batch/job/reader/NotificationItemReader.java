package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationAgencyExtensionRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.PackageRepository;




public class NotificationItemReader extends BaseItemReader implements ItemReader<Notification>{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    NotificationRepository notificationRepository;
	
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
	            delegate = new IteratorItemReader<>(readTable(getLastExecutedJobTimeStamp()));
	        }
	        return  delegate.read();
		}
		
	 	public List<Notification> readTable(Timestamp lastExecutedTimeStamp){
	 		 
	 		 List<String[]> data = new ArrayList<String[]>();
	 		List<Notification> notifications = null;

	        
	 		if (Objects.isNull(lastExecutedTimeStamp)) {
	        	notifications = notificationRepository.findAll();
	        } else {
	        	notifications = notificationRepository.findAllByTimeStamp(lastExecutedTimeStamp);
	        }
	         	
	        
	        
	        logger.info("Number of Records Exported: {}", data.size());
	 		
	 		return notifications;
	 	}
	
 
}
