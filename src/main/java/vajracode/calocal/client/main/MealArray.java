package vajracode.calocal.client.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.model.MealData;

/**
 * Widget, collection of meal panels for several days 
 * @see vajracode.calocal.client.main.DayMealPanel
 */
public class MealArray extends MealCollectionBase {

	@Inject private Provider<DayMealPanel> panelProvider;
	
	private Map<String, DayMealPanel> panels = new HashMap<>();
	
	public MealArray() {
		setWidth("100%");
	}
	
	public MealArray apply(List<MealData> data) {
		for (MealData meal : data) {
			String date = DateUtils.dateYear.format(meal.getDateTime());
			DayMealPanel p = panels.get(date);
			if (p == null) {
				p = panelProvider.get().apply(meal.getDateTime());
				panels.put(date, p);
				add(p);
				p.getMealTable().addValueChangeHandler(new ValueChangeHandler<Integer>() {					
					@Override
					public void onValueChange(ValueChangeEvent<Integer> event) {
						update();
					}
				});
			}
			p.add(meal);
		}
		return this;
	}

	public int getValue() {
		return panels.size() == 0 ? 0 : getCalSum() / panels.size();
	}

	public int getCalSum() {
		int sum = 0;
		for (DayMealPanel p : panels.values())
			sum += p.getCalSum();
		return sum;
	}

}
