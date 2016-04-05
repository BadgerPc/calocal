package vajracode.calocal.client.main;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.constants.DateConstants;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;
import vajracode.calocal.shared.service.MealService;


/**
 * Main application page's presenter
 *
 */
@Presenter(view = MainView.class)
public class MainPresenter extends CommonPresenter<MainView> {
		
	@InjectService MealService service;
	
	private DateTimeFormat pureDate = DateTimeFormat.getFormat("d-M-yyyy");
	
	private Date fromDatePrev, toDatePrev;
	private String fromTimePrev, toTimePrev;
	
	public void onMain(){					
		fromDatePrev = null;
		toDatePrev = null;
		fromTimePrev = null;
		toTimePrev = null;
		Date now = new Date();
		list(true, now, now, null, null);			
	}
	
	private void list(boolean today, Date fromDatePrev, Date toDatePrev, String fromTimePrev, String toTimePrev) {
		view.reset();
		view.setTodayMode(today);
		if (fromDatePrev != null)
			fromDatePrev = pureDate.parse(pureDate.format(fromDatePrev));
		if (toDatePrev != null)
			toDatePrev = pureDate.parse(pureDate.format(new Date(toDatePrev.getTime() + DateConstants.MILLIS_DAY)));	
		service.list(fromDatePrev != null ? fromDatePrev.getTime() : 0, 
				toDatePrev != null ? toDatePrev.getTime() : 0, 
				DateUtils.uiToTime(fromTimePrev), DateUtils.uiToTime(toTimePrev), 
				DateUtils.getTimeZoneOffset(), 0, 0, Integer.MAX_VALUE);
		eventBus.setBody(view);
	}

	public void onFilter(Date fromDate, Date toDate, String fromTime, String toTime) {
		if (fromDate != null)
			fromDatePrev = fromDate;
		if (toDate != null)
			toDatePrev = toDate;
		if (fromTime != null)
			fromTimePrev = fromTime;
		if (toTime != null)
			toTimePrev = toTime;
		list(false, fromDatePrev, toDatePrev, fromTimePrev, toTimePrev);
	}
	
	@AsyncHandler
	public void onList(MealDataList list){
		view.apply(list);		
	}

	public void addMeal(String name) {
		MealData meal = new MealData();
		meal.setName(name);
		service.create(meal);
	}
	
	@AsyncHandler
	public void onCreate(MealData meal){
		view.addMeal(meal);
	}
	
	@AsyncHandler
	public void onCreateThrowable(Throwable t){
		view.onAddMealFailed();
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}
		
}
