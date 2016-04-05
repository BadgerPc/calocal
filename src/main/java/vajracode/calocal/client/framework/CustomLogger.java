package vajracode.calocal.client.framework;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.logging.client.SystemLogHandler;
import com.mvp4g.client.event.Mvp4gLogger;

/**
 * MVP4G has some useful debug logging, but it contins a lot of extra text. This logger cleans it out.
 *
 */
public class CustomLogger implements Mvp4gLogger {

	private static final String PREFIX = "Module: Mvp4gModule || ";
	private Logger log;
	
	public CustomLogger() {
		//if (GWT.isProdMode()) return;
		Logger.getLogger("").addHandler(new SystemLogHandler() {
			@Override
			public Formatter getFormatter() {
				return getLogFormatter();
			}
		});
		Logger.getLogger("").addHandler(new ConsoleLogHandler() {
			@Override
			public Formatter getFormatter() {
				return getLogFormatter();
			}
		});
		log = Logger.getLogger("vajracode.calocal.client.framework.CustomLogger");		
	}
	
    public void log( String message, int depth ) {
    	//if (GWT.isProdMode()) return;
    	if (message.contains("event: setBody"))
    		return;
    	if (message.startsWith(PREFIX))
    		message = message.substring(PREFIX.length(), message.length());
    		
    	String indent = "";
        for ( int i = 0; i < depth; ++i )
                indent += "    ";
        log.info(indent + message);
    }

    protected Formatter getLogFormatter() {
		return new Formatter() {
			@Override
			public String format(LogRecord lr) {
				String ret = lr.getMessage();
//				if (lr.getThrown() != null)
//					ret += CommonConstants.BRN + ExceptionHandler.getExceptionTrace(lr.getThrown());
				return ret;
			}
		};
	}
}
