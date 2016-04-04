package vajracode.calocal.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.*;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainer;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import vajracode.calocal.shared.constants.LoginFields;
import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;

public class CalocalTest extends JerseyTest {
	
	protected Logger logger = Logger.getLogger(getClass().getCanonicalName());    
	
	protected Random rand = new Random();
	protected String login = getRandomLogin();
	protected String pass = getRandomString();
	protected long userId;
	
	protected WebTarget loginTarget, mealTarget, userTarget;
	protected JerseyClient client;
	
	public CalocalTest() {
//		Level level = Level.FINEST;
//		logger.setLevel(level);			  
//	    logger.log(level, "Logging enabled");
	}
	
	private String getRandomLogin() {
		return "test" + rand.nextInt(Integer.MAX_VALUE / 2);	
	}

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
	    enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig();		
	}
	
	protected String getRandomString() {
		return "testString" + rand.nextInt(Integer.MAX_VALUE / 2);
	}
	
	@Override
	protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
		return new ExternalTestContainerFactory() {
			@Override
			public TestContainer create(URI baseUri, DeploymentContext context) throws IllegalArgumentException {
				try {
					baseUri = new URI("http://localhost:8080/");
				} catch (URISyntaxException e) {					
					e.printStackTrace();
				}
				return super.create(baseUri, context);
			}
		};
	}
	
	@Override
	protected Client getClient() {
		if (client == null) {
			ClientConfig cc = new ClientConfig();			
	        cc.connectorProvider(new ApacheConnectorProvider());
	        cc.property(ClientProperties.READ_TIMEOUT, 2000);
	        cc.property(ClientProperties.CONNECT_TIMEOUT, 500);
	        
	        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	        connectionManager.setMaxTotal(100);
	        connectionManager.setDefaultMaxPerRoute(20);	        
	        
	        cc.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
	        	      
			client = JerseyClientBuilder.createClient(cc);
			client.register(new LoggingFilter(logger, true));						
		}
		return client;
	}	

	protected void authUser() {
		assertEquals(Status.UNAUTHORIZED.getStatusCode(), 
			loginTarget.request().get().getStatus()); 
		
		UserData ud = null;
		do {
			try {
				ud = loginTarget.request().put(
					Entity.entity(new RegistrationData(login, pass), MediaType.APPLICATION_JSON_TYPE))
					.readEntity(UserData.class);
			} catch (Exception e) {
				login = getRandomString();
			}
		} while (ud == null);
		
		assertNotNull(ud);
		assertEquals(login, ud.getName());
		assertEquals(Role.USER, ud.getRole());
		assertTrue(ud.getId() > 0);
		userId = ud.getId();
		
		authUser(login, pass);
	}
	
	private void authUser(String login, String pass) {
		Form form = new Form();
		form.param(LoginFields.USERNAME, login);
		form.param(LoginFields.PASSWORD, pass);
		 
		assertEquals(Status.OK.getStatusCode(), 
			loginTarget.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE))
			.getStatus());
		
		assertEquals(login, loginTarget.request().get().readEntity(UserData.class).getName());
	}

	protected void authAdmin() {
		authUser("admin", "adminadmin");
	}
	
	protected void logOut() {
		assertEquals(Status.OK.getStatusCode(), 
				loginTarget.request().delete().getStatus()); 
	}

	protected <T> Entity<T> getEntity(T in) {
		return Entity.entity(in, MediaType.APPLICATION_JSON_TYPE);
	}

	protected void init() {    
		loginTarget = target(ResourcePaths.API_LOGIN);
		mealTarget = target(ResourcePaths.API_MEAL);
		userTarget = target(ResourcePaths.API_USER);
	}
	
	public static Date getDateBack(long millis) {
		return new Date(System.currentTimeMillis() - millis);
	}
	

}
