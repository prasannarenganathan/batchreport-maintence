package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;

public class ReportingUtil {

	
	public static String convertToString(Object obj) {
		if(obj!=null) {
			return obj.toString();
		}else {
			return "";
		}
	}
	
	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat(BatchConstants.FILE_DATE_FORMAT);
		return df.format(new Date());
	}
	
	public static String generateLogId() {
		  UUID logId = UUID.randomUUID();
		  return logId.toString();
		  
	}
}
