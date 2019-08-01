package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;


@Service
public class EventAuditItemWriter implements ItemWriter<EventAudit> {

	@Autowired
	WriteProcessor writeProcessor;
	
	 private static final String KEY_VALUE_TABLE = "KEYVALUETABLE_";
	
	@Override
	public void write(List<? extends EventAudit> eventAudits) throws IvansBatchItemException {
		 List<String[]> data = new ArrayList<String[]>();
 		 try {
 		for (EventAudit e : eventAudits) {
            data.add(e.getEntityDataString());
        }
 		writeProcessor.writeFile(data, KEY_VALUE_TABLE);
 		
	}catch(Exception exception){ 			
		 throw new IvansBatchItemException("Error in EventAuditItemWriter",exception);
	}
	    
	}

	

	

	

}
