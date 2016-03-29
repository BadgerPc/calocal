package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;

import com.google.gwt.http.client.Response;

public class UnauthorizedException extends WebApplicationException {	
	
	public UnauthorizedException() {
		super(Response.SC_UNAUTHORIZED);
	}	

}