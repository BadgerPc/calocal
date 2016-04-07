package vajracode.calocal.server.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.exceptions.ConflictException;
import vajracode.calocal.server.exceptions.ForbiddenException;
import vajracode.calocal.server.exceptions.NotFoundException;
import vajracode.calocal.server.security.AccessManager;
import vajracode.calocal.server.security.PasswordEncoder;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;

/**
 * User resource manupulation class
 *
 */
@Component
public class UserManager {
	
	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccessManager accessManager;
	
	@Transactional
	public UserData register(String login, String pass) {		
		checkExistingName(login);		
		UserDTO user = createUser(login, pass, Role.USER);		
		return user.getUserData();
	}

	private void checkExistingName(String login) {		
		if (userDao.getUserByName(login) != null)
			throw new ConflictException("User with name " + login + " already exists");
	}

	private UserDTO createUser(String login, String pass, Role role) {
		UserDTO user = new UserDTO();
		user.setCreated(new Date());
		user.setName(login);
		user.setPassword(passwordEncoder.encodePassword(pass == null ? login + (new Random().nextInt()) : pass));
		user.setRole(role == null ? Role.USER : role);
		user.setDailyCalories(1000);
		userDao.persist(user);
		userDao.flush();
		return user;
	}

	@Transactional
	public UserData create(UserData data) {
		checkExistingName(data.getName());
		return createUser(data.getName(), data.getPassword(), data.getRole()).getUserData();
	}

	@Transactional
	public UserData update(UserData data) {
		UserDTO user = getAccessibleUser(data.getId());
		if (!accessManager.isAdmin()) {						
			data.setRole(null);			
		}
		if (data.getName() != null) {
			UserDTO conc = userDao.getUserByName(data.getName());
			if (conc != null && conc.getId() != data.getId())
				throw new ConflictException("User with name " + data.getName() + " already exists");
			user.setName(data.getName());
		}
		if (data.getPassword() != null) {
			user.setPassword(passwordEncoder.encodePassword(data.getPassword()));
		}
		if (data.getRole() != null)
			user.setRole(data.getRole());
		if (data.getDailyCalories() != null)
			user.setDailyCalories(data.getDailyCalories());
		
		userDao.flush();
		return user.getUserData();
	}

	@Transactional(readOnly = true)
	public UserData get(long id) {		
		return getAccessibleUser(id).getUserData();
	}

	private UserDTO getAccessibleUser(long id) {
		UserDTO user = userDao.getById(id);
		if (user == null)
			throw new NotFoundException();
		if (!accessManager.isAdmin() && accessManager.getLoggedInUserId() != id)
				throw new ForbiddenException();
		return user;
	}

	@Transactional
	public void delete(long id) {
		userDao.delete(getAccessibleUser(id));
	}

	@Transactional(readOnly = true)
	public UserDataList list(int offset, int limit) {					
		UserDataList ret = new UserDataList();
		ret.setOffset(offset);
		ret.setLimit(limit);
		ret.setTotal(userDao.getUserCount());				
		ret.setData(userDao.getUserList(offset, limit)
			.stream().map( it -> it.getUserData() ).collect(Collectors.toCollection(ArrayList::new)));
		log.debug("list " + ret.getData().size() + "/" + ret.getTotal());
		return ret;
	}

}
