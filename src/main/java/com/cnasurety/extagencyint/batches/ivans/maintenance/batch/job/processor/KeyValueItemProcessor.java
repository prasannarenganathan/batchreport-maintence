package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class KeyValueItemProcessor implements ItemProcessor<KeyValue, KeyValue> {
	
	
	@Override
	public KeyValue process(KeyValue e) throws IvansBatchItemException {
		try {
      
      
        e.setEntityString(StringUtils.arrayToDelimitedString(new String[] { e.getKeyValueKey().toString(), e.getKey(), e.getValue(),
          		ReportingUtil.convertToString(e.getLastModifiedDate()), ReportingUtil.convertToString(e.getKeyValuePairId()),
        }, BatchConstants.ITEM_DELIMINATOR));
	 }catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in KeyValueItemProcessor",exception);
	 }
		return e;
	}
    

}
