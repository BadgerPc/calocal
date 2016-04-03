package vajracode.calocal.server.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vajracode.calocal.server.manager.MealManager;
import vajracode.calocal.server.utils.ParamUtils;
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
		FieldVerifier.checkMealName(meal.getName());
		FieldVerifier.checkCal(meal.getCal());
	}

	@Override
	public void delete(long id) {
		mealManager.delete(id);
	}

	@Override
	public MealDataList list(long fromDate, long toDate, String fromTime, String toTime, 
			long uid, int offset, int limit) {
		LocalDateTime dFromDate = ParamUtils.getDate(fromDate);
		LocalDateTime dToDate = ParamUtils.getDate(toDate);
		LocalTime dFromTime = ParamUtils.getTime(fromTime);
		LocalTime dToTime = ParamUtils.getTime(toTime);
		FieldVerifier.checkFilterDates(dFromDate, dToDate);
		FieldVerifier.checkFilterTimes(dFromTime, dToTime);
		return mealManager.list(dFromDate, dToDate, dFromTime, dToTime, uid, offset, limit);
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
