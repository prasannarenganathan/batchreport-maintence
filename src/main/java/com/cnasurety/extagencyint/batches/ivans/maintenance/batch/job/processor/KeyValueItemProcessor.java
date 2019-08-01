package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class KeyValueItemProcessor implements ItemProcessor<KeyValue, KeyValue> {
	
	
	@Override
	public KeyValue process(KeyValue e) throws IvansBatchItemException {
		try {
        String[] entityDataString = new String[] { e.getKeyValueKey().toString(), e.getKey(), e.getValue(),
          		ReportingUtil.convertToString(e.getLastModifiedDate()), ReportingUtil.convertToString(e.getKeyValuePairId()),
                };
        e.setEntitydataString(entityDataString);
	 }catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in KeyValueItemProcessor",exception);
	 }
		return e;
	}
    

}
