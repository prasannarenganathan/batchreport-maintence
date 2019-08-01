package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.model.BatchJobExecution;

@Repository
public interface BatchJobExecutionRepository extends JpaRepository<BatchJobExecution, String> {
    @Query(nativeQuery = true, value = "select max(end_time) from maintenance.batch_job_execution where status='COMPLETED'")
    Timestamp findLastExecutedTimeStamp();
}
