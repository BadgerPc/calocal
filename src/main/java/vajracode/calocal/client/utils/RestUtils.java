package vajracode.calocal.client.utils;

import org.fusesource.restygwt.client.FailedStatusCodeException;

public class RestUtils {

	public static int getStatuCode(Throwable e) {
		if (e instanceof FailedStatusCodeException)
			 return ((FailedStatusCodeException) e).getStatusCode();
		return -1;
	}

}
