package vajracode.calocal.server.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.exceptions.ConflictException;
import vajracode.calocal.server.security.PasswordEncoder;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;

@Component
public class UserManager {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public UserData createUser(String login, String pass) {
		UserDTO u = userDao.getUserByName(login);
		if (u != null)
			throw new ConflictException();
		u = new UserDTO();
		u.setCreated(new Date());
		u.setName(login);
		u.setPassword(passwordEncoder.encodePassword(pass));
		u.setRole(Role.USER);
		u.setDailyCalories(1000);
		userDao.persist(u);
		return u.getUserData();
	}

}
