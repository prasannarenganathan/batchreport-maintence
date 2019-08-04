package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model;

import java.util.List;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessageAttachment;

public class EntityDataReader {
	
	String entityString;

	
	private List<IvansMessageAttachment> ivansMessageAttaments;
	
	public List<IvansMessageAttachment> getIvansMessageAttaments() {
		return ivansMessageAttaments;
	}

	public void setIvansMessageAttaments(List<IvansMessageAttachment> ivansMessageAttaments) {
		this.ivansMessageAttaments = ivansMessageAttaments;
	}
	
	
	NotificationAgencyExtension notificationAgencyExtension;

	public NotificationAgencyExtension getNotificationAgencyExtension() {
		return notificationAgencyExtension;
	}

	public void setNotificationAgencyExtension(NotificationAgencyExtension notificationAgencyExtension) {
		this.notificationAgencyExtension = notificationAgencyExtension;
	}

	public String getEntityString() {
		return entityString;
	}

	public void setEntityString(String entityString) {
		this.entityString = entityString;
	}

	
	
	
}
