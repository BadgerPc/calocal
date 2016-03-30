package vajracode.calocal.server.service;

import javax.ws.rs.InternalServerErrorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Autowired
	private UserManager userManager;
	
	public AuthServiceImpl() {		
	}
	
	@Override
	public void login(String username, String password) {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.error("Spring Security misconfiguration: login");
		throw new InternalServerErrorException();
	}

	@Override
	public void logout() {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.error("Spring Security misconfiguration: logout");
		throw new InternalServerErrorException();
	}

	@Override
	public UserData getCurrentUser() {
		return principalAccessor.getLoggedInUser();
	}

	@Override
	public void register(RegistrationData data) {
		FieldVerifier.checkName(data.getLogin());
		FieldVerifier.checkPass(data.getPass());	
		userManager.createUser(data.getLogin(), data.getPass());
	}
		

}
