package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import com.google.gwt.http.client.Response;

public class ForbiddenException extends WebApplicationException {	
	
	public ForbiddenException() {
		super(Response.SC_FORBIDDEN);
	}	

}
