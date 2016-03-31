package vajracode.calocal.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.manager.UserManager;
import vajracode.calocal.server.security.AccessManager;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;
import vajracode.calocal.shared.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private AccessManager principalAccessor;
		
	@Override
	public UserData create(UserData data) {	
		return null;
	}

	@Override
	public UserDataList list(long offset, int limit) {
		return null;
	}

	@Override
	public UserData get(long id) {
		/*UserData u = principalAccessor.getLoggedInUser();
		if (id == 0 || id == u.getId())
			return u;
		if (u.getRole() != Role.ADMIN) 
			throw new ForbiddenException();
		UserDTO dto = userDao.getById(id);
		if (dto == null)
			throw new NotFoundException();
		return dto.getUserData();*/
		return null;
	}

	@Override
	public UserData update(long id, UserData user) {
		return null;
	}

	@Override
	public void delete(long id) {
	}
		


}
