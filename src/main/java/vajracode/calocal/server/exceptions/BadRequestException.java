package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import com.google.gwt.http.client.Response;

public class BadRequestException extends WebApplicationException {	
	
	public BadRequestException() {
		super(Response.SC_BAD_REQUEST);
	}	

}