package vajracode.calocal.server.service;

import java.util.Date;

import javax.ws.rs.InternalServerErrorException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.exceptions.ConflictException;
import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@Service
//@Path(ResourcePaths.LOGIN)
public class AuthServiceImpl implements AuthService {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Autowired
	private UserDao userDao;
	
	public AuthServiceImpl() {		
	}
	
	@Override
	public void login(String username, String password) {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.warn("Spring Security misconfiguration: login");
		throw new InternalServerErrorException();
	}

	@Override
	public void logout() {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
		log.warn("Spring Security misconfiguration: logout");
		throw new InternalServerErrorException();
	}

	@Override
	//@GET
	public UserData getCurrentUser() {
		log.debug("getCurrentUser");
		return principalAccessor.getLoggedInUser();
	}

	@Override
	@Transactional
	public void register(RegistrationData data) {
		FieldVerifier.checkName(data.getLogin());
		FieldVerifier.checkPass(data.getPass());	
		UserDTO u = userDao.getUserByName(data.getLogin());
		if (u != null)
			throw new ConflictException();
		u = new UserDTO();
		u.setCreated(new Date());
		u.setName(data.getLogin());
		u.setPassword(data.getPass());
		userDao.persist(u);
	}
		

}
