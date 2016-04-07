package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

    public NotFoundException() {
        super(Response.Status.NOT_FOUND);
    }
}