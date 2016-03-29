package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import com.google.gwt.http.client.Response;

public class ConflictException extends WebApplicationException {	
	
	public ConflictException() {
		super(Response.SC_CONFLICT);
	}	

}
