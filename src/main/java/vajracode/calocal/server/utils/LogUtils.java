package vajracode.calocal.server.utils;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {
	
	public static void setJerseyLoggingLevel(Level level) {
		Logger l = Logger.getLogger("org.glassfish.jersey");
	    l.setLevel(level);
	    for (Handler h : l.getHandlers())
	    	h.setLevel(level);
	    l.log(level, "Jersey logging enabled");
		
	}

}
