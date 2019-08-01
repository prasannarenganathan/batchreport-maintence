package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.job.writer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.config.ApplicationConfig;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.CSVWriter;
import com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util.ReportingUtil;


@Service
public class WriteProcessor {

	private static final String FILE_TYPE = ".csv";
	
	private static final char SEPARATOR = '|';
	
    /*
     * @Autowired CloudStorageHelper gcloudStorage;
     */
	
	@Autowired
    ApplicationConfig applicationConfig;
	
	public String writeFile(List<String[]> data, String tableName) throws IOException {
		
		 String storageSuccesslink = null;
		 File file = new File(applicationConfig.getFilePath()+tableName+ReportingUtil.getCurrentDate()+FILE_TYPE);
		 FileWriter outputfile = new FileWriter(file);
		 
		 CSVWriter writer = new CSVWriter(outputfile, SEPARATOR,
                 CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		 writer.writeAll(data);
         writer.close();
         
         final InputStream targetStream = new DataInputStream(new FileInputStream(file));
        /*
         * storageSuccesslink =
         * gcloudStorage.uploadFile(tableName,FILE_TYPE,targetStream, "ivans_bucket");
         * if(!StringUtils.isEmpty(storageSuccesslink)) { file.delete(); }
         */
		return storageSuccesslink;
	}
		
}
