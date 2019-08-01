package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;
@Service
public class EventAuditItemProcessor implements ItemProcessor<EventAudit, EventAudit> {

	@Override
	public EventAudit process(EventAudit e) throws IvansBatchItemException {
		 
 		 
 		 
 		try {
        String[] entityDataString = new String[] { ReportingUtil.convertToString(e.getEventAuditKey()), e.getNotificationEventFromStatus(),
                    e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                    ReportingUtil.convertToString(e.getLastModifiedDate()), ReportingUtil.convertToString(e.getNotificationKey()), ReportingUtil.convertToString(e.getPackageKey())};
        
        e.setEntitydataString(entityDataString);
 		 }catch(Exception exception){ 			
 			 throw new IvansBatchItemException("Error in EventAuditItemProcessor",exception);
 		 }
		return e;
	}

	

}
