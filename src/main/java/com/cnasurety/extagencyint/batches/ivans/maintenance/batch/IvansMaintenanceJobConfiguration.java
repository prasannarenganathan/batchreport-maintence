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

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    EventAuditItemProcessor eventAuditItemProcessor;

    @Autowired
    EventAuditItemWriter eventAuditItemWriter;

    @Autowired
    KeyValueItemProcessor keyValueItemProcessor;

    @Autowired
    KeyValueItemWriter keyValueItemWriter;

    @Autowired
    IvansMessageItemProcessor ivansMessageItemProcessor;

    @Autowired
    IvansMessageItemWriter ivansMessageItemWriter;

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
    public Job exportEventAuditJob(JobCompletionNotificationListener listener, Step eventAuditStep) {

        File directory = new File(applicationConfig.getFilePath());
        if (!directory.exists()) {
            directory.mkdir();
        }

        setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportEventAuditJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(eventAuditStep).end().build();
    }

    @Bean
    public Step eventAuditStep() {
        return stepBuilderFactory.get("eventAuditStep").<EventAudit, EventAudit>chunk(1).reader(eventAuditItemReader())
                .processor(eventAuditItemProcessor).writer(eventAuditItemWriter).build();
    }

          @Autowired
          public EventAuditItemReader eventAuditItemReader(){
      EventAuditItemReader itemReader = new EventAuditItemReader();
      itemReader.setLastExecutedJobTimeStamp(getLastExecutedJobTimeStamp());
      
      return itemReader; 
      }
     

    @Bean
    public Job exportKeyValueJob(JobCompletionNotificationListener listener, Step keyValueStep) {

        // File directory = new File(applicationConfig.getFilePath());
        // if (! directory.exists()){
        // directory.mkdir();
        // }

        setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportKeyValueJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(keyValueStep).end().build();
    }

    @Bean
    public Step keyValueStep() {
        return stepBuilderFactory.get("keyValueStep").<KeyValue, KeyValue>chunk(10).reader(keyValueItemReader())
                .processor(keyValueItemProcessor).writer(keyValueItemWriter).build();
    }

    @Bean
    public KeyValueItemReader keyValueItemReader() {
        KeyValueItemReader itemReader = new KeyValueItemReader();
        itemReader.setLastExecutedJobTimeStamp(getLastExecutedJobTimeStamp());

        return itemReader;
    }

    @Bean
    public Job exportIvansMessageJob(JobCompletionNotificationListener listener, Step ivansMessageStep) {

        // File directory = new File(applicationConfig.getFilePath());
        // if (! directory.exists()){
        // directory.mkdir();
        // }

        setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportIvansMessageJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(ivansMessageStep).end().build();
    }

    @Bean
    public Step ivansMessageStep() {
        return stepBuilderFactory.get("ivansMessageStep").<IvansMessage, IvansMessage>chunk(10)
                .reader(ivansMessageItemReader()).processor(ivansMessageItemProcessor).writer(ivansMessageItemWriter)
                .build();
    }

    @Bean
    public IvansMessageItemReader ivansMessageItemReader() {
        IvansMessageItemReader itemReader = new IvansMessageItemReader();
        itemReader.setLastExecutedJobTimeStamp(getLastExecutedJobTimeStamp());

        return itemReader;
    }

    @Bean
    public Job exportNotificationJob(JobCompletionNotificationListener listener, Step notificationStep) {

        // File directory = new File(applicationConfig.getFilePath());
        // if (! directory.exists()){
        // directory.mkdir();
        // }

        setLastExecutedJobTimeStamp(batchJobExecutionRepository.findLastExecutedTimeStamp());

        return jobBuilderFactory.get("exportIvansMessageJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(notificationStep).end().build();
    }

    @Bean
    public Step notificationStep() {
        return stepBuilderFactory.get("notificationStep").<Notification, Notification>chunk(10)
                .reader(notificationItemReader()).processor(notificationItemProcessor).writer(notificationItemWriter)
                .build();
    }

    @Bean
    public NotificationItemReader notificationItemReader() {
        NotificationItemReader itemReader = new NotificationItemReader();
        itemReader.setLastExecutedJobTimeStamp(getLastExecutedJobTimeStamp());

        return itemReader;
    }

}
