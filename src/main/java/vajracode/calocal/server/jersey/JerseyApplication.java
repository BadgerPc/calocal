package vajracode.calocal.server.jersey;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import vajracode.calocal.server.service.AuthServiceImpl;
import vajracode.calocal.server.service.UserServiceImpl;

public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		Logger l = Logger.getLogger("org.glassfish.jersey");
	    l.setLevel(Level.FINEST);
	    l.info("Initializing JerseyApplication");
		register(RequestContextFilter.class);
		//register(JacksonFeature.class);
        register(AuthServiceImpl.class);
        register(UserServiceImpl.class);        
        //register(CustomExceptionMapper.class);
	}
	 
}
