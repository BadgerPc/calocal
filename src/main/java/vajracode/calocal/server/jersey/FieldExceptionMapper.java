package vajracode.calocal.server.jersey;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import vajracode.calocal.shared.exceptions.ErrorData;
import vajracode.calocal.shared.exceptions.FieldException;

/**
 * Exception mapper to convert {@link IllegalArgumentException} into a textual response.
 *
 * @author Marko Asplund (marko.asplund at yahoo.com)
 */
@Provider
public class FieldExceptionMapper implements ExceptionMapper<FieldException> {

    @Override
    public Response toResponse(FieldException exception) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorData(exception.getMessage())).build();
    }
}
