package vajracode.calocal.server.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ForbiddenException extends WebApplicationException {

    private static final long serialVersionUID = -2740045367479165061L;

    /**
     * Construct a new "forbidden" exception.
     */
    public ForbiddenException() {
        super(Response.Status.FORBIDDEN);
    }
}