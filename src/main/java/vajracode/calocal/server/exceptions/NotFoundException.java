package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import com.google.gwt.http.client.Response;

public class NotFoundException extends WebApplicationException {	
	
	public NotFoundException() {
		super(Response.SC_NOT_FOUND);
	}	

}