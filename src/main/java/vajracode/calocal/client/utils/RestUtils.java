package vajracode.calocal.client.utils;

import org.fusesource.restygwt.client.FailedStatusCodeException;

public class RestUtils {

	/**
	 * @return status code, corresponding to the RestyGWT exception or -1 otherwise 
	 */
	public static int getStatuCode(Throwable e) {
		if (e instanceof FailedStatusCodeException)
			 return ((FailedStatusCodeException) e).getStatusCode();
		return -1;
	}

}
