package vajracode.calocal.client.main;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.client.auth.UserManager;
import vajracode.calocal.client.elements.ColFlexPanel;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.UserData;

public class MealTable extends ColFlexPanel implements HasValueChangeHandlers<Integer> {
	
	@Inject Provider<MealRow> mealRowProvider;
	@Inject UserManager userManager;
	@Inject I18nConstants msgs;
	
	private HTML summary;
	private HandlerRegistration hr;
	
	public MealTable() {
		setWidth("100%");		
	}
	
	public MealTable(List<MealData> data) {
		this();
		apply(data);
	}

	public MealTable apply(List<MealData> data) {
		for (int i = data.size() - 1; i >= 0; i--) {
			add(data.get(i));
		}
		return this;
	}

	public MealRow add(MealData meal) {
		MealRow r = mealRowProvider.get().apply(meal);
		r.setMealTable(this);
		add(r);
		update();
		return r;
	}

	public void addAndEdit(MealData meal) {
		MealRow r = add(meal);
		r.edit();
		r.focusOnCal();
	}

	public void setSummary(HTML summary) {
		this.summary = summary;
		update();
	}

	public void update() {
		ValueChangeEvent.fire(this, getCalSum());		
	}

	public int getCalSum() {
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
	
	@Override
	protected void onLoad() {
		super.onLoad();
		hr = userManager.addValueChangeHandler(new ValueChangeHandler<UserData>() {			
			@Override
			public void onValueChange(ValueChangeEvent<UserData> event) {
				update();
			}
		});
	}

	@Override
	protected void onUnload() {
		super.onUnload();
		if (hr != null)
			hr.removeHandler();
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
}
