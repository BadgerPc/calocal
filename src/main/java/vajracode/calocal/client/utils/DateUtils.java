package vajracode.calocal.client.utils;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;

public class DateUtils {
	
	private static final DateTimeFormat full = DateTimeFormat.getFormat("d MMMM yyyy");
	private static final DateTimeFormat year = DateTimeFormat.getFormat("d MMMM");
	private static final DateTimeFormat today = DateTimeFormat.getFormat("hh:mm");

	public static String formatDate(long date) {
		Date now = new Date();
		//int offset = now.getTimezoneOffset();		
		Date dt = new Date(date);
								
		if (CalendarUtil.isSameDate(dt, now))
			return "сегодня в " + today.format(dt);
		
		
//		Calendar yesterday = Calendar.getInstance(session.getTimeZone());
//		yesterday.add(Calendar.DAY_OF_YEAR, -1);
		
		Date yesterday = new Date();
		CalendarUtil.addDaysToDate(yesterday, -1);
		
		if (CalendarUtil.isSameDate(dt, yesterday))
			return "вчера в " + today.format(dt);
		
		if (dt.getYear() == now.getYear())
			return year.format(dt);
		
		return full.format(dt);
	}
	

}
