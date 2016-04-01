package vajracode.calocal.client.main;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialTextBox;
import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.CalSummary;
import vajracode.calocal.client.elements.Header;
import vajracode.calocal.client.framework.CommonView;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.modals.EditDailyCaloriesModal;
import vajracode.calocal.client.modals.FilterDateModal;
import vajracode.calocal.client.modals.FilterTimeModal;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.model.MealDataList;


public class MainView extends CommonView<MainPresenter> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, MainView> {}	
	
	@Inject I18nConstants msgs;
	@Inject Provider<MealTable> mealTableProvider;
	@Inject Provider<MealArray> allMealsProvider;
	@Inject Provider<EditDailyCaloriesModal> editDailyCaloriesModalProvider;
	@Inject Provider<FilterDateModal> filterDateModalProvider;
	@Inject Provider<FilterTimeModal> filterTimeModalProvider;
		
	@UiField Header dateLabel, timeLabel, percents;
	@UiField(provided = true) @Inject CalSummary summary;
	@UiField FlowPanel today, content;
	@UiField MaterialTextBox mealName;	
	@UiField Button addMeal;

	private MealTable todayMeals;
	private MealArray allMeals;
	private Header welcome;
	private Date fromDate, toDate, fromTime, toTime;

	public MainView() {		
	}	
		
	public void reset() {
		init();
		welcome = new Header();
		welcome.addStyleName(Resources.INSTANCE.css().padding32());
		dateLabel.setText(msgs.today());
		timeLabel.setText(msgs.allTime());
		content.clear();
		mealName.clear();
		percents.setText("");
		addMeal.setEnabled(true);
		todayMeals = null;		
	}

	private void init() {
		if (getWidget() == null)
			initWidget(uiBinder.createAndBindUi(this));
	}

	public void apply(MealDataList list) {
		if (isTodayMode())
			applyTodayData(list);
		else
			applyFilteredData(list);
	}

	private void applyFilteredData(MealDataList list) {
		content.add(getAllMeals().apply(list.getData()));
		updateWelcome(list.getData().size() > 0, msgs.noData());
	}

	private void applyTodayData(MealDataList list) {
		content.add(getTodayMeals().apply(list.getData()));
		updateWelcome(list.getData().size() > 0, msgs.todayWelcome());
	}

	private void updateWelcome(boolean show, String msg) {
		if (show) {
			welcome.setText(msg);
			content.add(welcome);
		} else {
			welcome.removeFromParent();
		}
	}

	private MealTable getTodayMeals() {
		if (todayMeals == null) {
			todayMeals = mealTableProvider.get();		
			todayMeals.addValueChangeHandler(new ValueChangeHandler<Integer>() {				
				@Override
				public void onValueChange(ValueChangeEvent<Integer> event) {
					summary.update(event.getValue());
				}
			});
		}
		return todayMeals;
	}
	
	private MealArray getAllMeals() {
		if (allMeals == null) {
			allMeals = allMealsProvider.get();					
		}
		return allMeals;
	}

	private boolean isTodayMode() {
		return today.isVisible();
	}

	public void setTodayMode(boolean b) {
		today.setVisible(b);
	}
	
	@UiHandler("addMeal")
	public void addMealHandler(ClickEvent event) {
		addMeal.setEnabled(false);
		getPresenter().addMeal(mealName.getText());	
	}

	@UiHandler("mealName")
	public void addMealNameHandler(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && addMeal.isEnabled())
			addMealHandler(null);
	}
	
	public void addMeal(MealData meal) {		
		content.add(getTodayMeals());
		todayMeals.addAndEdit(meal);
		addMeal.setEnabled(true);
		updateWelcome(todayMeals.getMealsCount() > 0, msgs.todayWelcome());
	}

	public void onAddMealFailed() {
		addMeal.setEnabled(true);
	}
	
	@UiHandler("summaryButton")
	public void summaryButtonHandler(ClickEvent event) {
		editDailyCaloriesModalProvider.get().show();
	}
	
	@UiHandler("dateButton")
	public void dateButtonHandler(ClickEvent event) {
		filterDateModalProvider.get().init(fromDate, toDate).show();
	}
	
	@UiHandler("timeButton")
	public void timeButtonHandler(ClickEvent event) {
		filterTimeModalProvider.get().init(fromTime, toTime).show();
	}
}
