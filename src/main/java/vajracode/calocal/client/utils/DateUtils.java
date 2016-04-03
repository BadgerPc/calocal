package vajracode.calocal.client.utils;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

import vajracode.calocal.shared.exceptions.FieldException;

public class DateUtils {
	
	public static final DateTimeFormat full = DateTimeFormat.getFormat("hh:mm - d MMMM yyyy");
	public static final DateTimeFormat dateYear = DateTimeFormat.getFormat("d MMMM yyyy");
	public static final DateTimeFormat year = DateTimeFormat.getFormat("yyyy");
	public static final DateTimeFormat date = DateTimeFormat.getFormat("d MMMM");
	public static final DateTimeFormat time = DateTimeFormat.getFormat("HH:mm");
	//public static final DateTimeFormat timeUi = DateTimeFormat.getFormat("h:mm a");
	public static final DateTimeFormat timeUi = DateTimeFormat.getFormat("H:mm");
	
	public static String timeToUi(String mTime) {
		try {
			return mTime == null ? null : timeUi.format(time.parse(mTime));
		} catch (IllegalArgumentException e) {
			throw new FieldException("Wrong date format: " + e.getMessage());
		}
	}

	public static String uiToTime(String uTime) {
		try {
			return uTime == null ? null : time.format(timeUi.parse(uTime));
		} catch (IllegalArgumentException e) {
			throw new FieldException("Wrong date format: " + e.getMessage());
		}
	}

	public static String formatDateSmart(Date d) {
		int y = Integer.parseInt(year.format(d));
		int yNow = Integer.parseInt(year.format(new Date()));
		return y == yNow ? date.format(d) : dateYear.format(d);		
	}

	public static int getTimeZoneOffset() {
		return new Date().getTimezoneOffset();
	}
	

}
