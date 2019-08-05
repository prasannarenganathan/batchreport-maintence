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
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;

@Service
public class KeyValueItemWriter extends FlatFileItemWriter<KeyValue> {

	@Autowired
	GCloudWriter cloudWriter;
	
    ApplicationConfig applicationConfig;
	
    FileSystemResource fileSystemResource;
    
    /*
     * Created custom Flat Item Writer to push the written file into the cloud
     * Hence, initializing the resource and aggregator of FileItemWriter 
     */
	 @Autowired
	 KeyValueItemWriter(ApplicationConfig applicationConfig){

		 this.applicationConfig=applicationConfig;
		 applicationConfig.setFileName(applicationConfig.getFilePath()+BatchConstants.KEY_VALUE_TABLE+ReportingUtil.getCurrentDate()+BatchConstants.FILE_TYPE);
		 this.fileSystemResource= new FileSystemResource(applicationConfig.getFileName()); 
		 
		 super.setResource(this.fileSystemResource);
		 super.setLineAggregator(new DelimitedLineAggregator<KeyValue>() {{
		      setFieldExtractor(new BeanWrapperFieldExtractor<KeyValue>() {{
		       setNames(new String[] { BatchConstants.FIELD_ENTITY_STRING });
		      }});
		     }});
	 }
	 
	/*
	 * Over riding write method to implement the gcloud file writer.
	 */	
	public void write(List<? extends KeyValue> KeyValues) throws IvansBatchItemException {
	      
	  try {
		super.write(KeyValues);
		//TODO: writing file to cloud logic has to be written here. Write Processor has implementation of cloud logic
		cloudWriter.writeFile( this.fileSystemResource);
	} catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in KeyValueItemWriter",exception);
	}
	}
	  
	
	

	

}
