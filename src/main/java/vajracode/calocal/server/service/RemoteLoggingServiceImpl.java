package vajracode.calocal.server.service;

import java.util.Enumeration;
import java.util.logging.LogRecord;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gwt.core.server.StackTraceDeobfuscator;
import com.google.gwt.logging.server.RemoteLoggingServiceUtil;
import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.putnami.pwt.core.service.shared.exception.CommandException;

/**
 * GWT remote logger backend  
 *
 */
public class RemoteLoggingServiceImpl extends RemoteServiceServlet implements RemoteLoggingService {
	
	private final Logger log = Logger.getLogger(RemoteLoggingServiceImpl.class);
	
	private StackTraceDeobfuscator deobfuscator;	
	
	public final String logOnServer(LogRecord record) {
		String strongName = getPermutationStrongName();			
		try {
			record = RemoteLoggingServiceUtil.deobfuscateLogRecord(getDeobfuscator(), record, strongName);
			String header = /*"Client side exception\n" + */record.getMessage() + "\n" + getRequestIP()
				+ "\n" + strongName;
			Throwable t = record.getThrown();
			if (t instanceof CommandException) {
				CommandException e = (CommandException) t;
				header += "\nCause\n " + e.getCauseClassName() + ": " + e.getCauseMessage() + "\n" 
					+ e.getCauseStackTrace() + "\n\n";
			}
			log.error(header, t);
		} catch (Exception e) {
			log.error("", e);
		}		
		
		return null;
	}

	private StackTraceDeobfuscator getDeobfuscator() {
		if (deobfuscator == null) {
			String path = getInitParameter("pathToSymbolMaps");			
			deobfuscator = StackTraceDeobfuscator.fromFileSystem(path);			
			log.warn("loading deobfuscator for " + path + ": " + deobfuscator);
		}
		return deobfuscator;
	}

	protected String getRequestIP() {	
		HttpServletRequest req = getThreadLocalRequest();
		
		Enumeration<String> headers = req.getHeaders("X-Forwarded-For");
		if (headers == null) return req.getRemoteAddr();
		
		while (headers.hasMoreElements()) {
            final String[] ips = headers.nextElement().split(",");
            for (int i = 0; i < ips.length; i++) {
                final String proxy = ips[i].trim();
                if (!"unknown".equals(proxy) && !proxy.isEmpty()) {                    
                	return proxy;                    
                }
            }
        }		
		return req.getRemoteAddr();
	}
	
}
