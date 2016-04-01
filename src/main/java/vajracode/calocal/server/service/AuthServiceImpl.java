package vajracode.calocal.server.service;

import javax.ws.rs.InternalServerErrorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.manager.UserManager;
import vajracode.calocal.server.security.AccessManager;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private static final String LOGIN_ERROR_MSG = "Spring Security misconfiguration: login";
	private static final String LOGOUT_ERROR_MSG = "Spring Security misconfiguration: logout";

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private AccessManager principalAccessor;
	
	@Autowired
	private UserManager userManager;
	
	public AuthServiceImpl() {		
	}
	
	@Override
	public void login(String username, String password) {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.error(LOGIN_ERROR_MSG);
		throw new InternalServerErrorException(LOGIN_ERROR_MSG);
	}

	@Override
	public void logout() {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.error(LOGOUT_ERROR_MSG);
		throw new InternalServerErrorException(LOGOUT_ERROR_MSG);
	}

	@Override
	public UserData getCurrentUser() {
		return principalAccessor.getLoggedInUser();
	}

	@Override
	public UserData register(RegistrationData data) {
		FieldVerifier.checkUserName(data.getLogin());
		FieldVerifier.checkPass(data.getPass());	
		return userManager.register(data.getLogin(), data.getPass());
	}
		

}
