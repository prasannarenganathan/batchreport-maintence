package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;
@Service
public class EventAuditItemProcessor implements ItemProcessor<EventAudit, EventAudit> {

	@Override
	public EventAudit process(EventAudit e) throws IvansBatchItemException {
		 
 		 
 		 
 		try {
         
        e.setEntityString(StringUtils.arrayToDelimitedString(
        		new String[] { ReportingUtil.convertToString(e.getEventAuditKey()), e.getNotificationEventFromStatus(),
                e.getNotificationEventToStatus(), e.getPackageEventFromStatus(), e.getPackageEventToStatus(),
                ReportingUtil.convertToString(e.getLastModifiedDate()), ReportingUtil.convertToString(e.getNotificationKey()), ReportingUtil.convertToString(e.getPackageKey())}, 
        		BatchConstants.ITEM_DELIMINATOR));
 		 }catch(Exception exception){ 			
 			 throw new IvansBatchItemException("Error in EventAuditItemProcessor",exception);
 		 }
		return e;
	}

	

}
