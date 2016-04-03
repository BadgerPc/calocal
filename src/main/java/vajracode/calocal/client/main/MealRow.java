package vajracode.calocal.client.main;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.Injected;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import gwt.material.design.client.constants.IconType;
import vajracode.calocal.client.elements.*;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.service.MealService;

public class MealRow extends CenteredRowFlexPanel implements Injected {

	@InjectService MealService service;
	
	private MealData meal;
	private TextBox tbDate, tbName, tbCal;
	private Button bFinish, bDelete;
	private MealTable mealTable;
	
	public MealRow() {
		addStyleName(Resources.INSTANCE.css().justifyContentSpace());
	}
	
	public MealRow(MealData meal) {
		this();
		apply(meal);
	}
	
	private void update() {
		clear();
		add(getWidget(getTimeText()));
		add(getWidget(meal.getName()));
		add(getWidget(String.valueOf(meal.getCal())));
		add(getEditButton());
	}

	private String getTimeText() {
		return DateUtils.timeUi.format(meal.getDateTime());
	}

	private Widget getEditButton() {
		return getIcoButton(IconType.MODE_EDIT, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				edit();
			}
		});
	}

	private Button getIcoButton(IconType icon, ClickHandler clickHandler) {		
		Button b = new FlatButton();		
		b.setIconType(icon);
		b.addClickHandler(clickHandler);
		return b;
	}

	private Widget getWidget(String text) {
		return new Body1(text);
	}

	public void edit() {
		clear();
		add(tbDate = getDateEditField());
		add(tbName = getNameEditField());
		add(tbCal = getCalEditField());
		add(bFinish = getFinishButton());
		add(bDelete = getDeleteButton());
	}
	
	private Button getDeleteButton() {
		return getIcoButton(IconType.DELETE, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bDelete.setEnabled(false);
				service.delete(meal.getId());
			}
		});
	}
	
	private Button getFinishButton() {
		return getIcoButton(IconType.DONE, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bFinish.setEnabled(false);
				done();
			}
		});
	}

	protected void done() {
		service.update(meal.getId(), meal);
	}
	
	@AsyncHandler
	public void onUpdate(MealData data) {
		meal = data;
		update();
		mealTable.update();
	}
	
	@AsyncHandler
	public void onUpdateThrowable(Throwable t){
		bFinish.setEnabled(true);
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}
	
	@AsyncHandler
	public void onDelete(Void v) {
		removeFromParent();
		mealTable.update();
	}
	
	@AsyncHandler
	public void onDeleteThrowable(Throwable t){
		bDelete.setEnabled(true);
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}

	private TextBox getDateEditField() {		
		return new TextBox(getTimeText(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					String date = DateUtils.date.format(meal.getDateTime());
					meal.setDateTime(DateUtils.full.parse(event.getValue() + " - " + date));
				} catch (IllegalArgumentException e) {
					tbDate.setText(getTimeText());
				}
			}
		});
	}
	
	private TextBox getNameEditField() {		
		return new TextBox(meal.getName(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				meal.setName(event.getValue());
			}
		});
	}
	
	private TextBox getCalEditField() {		
		return new TextBox(String.valueOf(meal.getCal()), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					meal.setCal(Integer.parseInt(event.getValue()));
				} catch (NumberFormatException e) {
					tbCal.setText(String.valueOf(meal.getCal()));
				}
			}
		});
	}

	public void focusOnCal() {
		tbCal.setFocus(true);
	}

	public MealRow apply(MealData meal) {
		this.meal = meal;
		update();
		return this;
	}

	public void setMealTable(MealTable mealTable) {
		this.mealTable = mealTable;
	}

	public MealData getMeal() {
		return meal;
	}


}
