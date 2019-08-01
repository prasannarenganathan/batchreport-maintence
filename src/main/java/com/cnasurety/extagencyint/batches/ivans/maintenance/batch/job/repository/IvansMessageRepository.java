package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;



public interface IvansMessageRepository extends JpaRepository<IvansMessage, String> {
	
	 @Query("SELECT ima  FROM IvansMessage ima where ima.lastModifiedDate > :lastExecutedDate")
	    List<IvansMessage> findAllByTimeStamp(@Param("lastExecutedDate") Timestamp lastExecutedDate);
	 
	 

}