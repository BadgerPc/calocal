package vajracode.calocal.client.error;

import org.fusesource.restygwt.client.FailedStatusCodeException;

import vajracode.calocal.shared.exceptions.ErrorData;

public class ServerException extends FailedStatusCodeException {

	private ErrorData errorData;
	
	public ServerException(String message, int statusCode, ErrorData errorData) {
		super(message, statusCode);
		this.errorData = errorData;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

}
