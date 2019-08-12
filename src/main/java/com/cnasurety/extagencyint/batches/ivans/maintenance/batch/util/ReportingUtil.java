package com.cnasurety.extagencyint.batches.ivans.maintenance.batch.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ReportingUtil {

	
	public static String convertToString(Object obj) {
		if(obj!=null) {
			return obj.toString();
		}else {
			return "";
		}
	}
	
	public static String getFormattedDate() {
		 DateTimeFormatter dtf = DateTimeFormat.forPattern(BatchConstants.DATE_FORMAT);
		    DateTime dt = DateTime.now(DateTimeZone.UTC);
		    return dt.toString(dtf);
	}
	
}
