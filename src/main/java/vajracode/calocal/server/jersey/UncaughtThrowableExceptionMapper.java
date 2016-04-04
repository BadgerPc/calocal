package vajracode.calocal.server.jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import vajracode.calocal.shared.exceptions.ErrorData;
import vajracode.calocal.shared.exceptions.FieldException;

@Provider
public class UncaughtThrowableExceptionMapper implements ExceptionMapper<Throwable> {

	private final Logger log = Logger.getLogger(getClass());

	@Override
	public Response toResponse(Throwable e) {
		log.error("toResponse() caught exception", e);

		return Response.status(getStatusCode(e)).entity(new ErrorData(getMessage(e)))
				.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	private String getMessage(Throwable e) {
		if (e instanceof FieldException) {
			return e.getMessage();
		}
		return "Internal server error";
	}

	private int getStatusCode(Throwable e) {
		if (e instanceof WebApplicationException) {
			return ((WebApplicationException) e).getResponse().getStatus();
		}

		return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
	}

}
