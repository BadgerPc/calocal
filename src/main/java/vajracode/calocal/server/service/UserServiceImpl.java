package vajracode.calocal.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.manager.UserManager;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;
import vajracode.calocal.shared.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserManager userManager;

		
	@Override
	public UserData create(UserData data) {	
		check(data);
		return userManager.create(data);
	}

	@Override
	public UserDataList list(int offset, int limit) {
		return userManager.list(offset, limit);
	}

	@Override
	public UserData get(long id) {
		return userManager.get(id);		
	}

	@Override
	public UserData update(long id, UserData data) {
		check(data);
		FieldVerifier.checkIdsEqual(id, data.getId());
		return userManager.update(data);				
	}

	@Override
	public void delete(long id) {
		userManager.delete(id);
	}
		
	private void check(UserData data) {
		FieldVerifier.checkUserName(data.getName());
		FieldVerifier.checkCal(data.getDailyCalories());		
	}

}
