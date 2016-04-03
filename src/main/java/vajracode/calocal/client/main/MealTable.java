package vajracode.calocal.client.main;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.model.MealData;

public class MealTable extends MealCollectionBase {
	
	@Inject Provider<MealRow> mealRowProvider;
	@Inject I18nConstants msgs;
	
	
	
	public MealTable() {
		setWidth("100%");		
	}
	
	public MealTable(List<MealData> data) {
		this();
		apply(data);
	}

	public MealTable apply(List<MealData> data) {
		for (MealData m : data) {
			addToBegining(m);
		}
		return this;
	}

	public MealRow addToBegining(MealData meal) {
		MealRow r = getMealRow(meal);
		insert(r, 0);		
		update();
		return r;
	}
	
	public MealRow addToEnd(MealData meal) {
		MealRow r = getMealRow(meal);
		add(r);
		update();
		return r;
	}

	private MealRow getMealRow(MealData meal) {
		MealRow r = mealRowProvider.get().apply(meal);
		r.setMealTable(this);
		return r;
	}

	public void addAndEdit(MealData meal) {
		MealRow r = addToEnd(meal);
		r.edit();
		r.focusOnCal();
	}

	public void update() {
		ValueChangeEvent.fire(this, getValue());		
	}

	public int getValue() {
		int sum = 0;
		for (Widget w : this) {
			if (w instanceof MealRow) {
				sum += ((MealRow) w).getMeal().getCal();
			}
		}
		return sum;
	}

	public int getMealsCount() {		
		return getWidgetCount();
	}
	
	
}
