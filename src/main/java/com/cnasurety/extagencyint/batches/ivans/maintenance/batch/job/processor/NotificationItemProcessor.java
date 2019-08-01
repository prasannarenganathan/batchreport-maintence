package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.DocumentEntity;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.NotificationAgencyExtension;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.PackageEntity;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.DocumentRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.NotificationAgencyExtensionRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.PackageRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;



@Service
public class NotificationItemProcessor implements ItemProcessor<Notification, Notification> {

@Autowired
   NotificationAgencyExtensionRepository notificationAgencyExtensionRepository;
   
   @Autowired
   DocumentRepository documentRepository;

   @Autowired
   PackageRepository packageRepository;


@Override
public Notification process(Notification notification) throws Exception {
NotificationAgencyExtension notificationAgencyExtension = null;
       List<PackageEntity> packages = null;
       List<DocumentEntity> documents = null;
       String[] notificationData = null;

       notificationAgencyExtension =   notificationAgencyExtensionRepository.findByNotificationKey(notification.getNotificationKey());
       if(notificationAgencyExtension!=null) {
       
           packages = packageRepository.findByNotificationKey(notification.getNotificationKey());
           if(!packages.isEmpty()) {
               for(PackageEntity packageEntity: packages) {
                   documents = documentRepository.findByPackageKey(packageEntity.getPackageKey());
                   if(!documents.isEmpty()) {
                       for(DocumentEntity documentEntity: documents) {
                           notificationData = new String[]{
                                   //NOTIFICATION TABLE        
                                     /*"NOTIFICATION_TYPE_CODE","NOTIFICATION_GLOBAL_ID","AGENCY_STATE_CODE","AGENCY_CODE",
                                       "PROCESSING_OFFICE_TYPE_CODE","PROCESSING_OFFICE_CODE","ACCOUNT_NUMBER","WRITING_COMPANY_CODE", 
                                       "SUBMISSION_NUMBER","BOND_NUMBER","TERM_EFFECTIVE_DATE","TERM_EXPIRY_DATE",     
                                       "TERM_NUMBER","TRANSACTION_DATE","LINE_OF_BUSINESS","PRINCIPAL_NAME",               
                                       "EVENT_DATE","EVENT_TYPE_CODE","EVENT_SUB_TYPE_CODE","SPECIAL_HANDLING_INDICATOR",  
                                       "REMARK_TEXT","NOTIFICATION_WORKFLOW_STATUS_TYPE_CODE","LAST_MODIFIED_DATE","NOTIFICATION_KEY",                 
                                       "IVANS_MESSAGE_KEY","KEY_VALUE_PAIR_ID",
                                     */  
                             notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
                             notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
                             notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
                             notification.getTermNumber(),notification.getTransactionDate().toString(),notification.getLineOfBusiness(),notification.getPrincipalName(),
                             notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
                             notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
                             ReportingUtil.convertToString(notification.getIvansMessageKey()),ReportingUtil.convertToString(notification.getKeyValuePairId()),String.valueOf(notification.getNotificationFailureCount()),
                             
                             
                           //NOTIFICATION AGENCY EXTENSION TABLE
                               /*"NOTIFICATION_AGENCY_EXTENSION_KEY","IVANS_ENROLLMENT_IND","IVANS_PREF_DIRBILRPRTS",          
                               "IVANS_PREF_AGCYBILSTMTS","IVANS_PREF_IBL","IVANS_PREF_SF_TX",                  
                               "IVANS_PREF_BR_TX","IVANS_YACCTNUM","IVANS_LASTUPDATE_DTTM"
                               */
                             notificationAgencyExtension.getNotificationAgencyExtensionKey().toString(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
                             ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
                             ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getLastModifiedDate()),//ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
                             
                             
                           //PACKAGE TABLE
                               /*"PACKAGE_KEY","PACKAGE_TYPE_CODE","PACKAGE_ID",               
                               "PACKAGE_DESCRIPTION","PACKAGE_OUTPUT_FILE_NAME","MIME_TYPE_CODE",          
                               "KEY_VALUE_PAIR_ID","PACKAGE_WORKFLOW_STATUS_TYPE","LAST_MODIFIED_DATE"*/
                             ReportingUtil.convertToString(packageEntity.getPackageKey()),     packageEntity.getPackageTypeCode(),         packageEntity.getPackageId(),
                             packageEntity.getPackageDescription(),                            packageEntity.getPackageOutputFileName(),   packageEntity.getMimeTypeCode(),
                             ReportingUtil.convertToString(packageEntity.getKeyValuePairId()),                             packageEntity.getPackageWorkflowStatusType(),ReportingUtil.convertToString(packageEntity.getLastModifiedDate()),
                             String.valueOf(packageEntity.getPackageFailureCount()),
                             //ReportingUtil.convertToString(packageEntity.getNotificationKey()),
   
                           //DOCUMENT TABLE
                               /*"DOCUMENT_KEY","DOCUMENT_TYPE_CODE","DOCUMENT_ID",                
                               "MIME_TYPE_CODE","DOCUMENT_REPOSITORY_CODE","KEY_VALUE_PAIR_ID",        
                               "LAST_MODIFIED_DATE"*/
                             documentEntity.getDocumentKey().toString(),   documentEntity.getDocumentTypeCode(),   documentEntity.getDocumentId(),
                             documentEntity.getMimeTypeCode(), documentEntity.getRepositoryCode(),     ReportingUtil.convertToString(documentEntity.getKeyValuePairId()),
                             //ReportingUtil.convertToString(documentEntity.getPackageKey()),  
                             ReportingUtil.convertToString(documentEntity.getLastModifiedDate())
                             
                             };    
                           notification.setEntitydataString(notificationData);
                       }
                   }else {
                       notificationData = new String[]{
                               //export notification, notification agency extension and package  details
                                 notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
                                 notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
                                 notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
                                 notification.getTermNumber(),notification.getTransactionDate().toString(),notification.getLineOfBusiness(),notification.getPrincipalName(),
                                 notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
                                 notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
                                 ReportingUtil.convertToString(notification.getIvansMessageKey()),ReportingUtil.convertToString(notification.getKeyValuePairId()),String.valueOf(notification.getNotificationFailureCount()),
                                 
                                 notificationAgencyExtension.getNotificationAgencyExtensionKey().toString(),   ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()), ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
                                 ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
                                 ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getLastModifiedDate()),ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
                                 
                                 ReportingUtil.convertToString(packageEntity.getPackageKey()), packageEntity.getPackageTypeCode(), packageEntity.getPackageId(),
                                 packageEntity.getPackageDescription(),packageEntity.getPackageOutputFileName(),packageEntity.getMimeTypeCode(),
                                 ReportingUtil.convertToString(packageEntity.getKeyValuePairId()),packageEntity.getPackageWorkflowStatusType(),ReportingUtil.convertToString(packageEntity.getLastModifiedDate()),ReportingUtil.convertToString(packageEntity.getNotificationKey()),
                                 String.valueOf(packageEntity.getPackageFailureCount()),
                                 
                                 "","","","","","","","" 
                                 
                        };
                       notification.setEntitydataString(notificationData);
                   }
               }
           }else {
               //export notification and notifi agency extension details
               notificationData = new String[]{
                         notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
                         notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
                         notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
                         notification.getTermNumber(),notification.getTransactionDate().toString(),notification.getLineOfBusiness(),notification.getPrincipalName(),
                         notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
                         notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
                         ReportingUtil.convertToString(notification.getIvansMessageKey()),ReportingUtil.convertToString(notification.getKeyValuePairId()),String.valueOf(notification.getNotificationFailureCount()),
                         
                         notificationAgencyExtension.getNotificationAgencyExtensionKey().toString(),ReportingUtil.convertToString(notificationAgencyExtension.getIvansEnrollmentInd()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefDirbilrprts()),
                         ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefAgcybilstmts()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefIbl()),ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefSfTx()),
                         ReportingUtil.convertToString(notificationAgencyExtension.getIvansPrefBrTx()),notificationAgencyExtension.getIvansYacctnum(),ReportingUtil.convertToString(notificationAgencyExtension.getLastModifiedDate()),ReportingUtil.convertToString(notificationAgencyExtension.getNotificationKey()),
                         
