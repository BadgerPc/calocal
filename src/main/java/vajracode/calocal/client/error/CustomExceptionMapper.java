package vajracode.calocal.client.error;

import org.fusesource.restygwt.client.ExceptionMapper;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;

import vajracode.calocal.shared.exceptions.ErrorData;

/**
 * Extends RestyGWT exception mapper to add {@link vajracode.calocal.shared.exceptions.ErrorData} 
 * to server-generated errors.
 *
 */
public class CustomExceptionMapper extends ExceptionMapper {
	
	public interface ErrorDataCodec extends JsonEncoderDecoder<ErrorData> {}
	private ErrorDataCodec codec = GWT.create(ErrorDataCodec.class); 
	
	@Override
	public Throwable createFailedStatusException(Method method, Response response) {
		ErrorData data = getErrorData(response);		
		return new ServerException(response.getStatusText(), response.getStatusCode(), data);		
	}

	private ErrorData getErrorData(Response response) {		
		try {
            return codec.decode(JSONParser.parseStrict(response.getText()));
        } catch (Throwable e) {
        	GWT.log("No error data: " + (response.getText() == null ? null : response.getText()), e);
            return null;
        }
	}

}
