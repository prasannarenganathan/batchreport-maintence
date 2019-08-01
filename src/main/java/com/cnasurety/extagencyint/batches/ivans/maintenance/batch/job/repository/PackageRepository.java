package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.PackageEntity;


@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, String> {
    @Query("SELECT p.packageKey  FROM PackageEntity p where p.keyValuePairId = :keyValuePairId")
   
   
   
    String findPackageIdByKeyValuePairId(@Param("keyValuePairId") UUID keyValuePairId);
    
    List<PackageEntity> findByNotificationKey(UUID notificationkey);
}
