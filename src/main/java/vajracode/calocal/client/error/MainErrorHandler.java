package vajracode.calocal.client.error;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.logging.client.SimpleRemoteLogHandler;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.StatusCodeException;

import fr.putnami.pwt.core.error.client.ErrorDisplayer.Severity;
import fr.putnami.pwt.core.error.client.ErrorHandler;
import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.service.shared.exception.CommandException;
import gwt.material.design.client.ui.MaterialToast;
import vajracode.calocal.client.elements.Header;
import vajracode.calocal.client.framework.CalocalEventBus;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.modals.WaitModal;
import vajracode.calocal.shared.exceptions.*;

public class MainErrorHandler implements ErrorHandler {

	@Inject I18nConstants msgs;
	
	private Logger logger;
	private CalocalEventBus eventBus;
	
	public MainErrorHandler() {
		logger = Logger.getLogger("critical");
		for (Handler h: logger.getHandlers())
			logger.removeHandler(h);
		logger.addHandler(new SimpleRemoteLogHandler());
	}
	
	
	@Override
	public boolean handle(Throwable e) {
		WaitModal.hide();
		
		if (e instanceof IncompatibleRemoteServiceException) {
			eventBus.setBody(new Header(msgs.updateApplication()));
			GWT.log(e.getMessage());
			return true;
		}
		
		String cause = null, message = null;
		if (e instanceof CommandException){
			CommandException ce = (CommandException)e; 
			cause = ce.getCauseClassName();
			message = ce.getCauseMessage();			
		}
		GWT.log(e + ", " + cause + ", " + message);
		if (cause != null) {
			if (cause.equals(NotAuthException.class.getCanonicalName())) {				
				eventBus.signOut();
				eventBus.authError(message);				
				return true;
			}
			if (cause.equals(NoAccessException.class.getCanonicalName())) {
				MaterialToast.alert(msgs.noAccess());
				return true;
			}		
			if (cause.equals(FieldException.class.getCanonicalName())) {
				MaterialToast.alert(message);
				return true;
			}
			if (cause.equals(ErrorMessageException.class.getCanonicalName())) {
				MaterialToast.alert(message);
				return true;
			}
		} 
		
		e = unwrap(e);		
		if (isNetworkException(e)){				
			ErrorManager.get().getErrorDisplayer().display(msgs.networkError(), e, Severity.DANGER);
		} else {
			if (!"EXCEPTION_INVOKATION".equals(message))				
				logger.log(Level.SEVERE, "Client side exception", e);
			ErrorManager.get().getErrorDisplayer().display(e, Severity.DANGER);
		}
		
		return true;
	}
	
	private boolean isNetworkException(Throwable e) {
		if (e instanceof StatusCodeException) return true;
		String msg = e.getMessage();
		if (msg == null) return false;
		return "Незавершенная строковая константа".equals(msg) || "Синтаксическая ошибка".equals(msg);
	}


	@Override
	public int getPriority() {				
		return Integer.MAX_VALUE;
	}
	
	public static Throwable unwrap(Throwable e) {
		while (e instanceof UmbrellaException) {
			UmbrellaException ue = (UmbrellaException) e;
			if (ue.getCauses().size() == 1) {
				e = unwrap(ue.getCauses().iterator().next());
			} else 
				return e;
		}
		return e;
	}


	public void setEventBus(CalocalEventBus eventBus) {
		this.eventBus = eventBus;
	}

}
