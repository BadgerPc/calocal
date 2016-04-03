package vajracode.calocal.client.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import vajracode.calocal.client.elements.ColFlexPanel;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.model.MealData;

public class MealArray extends ColFlexPanel {

	@Inject Provider<DayMealPanel> panelProvider;
	
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
			}
			p.add(meal);
		}
		return this;
	}

	public int getCalAvg() {
		return panels.size() == 0 ? 0 : getCalSum() / panels.size();
	}

	public int getCalSum() {
		int sum = 0;
		for (DayMealPanel p : panels.values())
			sum += p.getCalSum();
		return sum;
	}

}
