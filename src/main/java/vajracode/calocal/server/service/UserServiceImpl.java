package vajracode.calocal.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.exceptions.ForbiddenException;
import vajracode.calocal.server.exceptions.NotFoundException;
import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserListData;
import vajracode.calocal.shared.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	
	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Override
	public UserListData listUsers(int page) {
		return null;
	}

	@Override
	public UserData getUser(long id) {
		UserData u = principalAccessor.getLoggedInUser();
		if (id == 0 || id == u.getId())
			return u;
		if (u.getRole() != Role.ADMIN) 
			throw new ForbiddenException();
		UserDTO dto = userDao.getById(id);
		if (dto == null)
			throw new NotFoundException();
		return dto.getUserData();
	}

	@Override
	public UserData createUser(String name, Role role) {
		return null;
	}

	@Override
	public UserData updateUser(long id, String name, Role role) {
		return null;
	}

	@Override
	public void deleteUser(long id) {
	}

}
