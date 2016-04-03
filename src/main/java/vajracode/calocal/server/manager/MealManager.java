package vajracode.calocal.server.manager;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.MealDao;
import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.MealDTO;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.server.security.AccessManager;
import vajracode.calocal.server.utils.ParamUtils;
import vajracode.calocal.shared.exceptions.FieldException;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;

@Component
public class MealManager {
	
	@Autowired
	private AccessManager accessManager;
	
	@Autowired
	private MealDao mealDao;
	
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	public MealData get(long id) {		
		return getAccessibleMeal(id).getMealData();
	}

	private MealDTO getAccessibleMeal(long id) {
		MealDTO meal = mealDao.getById(id);
		if (meal == null)
			throw new NotFoundException();
		if (!accessManager.hasAccessToMeal(meal))
			throw new ForbiddenException();
		return meal;
	}

	@Transactional
	public void delete(long id) {
		mealDao.delete(getAccessibleMeal(id));
	}

	@Transactional
	public MealData update(MealData data) {
		if (data.getUserId() != accessManager.getLoggedInUserId())
			throw new FieldException("It's prohibited to change meal owner");
		MealDTO meal = getAccessibleMeal(data.getId());		
		meal.setName(data.getName());
		meal.setCal(data.getCal());
		meal.setConsumed(data.getDateTime());
		mealDao.flush();
		return meal.getMealData();
	}

	@Transactional
	public MealData create(MealData data) {
		if (data.getUserId() == 0)
			data.setUserId(accessManager.getLoggedInUserId());
		else if (data.getUserId() != accessManager.getLoggedInUserId() && !accessManager.isAdmin())
			throw new FieldException("You can't add the meal for another user");
		
		UserDTO user = userDao.getById(data.getUserId());
		if (user == null)
			throw new FieldException("No such user");
		
		MealDTO meal = new MealDTO();
		meal.setName(data.getName());
		meal.setUser(user);
		meal.setConsumed(data.getDateTime() == null ? new Date() : data.getDateTime());
		meal.setCal(data.getCal());
		mealDao.persist(meal);
		mealDao.flush();
		return meal.getMealData();
	}

	@Transactional(readOnly = true)
	public MealDataList list(LocalDateTime fromDate, LocalDateTime toDate, LocalTime fromTime, LocalTime toTime, 
			long uid, int offset, int limit) {
		if (uid == 0 || (uid != accessManager.getLoggedInUserId() && !accessManager.isAdmin()))
			uid = accessManager.getLoggedInUserId();
		
		UserDTO user = userDao.getReference(uid);
		
		MealDataList ret = new MealDataList();
		ret.setOffset(offset);
		ret.setLimit(limit);
		ret.setDateFrom(ParamUtils.toDate(fromDate));
		ret.setDateTo(ParamUtils.toDate(toDate));		
		ret.setTotal(mealDao.getListSize(fromDate, toDate, fromTime, toTime, user));		
		ret.setData(mealDao.getList(fromDate, toDate, fromTime, toTime, user, offset, limit)
			.stream().map( it -> it.getMealData() ).collect(Collectors.toCollection(ArrayList::new)));	
		
		return ret;
	}


}
