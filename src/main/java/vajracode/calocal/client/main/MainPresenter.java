package vajracode.calocal.client.main;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;
import vajracode.calocal.shared.service.MealService;


@Presenter(view = MainView.class)
public class MainPresenter extends CommonPresenter<MainView> {
		
	@InjectService MealService service;
	
	private DateTimeFormat pureDate = DateTimeFormat.getFormat("d-M-yyyy");
	
	public void onMain(){		
		view.reset();		
		Date now = new Date();
		Date plusDay = new Date(now.getTime() + 86400001);		
		Date fromDate = pureDate.parse(pureDate.format(now));
		Date toDate = pureDate.parse(pureDate.format(plusDay));
		service.list(fromDate.getTime(), toDate.getTime(), 0, 0, 0, 0, Integer.MAX_VALUE);
		eventBus.setBody(view);		
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
