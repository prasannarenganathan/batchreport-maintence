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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.dto.KeyValueDTO;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.KeyValueRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.PackageRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class KeyValueItemReader implements ItemReader<KeyValue>{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ApplicationConfig applicationConfig;
	
	private ItemReader<KeyValue> delegate;
	@SuppressWarnings("unchecked")
	@Override
	public KeyValue read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (delegate == null) {
            delegate = new IteratorItemReader<>(readTable());
        }
        return  delegate.read();
	}
	
	@Autowired
	KeyValueRepository keyValueRepository;
	/*
 	 * Read table will have list of item as the records are retrived based on the last execution job time
 	 * So we are collecting all the records which are created after the last execution time
 	 */
 	public List<KeyValue> readTable() throws IvansBatchItemException{
 		 List<KeyValue> keyValues = null;
 		 try {
 		 if (Objects.isNull(applicationConfig.getLastExecutedJobTimeStamp())) {
 			keyValues = keyValueRepository.findAll();
            } else {
            	keyValues = keyValueRepository.findAllByTimeStamp(applicationConfig.getLastExecutedJobTimeStamp());
            }
 		keyValues.forEach(keyValue -> {
            KeyValueDTO keyValueDTO = new KeyValueDTO();
            BeanUtils.copyProperties(keyValue, keyValueDTO);
            
            /*
             * The keyva is no 
             */
              if (keyValue.getKeyValuePairTypeCode().equals("NOTIFICATION_TBL")) {
              keyValue.setForiegnKeyId(
              notificationRepository.findNotificationIdByKeyValuePairId(keyValue.
              getKeyValuePairId())); } else if
              (keyValue.getKeyValuePairTypeCode().equals("PACKAGE_TBL")) {
              keyValue.setForiegnKeyId(
              packageRepository.findPackageIdByKeyValuePairId(keyValue.getKeyValuePairId())
              ); } else { // (keyValue.getKeyValuePairTypeCode().equals("DOCUMENT_TBL")) {
              keyValue.setForiegnKeyId(
              documentRepository.findDocumentIdByKeyValuePairId(keyValue.getKeyValuePairId(
              ))); }
             

        });
 		}catch(Exception e){
			
			 throw new IvansBatchItemException("Error in KeyValueItemReader",e);
		 }
 		return keyValues;
 	}
	
	
 	
	  @Autowired
	    NotificationRepository notificationRepository;
	  
	  @Autowired
	    PackageRepository packageRepository;
	  
	  @Autowired
	    DocumentRepository documentRepository;
	  
	 
 
}
