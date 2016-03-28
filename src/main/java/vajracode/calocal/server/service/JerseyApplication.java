package vajracode.calocal.server.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
        register(JerseyResource.class);
        register(SpringSingletonResource.class);
        register(SpringRequestResource.class);
        register(CustomExceptionMapper.class);
	}
	 
}
