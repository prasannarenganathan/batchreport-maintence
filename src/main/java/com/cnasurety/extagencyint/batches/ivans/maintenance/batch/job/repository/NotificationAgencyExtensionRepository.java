package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository;


import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.NotificationAgencyExtension;


@Repository
public interface NotificationAgencyExtensionRepository extends JpaRepository<NotificationAgencyExtension, String> {

	 public NotificationAgencyExtension findByNotificationKey(UUID key);

}
