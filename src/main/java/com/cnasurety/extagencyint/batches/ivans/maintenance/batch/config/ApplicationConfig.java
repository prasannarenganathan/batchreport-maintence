package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config;


import java.sql.Timestamp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="workflow.report")
@PropertySources(value = {@PropertySource(value = "classpath:application.properties")})
public class ApplicationConfig { 
    String filePath;
    
    Timestamp lastExecutedJobTimeStamp;

    String fileName;
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

	public Timestamp getLastExecutedJobTimeStamp() {
		return lastExecutedJobTimeStamp;
	}

	public void setLastExecutedJobTimeStamp(Timestamp lastExecutedJobTimeStamp) {
		this.lastExecutedJobTimeStamp = lastExecutedJobTimeStamp;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
    
}
