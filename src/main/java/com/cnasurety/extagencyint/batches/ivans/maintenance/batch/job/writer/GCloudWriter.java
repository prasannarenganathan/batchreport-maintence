package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.CSVWriter;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class GCloudWriter {

	
    /*
     * @Autowired CloudStorageHelper gcloudStorage;
     */
	
	@Autowired
    ApplicationConfig applicationConfig;
	
	public String writeFile(FileSystemResource resource) throws IOException {
		
		 String storageSuccesslink = null;
         final InputStream targetStream = new DataInputStream(new FileInputStream(resource.getFile()));
        /*
         * storageSuccesslink =
         * gcloudStorage.uploadFile(tableName,FILE_TYPE,targetStream, "ivans_bucket");
         * if(!StringUtils.isEmpty(storageSuccesslink)) { file.delete(); }
         */
		return storageSuccesslink;
	}
		
}
