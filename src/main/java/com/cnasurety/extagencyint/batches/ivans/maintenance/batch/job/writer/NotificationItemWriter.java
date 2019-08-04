package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class NotificationItemWriter extends FlatFileItemWriter<Notification> {

	@Autowired
	GCloudWriter cloudWriter;
	
    ApplicationConfig applicationConfig;
	
    FileSystemResource fileSystemResource;
	 
	
	 @Autowired
	 NotificationItemWriter(ApplicationConfig applicationConfig){

		 this.applicationConfig=applicationConfig;
		 applicationConfig.setFileName(applicationConfig.getFilePath()+BatchConstants.NOTIFICATION_TABLE+ReportingUtil.getCurrentDate()+BatchConstants.FILE_TYPE);
		 this.fileSystemResource= new FileSystemResource(applicationConfig.getFileName()); 
		 
		 super.setResource(this.fileSystemResource);
		 super.setLineAggregator(new DelimitedLineAggregator<Notification>() {{
		      setFieldExtractor(new BeanWrapperFieldExtractor<Notification>() {{
		       setNames(new String[] { BatchConstants.FIELD_ENTITY_STRING });
		      }});
		     }});
	 }
	 
	 
	public void write(List<? extends Notification> Notifications) throws IvansBatchItemException {
	      
	  try {
		super.write(Notifications);
		//TODO: writing file to cloud logic has to be written here. Write Processor has implementation of cloud logic
		cloudWriter.writeFile(this.fileSystemResource);
	} catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in NotificationItemWriter",exception);
	}
	}
	  	

}
