package vajracode.calocal.server.service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.exceptions.ItemExistsException;
import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@Service
@Path(ResourcePaths.LOGIN)
public class AuthServiceImpl implements AuthService {

	private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
	
	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Autowired
	private UserDao userDao;
	
	public AuthServiceImpl() {		
		//Handler fh = new ConsoleHandler();
	    //Logger.getLogger("").addHandler(fh);
	    Logger l = Logger.getLogger("com.sun.jersey");
	    l.setLevel(Level.FINEST);
	    //log.info("Jersey logger tuned up");
	    //l.info("Hi, I'm Jersey");	    
	}
	
	@Override
	public void login(String username, String password) {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
	}

	@Override
	public void logout() {
		//intercepted by Spring Security
		//configured in WebSecurityConfig
	}

	@Override
	@GET
	public UserData getCurrentUser() {		
		return principalAccessor.getLoggedInUser();
	}

	@Override
	@Transactional
	public void register(RegistrationData data) {
		FieldVerifier.checkName(data.getLogin());
		FieldVerifier.checkPass(data.getPass());	
		UserDTO u = userDao.getUserByName(data.getLogin());
		if (u != null)
			throw new ItemExistsException();
		u = new UserDTO();
		u.setCreated(new Date());
		u.setName(data.getLogin());
		u.setPassword(data.getPass());
		userDao.persist(u);
	}
		

}
