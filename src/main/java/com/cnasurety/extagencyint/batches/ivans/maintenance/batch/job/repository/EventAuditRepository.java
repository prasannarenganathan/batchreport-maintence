package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;



@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, String> {

    @Query("SELECT ed  FROM EventAudit ed where ed.lastModifiedDate > :lastExecutedDate")
    List<EventAudit> findAllByTimeStamp(@Param("lastExecutedDate") Timestamp lastExecutedDate);

}
