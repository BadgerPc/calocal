package vajracode.calocal.client.utils;

import com.google.gwt.i18n.client.DateTimeFormat;

public class DateUtils {
	
	public static final DateTimeFormat full = DateTimeFormat.getFormat("hh:mm - d MMMM yyyy");
	public static final DateTimeFormat date = DateTimeFormat.getFormat("d MMMM yyyy");
	public static final DateTimeFormat time = DateTimeFormat.getFormat("hh:mm");

}
