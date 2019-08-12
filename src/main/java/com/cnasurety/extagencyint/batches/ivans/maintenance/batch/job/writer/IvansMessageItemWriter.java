package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.io.File;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class IvansMessageItemWriter extends FlatFileItemWriter<IvansMessage> {

	@Autowired
	GCloudWriter cloudWriter;
	
    ApplicationConfig applicationConfig;
	
    FileSystemResource fileSystemResource;

    /*
     * Created custom Flat Item Writer to push the written file into the cloud
     * Hence, initializing the resource and aggregator of FileItemWriter 
     */
	 @Autowired
	 IvansMessageItemWriter(ApplicationConfig applicationConfig){

		 this.applicationConfig=applicationConfig;
		 applicationConfig.setFileName(applicationConfig.getFilePath()+BatchConstants.IVANS_MESSAGE_TABLE+ReportingUtil.getFormattedDate()+BatchConstants.FILE_TYPE);
		 this.fileSystemResource= new FileSystemResource(applicationConfig.getFileName()); 
		 
		 super.setResource(this.fileSystemResource);
		 super.setLineAggregator(new DelimitedLineAggregator<IvansMessage>() {{
		      setFieldExtractor(new BeanWrapperFieldExtractor<IvansMessage>() {{
		       setNames(new String[] { BatchConstants.FIELD_ENTITY_STRING });
		      }});
		     }});
	 }
	 
    /*
     * Over riding write method to implement the gcloud file writer.
     */
	public void write(List<? extends IvansMessage> IvansMessages) throws IvansBatchItemException {
	      
	  try {
		super.write(IvansMessages);
		String successLink=cloudWriter.writeFile( this.fileSystemResource);
		if(!StringUtils.isEmpty(successLink)) {
			File file = this.fileSystemResource.getFile();
			file.delete();
		}
		
	} catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in IvansMessageItemWriter",exception);
	}
	}
	  
	}
