package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
}
