package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ReportingUtil {

	
	public static String convertToString(Object obj) {
		if(obj!=null) {
			return obj.toString();
		}else {
			return "";
		}
	}
	
	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(new Date());
	}
	
	public static String generateLogId() {
		  UUID logId = UUID.randomUUID();
		  return logId.toString();
		  
	}
}
