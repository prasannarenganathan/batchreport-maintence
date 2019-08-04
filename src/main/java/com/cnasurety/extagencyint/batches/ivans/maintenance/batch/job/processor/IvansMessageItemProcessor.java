package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessageAttachment;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.BatchConstants;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;
@Service
public class IvansMessageItemProcessor implements ItemProcessor<IvansMessage, IvansMessage> {

	@Override
	public IvansMessage process(IvansMessage ivansMessage) throws IvansBatchItemException {
		 
		
         	
         	String[] ivansMessageData = null; 
         	try {
         	if(!ivansMessage.getIvansMessageAttaments().isEmpty()) {
	            	for(IvansMessageAttachment ivansMessageAttachment:ivansMessage.getIvansMessageAttaments()) {
	            		ivansMessageData =  new String[] {
	            				ReportingUtil.convertToString(ivansMessage.getIvansMessageKey()),
		                		ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
		                		ivansMessage.getNaicsCode(),	
		                		ivansMessage.getBondNumber(),
		                		ivansMessage.getTermEffectiveDate().toString(),
		                		ReportingUtil.convertToString(ivansMessage.getTermExpiryDate()),
		                		ReportingUtil.convertToString(ivansMessage.getTransactionDate()),
		                		ivansMessage.getLineOfBusiness(),
		                		ivansMessage.getPrincipalName(),
		                		ReportingUtil.convertToString(ivansMessage.getEventDate()),
		                		ivansMessage.getActivityNoteTypeCode(),
		                		ivansMessage.getBusinessPurposeTypeCode(),
		                		ivansMessage.getRemarkText(),
		                		ReportingUtil.convertToString(ivansMessage.getLastModifiedDate()),
		                		String.valueOf(ivansMessage.getDeliveryFailureCount()),
		                	
		                		ReportingUtil.convertToString(ivansMessageAttachment.getIvansMessgaeAttachmentKey()),
		                		ivansMessageAttachment.getAttachmentTypeCode(),
		            	        ivansMessageAttachment.getAttachmentDescription(),
		            	        ivansMessageAttachment.getAttachmentFileName(),
		            	        ivansMessageAttachment.getMimeTypeCode(),
		            	        ReportingUtil.convertToString(ivansMessageAttachment.getLastModifiedDate()),
		            	        ReportingUtil.convertToString(ivansMessageAttachment.getPackageKey())//,
		            	        
		            	        //ReportingUtil.convertToString(ivansMessageAttachment.getIvansMessageKey()
	            		};
		            }
         	}else {
         		ivansMessageData =  new String[] {
	                		ivansMessage.getIvansMessageKey().toString(),ivansMessage.getAgencyStateCode(),ivansMessage.getAgencyCode(),
	                		ivansMessage.getNaicsCode(),	ivansMessage.getBondNumber(),ReportingUtil.convertToString(ivansMessage.getTermEffectiveDate()),
	                		ReportingUtil.convertToString(ivansMessage.getTermExpiryDate()),
	                		ReportingUtil.convertToString(ivansMessage.getTransactionDate()),
	                		ivansMessage.getPrincipalName(),
	                		ReportingUtil.convertToString(ivansMessage.getEventDate()),
	                		ivansMessage.getBusinessPurposeTypeCode(),ivansMessage.getRemarkText(),
	                		ReportingUtil.convertToString(ivansMessage.getLastModifiedDate()),
	                		String.valueOf(ivansMessage.getDeliveryFailureCount()),
	                				
	                		"","","","","","",""};
	            }
         
 		 
 		
      
         	ivansMessage.setEntityString(StringUtils.arrayToDelimitedString(ivansMessageData, BatchConstants.ITEM_DELIMINATOR));
         	
	 }catch(Exception exception){ 			
			 throw new IvansBatchItemException("Error in IvansMessageItemProcessor",exception);
		 }
		return ivansMessage;
	}
}
