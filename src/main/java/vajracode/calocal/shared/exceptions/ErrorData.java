package vajracode.calocal.shared.exceptions;

import java.io.Serializable;

public class ErrorData implements Serializable {
	
	private String error;
	
	public ErrorData() {
	}
	
	public ErrorData(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
