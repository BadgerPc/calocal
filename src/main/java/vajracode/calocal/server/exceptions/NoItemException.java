package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import org.springframework.http.HttpStatus;

public class NoItemException extends WebApplicationException {	
	
	public NoItemException() {
		super(HttpStatus.NO_CONTENT.value());
	}	

}