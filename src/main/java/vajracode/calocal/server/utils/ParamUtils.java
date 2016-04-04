package vajracode.calocal.server.utils;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import vajracode.calocal.shared.exceptions.FieldException;

public class ParamUtils {
	
	private static final Logger log = Logger.getLogger("vajracode.calocal.server.utils.ParamUtils");
	
	private static final ZoneId zone = ZoneId.systemDefault();
	//private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

	public static LocalDateTime getDate(long millis) {
		return millis == 0 ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
	}

	public static LocalTime getTime(String time, int timeOffsetToUTC) {
		if (time == null)
			return null;
		try {
			LocalTime remote = LocalTime.parse(time);
			ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("Z"))
				.withHour(remote.getHour()).withMinute(remote.getMinute()).plusMinutes(timeOffsetToUTC);		
			LocalTime lt = utc.withZoneSameInstant(zone).toLocalTime();
			log.debug(time + "+" + timeOffsetToUTC + " -> " + lt.toString() + " " + zone.toString());
			return lt;
		} catch (DateTimeParseException e) {
			throw new FieldException("Wrong time format: " + e.getMessage());
		}
	}

	public static Date toDate(LocalDateTime ldt) {
		return ldt == null ? null : Date.from(ldt.atZone(zone).toInstant());
	}

}
