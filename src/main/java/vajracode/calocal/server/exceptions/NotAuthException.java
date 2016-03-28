package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import org.springframework.http.HttpStatus;

public class NotAuthException extends WebApplicationException {	
	
	public NotAuthException() {
		super(HttpStatus.UNAUTHORIZED.value());
	}	

}