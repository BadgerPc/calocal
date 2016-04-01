package vajracode.calocal.server.utils;

import java.util.Date;

public class ParamUtils {

	public static Date getDate(long l) {
		return l == 0 ? null : new Date(l);
	}

}
