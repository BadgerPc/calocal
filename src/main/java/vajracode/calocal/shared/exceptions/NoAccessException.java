package vajracode.calocal.shared.exceptions;

import javax.ws.rs.WebApplicationException;

import org.springframework.http.HttpStatus;

public class NoAccessException extends WebApplicationException {	
	
	public NoAccessException() {
		super(HttpStatus.FORBIDDEN.value());
	}	

}
