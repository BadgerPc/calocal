package vajracode.calocal.server.jersey;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import vajracode.calocal.shared.exceptions.ErrorData;
import vajracode.calocal.shared.exceptions.FieldException;

@Provider
public class FieldExceptionMapper implements ExceptionMapper<FieldException> {

    @Override
    public Response toResponse(FieldException exception) {
        return Response.status(Status.BAD_REQUEST)
        		.entity(new ErrorData(exception.getMessage()))
        		.type(MediaType.APPLICATION_JSON_TYPE)
        		.build();
    }
}
