package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the "NOTIFICATION_AGENCY_EXTENSION_TBL" database table.
 * 
 */
@Entity
@Table(name="\"NOTIFICATION_AGENCY_EXTENSION_TBL\"", schema = "workflow")
public class NotificationAgencyExtension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"NOTIFICATION_AGENCY_EXTENSION_KEY\"")
	private UUID notificationAgencyExtensionKey;

	@Column(name="\"IVANS_ENROLLMENT_IND\"")
	private Boolean ivansEnrollmentInd;

	@Column(name="\"LAST_MODIFIED_DATE\"")
	 Timestamp lastModifiedDate;

	@Column(name="\"IVANS_PREF_AGCYBILSTMTS\"")
	private Boolean ivansPrefAgcybilstmts;

	@Column(name="\"IVANS_PREF_BR_TX\"")
	private Boolean ivansPrefBrTx;

	@Column(name="\"IVANS_PREF_DIRBILRPRTS\"")
	private Boolean ivansPrefDirbilrprts;

	@Column(name="\"IVANS_PREF_IBL\"")
	private Boolean ivansPrefIbl;

	@Column(name="\"IVANS_PREF_SF_TX\"")
	private Boolean ivansPrefSfTx;

	@Column(name="\"IVANS_YACCTNUM\"", nullable=false)
	private String ivansYacctnum;
	
	@Column(name="\"NOTIFICATION_KEY\"", nullable=false)
	private UUID notificationKey;

	public NotificationAgencyExtension() {
	}


	public UUID getNotificationAgencyExtensionKey() {
        return notificationAgencyExtensionKey;
    }


    public void setNotificationAgencyExtensionKey(UUID notificationAgencyExtensionKey) {
        this.notificationAgencyExtensionKey = notificationAgencyExtensionKey;
    }


    public Boolean getIvansEnrollmentInd() {
		return this.ivansEnrollmentInd;
	}

	public void setIvansEnrollmentInd(Boolean ivansEnrollmentInd) {
		this.ivansEnrollmentInd = ivansEnrollmentInd;
	}

	
	public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }


    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    public Boolean getIvansPrefAgcybilstmts() {
		return this.ivansPrefAgcybilstmts;
	}

	public void setIvansPrefAgcybilstmts(Boolean ivansPrefAgcybilstmts) {
		this.ivansPrefAgcybilstmts = ivansPrefAgcybilstmts;
	}

	public Boolean getIvansPrefBrTx() {
		return this.ivansPrefBrTx;
	}

	public void setIvansPrefBrTx(Boolean ivansPrefBrTx) {
		this.ivansPrefBrTx = ivansPrefBrTx;
	}

	public Boolean getIvansPrefDirbilrprts() {
		return this.ivansPrefDirbilrprts;
	}

	public void setIvansPrefDirbilrprts(Boolean ivansPrefDirbilrprts) {
		this.ivansPrefDirbilrprts = ivansPrefDirbilrprts;
	}

	public Boolean getIvansPrefIbl() {
		return this.ivansPrefIbl;
	}

	public void setIvansPrefIbl(Boolean ivansPrefIbl) {
		this.ivansPrefIbl = ivansPrefIbl;
	}

	public Boolean getIvansPrefSfTx() {
		return this.ivansPrefSfTx;
	}

	public void setIvansPrefSfTx(Boolean ivansPrefSfTx) {
		this.ivansPrefSfTx = ivansPrefSfTx;
	}

	public String getIvansYacctnum() {
		return ivansYacctnum;
	}

	public void setIvansYacctnum(String ivansYacctnum) {
		this.ivansYacctnum = ivansYacctnum;
	}

	public UUID getNotificationKey() {
		return notificationKey;
	}

	public void setNotificationKey(UUID notificationKey) {
		this.notificationKey = notificationKey;
	}
	
	

	

}