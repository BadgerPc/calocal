package vajracode.calocal.client.utils;

import com.google.gwt.i18n.client.DateTimeFormat;

import vajracode.calocal.shared.exceptions.FieldException;

public class DateUtils {
	
	public static final DateTimeFormat full = DateTimeFormat.getFormat("hh:mm - d MMMM yyyy");
	public static final DateTimeFormat dateYear = DateTimeFormat.getFormat("d MMMM yyyy");
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
	

}
