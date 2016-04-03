package vajracode.calocal.server.jersey;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import vajracode.calocal.shared.exceptions.ErrorData;

@Provider
public class UncaughtThrowableExceptionMapper implements ExceptionMapper<Throwable> {

	private final Logger log = Logger.getLogger(getClass());
	
	 @Override
	    public Response toResponse(Throwable exception)
	    {
	        log.error("toResponse() caught exception", exception);

	        return Response.status(getStatusCode(exception))
	                .entity(new ErrorData(exception.getMessage()))
	                .type(MediaType.APPLICATION_JSON_TYPE)
	                .build();
	    }

	    /*
	     * Get appropriate HTTP status code for an exception.
	     */
	    private int getStatusCode(Throwable exception)
	    {
	        if (exception instanceof WebApplicationException)
	        {
	            return ((WebApplicationException)exception).getResponse().getStatus();
	        }

	        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	    }

	    /*
	     * Get response body for an exception.
	     */
	    private Object getEntity(Throwable exception)
	    {
	        // return stack trace for debugging (probably don't want this in prod...)
	        StringWriter errorMsg = new StringWriter();
	        exception.printStackTrace(new PrintWriter(errorMsg));
	        return errorMsg.toString();            
	    }
}
