package vajracode.calocal.server.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.exceptions.ItemExistsException;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Autowired
	private UserDao userDao;
	
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
	public UserData getCurrentUser() {		
		return principalAccessor.getLoggedInUser();
	}

	@Override
	@Transactional
	public void register(String login, String pass, String passConfirm) {
		FieldVerifier.checkName(login);
		FieldVerifier.checkPass(pass);
		FieldVerifier.checkPassEqual(pass, passConfirm);
		UserDTO u = userDao.getUserByName(login);
		if (u != null)
			throw new ItemExistsException();
		u = new UserDTO();
		u.setCreated(new Date());
		u.setName(login);
		u.setPassword(pass);
		userDao.persist(u);
	}

}
