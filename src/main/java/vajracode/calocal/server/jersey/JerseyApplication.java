package vajracode.calocal.server.jersey;

import java.util.logging.Level;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import vajracode.calocal.server.service.AuthServiceImpl;
import vajracode.calocal.server.service.MealServiceImpl;
import vajracode.calocal.server.service.UserServiceImpl;
import vajracode.calocal.server.utils.LogUtils;

/**
 * Jersey configuration
 *
 */
public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		LogUtils.setJerseyLoggingLevel(Level.FINEST);	    
		register(RequestContextFilter.class);
        register(AuthServiceImpl.class);
        register(MealServiceImpl.class);
        register(UserServiceImpl.class);        
        register(FieldExceptionMapper.class);
        register(UncaughtThrowableExceptionMapper.class);
	}
	 
}