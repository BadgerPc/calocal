package vajracode.calocal.client.main;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.shared.model.MealData;

public class MealTable extends MealCollectionBase {
	
	public MealTable() {		
	}
	
	public MealTable(List<MealData> data) {
		this();
		apply(data);
	}

	public int getValue() {
		int sum = 0;
		for (Widget w : this) {
			if (w instanceof MealRow) {
				sum += ((MealRow) w).getData().getCal();
			}
		}
		return sum;
	}

	public int getMealsCount() {		
		return getWidgetCount();
	}
	
	
}
