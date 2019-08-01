package com.cnasurety.extagencyint.batches.ivans.maintenance.batch;

import java.io.File;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.exception.IvansBatchItemException;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.EventAudit;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.IvansMessage;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.KeyValue;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.model.Notification;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor.EventAuditItemProcessor;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor.IvansMessageItemProcessor;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor.KeyValueItemProcessor;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.processor.NotificationItemProcessor;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader.EventAuditItemReader;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader.IvansMessageItemReader;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader.KeyValueItemReader;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.reader.NotificationItemReader;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.repository.BatchJobExecutionRepository;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer.EventAuditItemWriter;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer.IvansMessageItemWriter;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer.KeyValueItemWriter;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer.NotificationItemWriter;

@Configuration
@EnableBatchProcessing
public class IvansMaintenanceJobConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    EventAuditItemReader eventAuditItemReader;

    @Autowired
    EventAuditItemProcessor eventAuditItemProcessor;

    @Autowired
    EventAuditItemWriter eventAuditItemWriter;
    
    @Autowired
    KeyValueItemReader keyValueItemReader;

    @Autowired
    KeyValueItemProcessor keyValueItemProcessor;

    @Autowired
    KeyValueItemWriter keyValueItemWriter;

    @Autowired
    IvansMessageItemReader ivansMessageItemReader;
    
    @Autowired
    IvansMessageItemProcessor ivansMessageItemProcessor;

    @Autowired
    IvansMessageItemWriter ivansMessageItemWriter;

    @Autowired
    NotificationItemReader notificationItemReader;

    @Autowired
    NotificationItemProcessor notificationItemProcessor;

    @Autowired
    NotificationItemWriter notificationItemWriter;

    @Autowired
    ApplicationConfig applicationConfig;
    
   

    @Autowired
    private BatchJobExecutionRepository batchJobExecutionRepository;

    Timestamp lastExecutedJobTimeStamp;

    public Timestamp getLastExecutedJobTimeStamp() {
        return lastExecutedJobTimeStamp;
    }

    public void setLastExecutedJobTimeStamp(Timestamp lastExecutedJobTimeStamp) {
        this.lastExecutedJobTimeStamp = lastExecutedJobTimeStamp;
    }

    @Bean
    public Job exportEventAuditJob(JobCompletionNotificationListener listener,  Step eventAuditStep) {
    	
        File directory = new File(applicationConfig.getFilePath());
        if (!directory.exists()) {
            directory.mkdir();
        }

        applicationConfig.setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

       return jobBuilderFactory.get("exportEventAuditJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(eventAuditStep).end().build();
       
    	
   }

 
    
    @Bean
    public Step eventAuditStep(StepErrorLoggingListener stepErrorLoggingListener) {
        return stepBuilderFactory.get("eventAuditStep").<EventAudit, EventAudit>chunk(1).reader(eventAuditItemReader)
                .processor(eventAuditItemProcessor).writer(eventAuditItemWriter).listener(stepErrorLoggingListener)
              
                .build();
    }
     
  

    @Bean
    public Job exportKeyValueJob(JobCompletionNotificationListener listener, Step keyValueStep) {

    	 applicationConfig.setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportKeyValueJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(keyValueStep).end().build();
    }

    @Bean
    public Step keyValueStep() {
        return stepBuilderFactory.get("keyValueStep").<KeyValue, KeyValue>chunk(10).reader(keyValueItemReader)
                .processor(keyValueItemProcessor).writer(keyValueItemWriter).build();
    }

  

    @Bean
    public Job exportIvansMessageJob(JobCompletionNotificationListener listener, Step ivansMessageStep) {


    	 applicationConfig.setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportIvansMessageJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(ivansMessageStep).end().build();
    }

    @Bean
    public Step ivansMessageStep() {
        return stepBuilderFactory.get("ivansMessageStep").<IvansMessage, IvansMessage>chunk(10)
                .reader(ivansMessageItemReader).processor(ivansMessageItemProcessor).writer(ivansMessageItemWriter)
                .build();
    }

    

    @Bean
    public Job exportNotificationJob(JobCompletionNotificationListener listener, Step notificationStep) {

       
    	 applicationConfig.setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportIvansMessageJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(notificationStep).end().build();
    }

    @Bean
    public Step notificationStep() {
        return stepBuilderFactory.get("notificationStep").<Notification, Notification>chunk(10)
                .reader(notificationItemReader).processor(notificationItemProcessor).writer(notificationItemWriter)
                .build();
    }

  

}
