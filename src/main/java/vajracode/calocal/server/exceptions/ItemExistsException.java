package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import org.springframework.http.HttpStatus;

public class ItemExistsException extends WebApplicationException {	
	
	public ItemExistsException() {
		super(HttpStatus.CONFLICT.value());
	}	

}
