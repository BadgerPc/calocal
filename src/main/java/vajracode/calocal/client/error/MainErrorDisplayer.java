package vajracode.calocal.client.error;

import com.google.gwt.logging.impl.StackTracePrintStream;

import fr.putnami.pwt.core.error.client.ErrorDisplayer;
import fr.putnami.pwt.core.service.shared.exception.CommandException;
import vajracode.calocal.client.modals.ModalUtils;
import vajracode.calocal.shared.constants.HTMLConstants;

public class MainErrorDisplayer implements ErrorDisplayer {
	
	public void display(String title, String message, String details, Severity severity) {
		ModalUtils.show(title, message + HTMLConstants.BR + details);
	}

	public void display(Throwable error, Severity severity) {
		display(error.getMessage(), "", getDetailString(error), severity);
	}

	public void display(String message, Throwable error, Severity severity) {
		display(error.getMessage(), message, getDetailString(error), severity);
	}

	public void display(String title, String message, Throwable error, Severity severity) {
		display(title, message, getDetailString(error), severity);
	}
	

	private String getDetailString(Throwable error) {
		StringBuilder sb = new StringBuilder();
		if (error instanceof CommandException) {
			sb.append(((CommandException) error).getCauseMessage()).append(" : \n");
			sb.append(((CommandException) error).getCauseStackTrace());
		} else {
			sb.append(error.getMessage()).append(" : \n");
			error.printStackTrace(new StackTracePrintStream(sb));
		}
		return sb.toString();
	}

}
