package vajracode.calocal.client.main;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.shared.constants.DateConstants;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;
import vajracode.calocal.shared.service.MealService;


@Presenter(view = MainView.class)
public class MainPresenter extends CommonPresenter<MainView> {
		
	@InjectService MealService service;
	
	private DateTimeFormat pureDate = DateTimeFormat.getFormat("d-M-yyyy");
	
	public void onMain(){					
		Date now = new Date();							
		list(true, now, now, null, null);			
	}
	
	private void list(boolean today, Date fromDate, Date toDate, Date fromTime, Date toTime) {
		view.reset();
		view.setTodayMode(today);
		fromDate = pureDate.parse(pureDate.format(fromDate));
		toDate = pureDate.parse(pureDate.format(new Date(toDate.getTime() + DateConstants.MILLIS_DAY + 1)));	
		service.list(fromDate.getTime(), toDate.getTime(), 
				fromTime == null ? 0 : fromTime.getTime(), toTime == null ? 0 : toTime.getTime(), 
				0, 0, Integer.MAX_VALUE);
		eventBus.setBody(view);
	}

	public void onFilter(Date fromDate, Date toDate, Date fromTime, Date toTime) {
		list(false, fromDate, toDate, fromTime, toTime);
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
