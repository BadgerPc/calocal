package vajracode.calocal.shared.exceptions;

/**
 * User input validation error. 
 * Can be thrown on server-side, then transforms to BAD_REQUEST with corresponding description.
 * Can be thrown on client-side, then just shown to the user.
 * 
 * @see vajracode.calocal.server.jersey.FieldExceptionMapper
 *
 */
public class FieldException extends RuntimeException {

	public FieldException(String message) {
		super(message);
	}
	
	

}
