package vajracode.calocal.server.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import vajracode.calocal.shared.exceptions.FieldException;

public class ParamUtils {
	
	private static final ZoneId zone = ZoneId.systemDefault();
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

	public static LocalDateTime getDate(long millis) {
		return millis == 0 ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
	}

	public static LocalTime getTime(String time) {
		try {
			return time == null ? null : LocalTime.parse(time, timeFormatter);
		} catch (DateTimeParseException e) {
			throw new FieldException("Wrong time format: " + e.getMessage());
		}
	}

	public static Date toDate(LocalDateTime ldt) {
		return ldt == null ? null : Date.from(ldt.atZone(zone).toInstant());
	}

}
