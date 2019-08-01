package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"EVENT_AUDIT_TBL\"", schema = "workflow")
public class EventAudit extends EntityDataReader{

    @Id
    @Column(name = "\"EVENT_AUDIT_KEY\"")
    private UUID eventAuditKey;

    @Column(name = "\"NOTIFICATION_EVENT_FROM_STATUS\"")
    private String notificationEventFromStatus;

    @Column(name = "\"NOTIFICATION_EVENT_TO_STATUS\"")
    private String notificationEventToStatus;

    @Column(name = "\"PACKAGE_EVENT_FROM_STATUS\"")
    private String packageEventFromStatus;

    @Column(name = "\"PACKAGE_EVENT_TO_STATUS\"")
    private String packageEventToStatus;

    @Column(name = "\"LAST_MODIFIED_DATE\"")
    Timestamp lastModifiedDate;

    @Column(name = "\"NOTIFICATION_KEY\"")
    private UUID notificationKey;

    @Column(name = "\"PACKAGE_KEY\"")
    private UUID packageKey;



    public UUID getEventAuditKey() {
        return eventAuditKey;
    }

    public void setEventAuditKey(UUID eventAuditKey) {
        this.eventAuditKey = eventAuditKey;
    }

    public String getNotificationEventFromStatus() {
        return notificationEventFromStatus;
    }

    public void setNotificationEventFromStatus(String notificationEventFromStatus) {
        this.notificationEventFromStatus = notificationEventFromStatus;
    }

    public String getNotificationEventToStatus() {
        return notificationEventToStatus;
    }

    public void setNotificationEventToStatus(String notificationEventToStatus) {
        this.notificationEventToStatus = notificationEventToStatus;
    }

    public String getPackageEventFromStatus() {
        return packageEventFromStatus;
    }

    public void setPackageEventFromStatus(String packageEventFromStatus) {
        this.packageEventFromStatus = packageEventFromStatus;
    }

    public String getPackageEventToStatus() {
        return packageEventToStatus;
    }

    public void setPackageEventToStatus(String packageEventToStatus) {
        this.packageEventToStatus = packageEventToStatus;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

 
    public UUID getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(UUID notificationKey) {
        this.notificationKey = notificationKey;
    }

   

    public UUID getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(UUID packageKey) {
        this.packageKey = packageKey;
    }

    public void setEventAuditKey(String string) {
        // TODO Auto-generated method stub
        
    }

}