                         "","","","","","","","",
                         "","","","","","","","",""
               };
               notification.setEntitydataString(notificationData);
           }
       }else {
           notificationData =  new String[]{
                     notification.getNotificationTypeCode(), notification.getNotificationGlobalId(), notification.getAgencyStateCode(),notification.getAgencyCode(),
                     notification.getProcessingOfficeTypeCode(),notification.getProcessingOfficeCode(),notification.getAccountNumber(),notification.getWritingCompanyCode(),
                     notification.getSubmissionNumber(),notification.getBondNumber(),ReportingUtil.convertToString(notification.getTermEffectiveDate()),ReportingUtil.convertToString(notification.getTermExpiryDate()),
                     notification.getTermNumber(),notification.getTransactionDate().toString(),notification.getLineOfBusiness(),notification.getPrincipalName(),
                     notification.getEventDate().toString(),notification.getEventTypeCode(),notification.getEventSubTypeCode(),notification.getSpecialHandlingIndicator(),
                     notification.getRemarkText(),notification.getNotificationWorkflowStatusTypeCode(),ReportingUtil.convertToString(notification.getLastModifiedDate()),ReportingUtil.convertToString(notification.getNotificationKey()),
                     ReportingUtil.convertToString(notification.getIvansMessageKey()), ReportingUtil.convertToString(notification.getKeyValuePairId()),String.valueOf(notification.getNotificationFailureCount()),
                     "","","","","","","","","",
                     "","","","","","","","",
                     "","","","","","","","",""
           };
           notification.setEntitydataString(notificationData);
       }
   
        
return notification;
}
}