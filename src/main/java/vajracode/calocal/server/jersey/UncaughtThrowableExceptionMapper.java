package vajracode.calocal.server.jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import vajracode.calocal.shared.exceptions.ErrorData;

@Provider
public class UncaughtThrowableExceptionMapper implements ExtendedExceptionMapper<Throwable> {

	private final Logger log = Logger.getLogger(getClass());
	
    @Override
    public boolean isMappable(Throwable throwable) {
        return !(throwable instanceof WebApplicationException);
    }

    @Override
    public Response toResponse(Throwable throwable) {
    	log.error("INTERNAL_SERVER_ERROR", throwable);
    	return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorData(throwable.getMessage())).build();
    }
}
