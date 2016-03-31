package vajracode.calocal.client.main;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialTextBox;
import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.Header;
import vajracode.calocal.client.framework.CommonView;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;


public class MainView extends CommonView<MainPresenter> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, MainView> {}	
	
	@Inject I18nConstants msgs;
	@Inject Provider<MealTable> mealTableProvider;
	
	@UiField Header dateLabel, timeLabel, percents;
	@UiField HTML summary;
	@UiField FlowPanel today, content;
	@UiField MaterialTextBox mealName;	
	@UiField Button addMeal;

	private MealTable todayMeals;

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));		
	}	
		
	public void reset() {
		dateLabel.setText(msgs.today());
		timeLabel.setText(msgs.allTime());
		content.clear();
		mealName.clear();
		percents.setText("");
		addMeal.setDisable(false);
		todayMeals = null;
		setTodayMode(true);
	}

	public void apply(MealDataList list) {
		if (isTodayMode())
			applyTodayData(list);
		else
			applyFilteredData(list);
	}

	private void applyFilteredData(MealDataList list) {
	}

	private void applyTodayData(MealDataList list) {	
		if (list.getData().size() > 0)
			content.add(todayMeals = mealTableProvider.get().apply(list.getData()));
		else {
			Header h = new Header(msgs.todayWelcome());
			h.addStyleName(Resources.INSTANCE.css().padding32());
			content.add(h);
		}
	}

	private boolean isTodayMode() {
		return today.isVisible();
	}

	public void setTodayMode(boolean b) {
		today.setVisible(b);
	}
	
	@UiHandler("addMeal")
	public void addMealHandler(ClickEvent event) {
		addMeal.setDisable(true);
		getPresenter().addMeal(mealName.getText());	
	}

	public void addMeal(MealData meal) {
		if (todayMeals == null)
			content.add(todayMeals = mealTableProvider.get());
		todayMeals.addAndEdit(meal);
		addMeal.setDisable(false);
	}

	public void onAddMealFailed() {
		addMeal.setDisable(false);
	}
	
	
}
