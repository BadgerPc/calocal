package vajracode.calocal.server.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.dto.MealDTO;
import vajracode.calocal.server.manager.MealManager;
import vajracode.calocal.server.security.PrincipalAccessor;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;
import vajracode.calocal.shared.service.MealService;

@Service
public class MealServiceImpl implements MealService {

	@Autowired
	private PrincipalAccessor principalAccessor;
	
	@Autowired
	private MealManager mealManager;
	
	@Override
	public MealData create(MealData meal) {
		return null;
	}

	@Override
	public void delete(long id) {
		MealDTO meal = mealManager.get(id);
	}

	@Override
	public MealDataList list(Date fromDate, Date toDate, Date fromTime, Date toTime, long offset, int limit) {
		return null;
	}

	@Override
	public MealData get(long id) {
		return null;
	}

	@Override
	public MealData update(long id, MealData meal) {
		return null;
	}

}
