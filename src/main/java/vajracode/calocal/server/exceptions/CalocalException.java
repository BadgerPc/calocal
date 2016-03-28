package vajracode.calocal.server.exceptions;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CalocalException extends RuntimeException implements IsSerializable {

	public CalocalException() {
	}

	public CalocalException(String message) {
		super(message);
	}
	
	
	
}
