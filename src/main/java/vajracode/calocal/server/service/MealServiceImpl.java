package vajracode.calocal.server.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.manager.MealManager;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;
import vajracode.calocal.shared.service.MealService;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	private MealManager mealManager;
	
	@Override
	public MealData create(MealData meal) {
		check(meal);
		return mealManager.create(meal);
	}

	private void check(MealData meal) {
		FieldVerifier.checkName(meal.getName());
		FieldVerifier.checkCal(meal.getCal());
	}

	@Override
	public void delete(long id) {
		mealManager.delete(id);
	}

	@Override
	public MealDataList list(Date fromDate, Date toDate, Date fromTime, Date toTime, 
			long uid,  long offset, int limit) {		
		return mealManager.list(fromDate, toDate, fromTime, toTime, uid, offset, limit);
	}

	@Override
	public MealData get(long id) {		
		return mealManager.get(id);		
	}
	
	@Override
	public MealData update(long id, MealData meal) {
		check(meal);
		FieldVerifier.checkIdsEqual(id, meal.getId());
		return mealManager.update(meal);
	}

}
