package vajracode.calocal.client.main;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.client.table.CRUDRow;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.model.MealData;
import vajracode.calocal.shared.service.MealService;

/**
 * Meal row with CRUD functionality
 *
 */
public class MealRow extends CRUDRow<MealData> {

	@InjectService MealService service;
	
	private TextBox tbDate, tbName, tbCal;
	private Widget lDate, lName, lCal;	
	
	public MealRow() {		
	}
	
	public MealRow(MealData meal) {
		this();
		apply(meal);
	}
	
	protected void update() {
		clear();
		add(lDate = getWidget(getTimeText()));
		lDate.addStyleName(Resources.INSTANCE.css().marginRight64());
		add(lName = getWidget(data.getName()));
		lName.addStyleName(Resources.INSTANCE.css().marginRight64());
		lName.setWidth("100%");
		add(lCal = getWidget(String.valueOf(data.getCal())));		
		lCal.getElement().getStyle().setMarginRight(8, Unit.PX);
		add(getEditButton());
	}

	private String getTimeText() {
		return DateUtils.timeUi.format(data.getDateTime());
	}

	public void edit() {
		clear();
		add(tbDate = getDateEditField());
		add(tbName = getNameEditField());
		tbName.setWidth("65%");
		add(tbCal = getCalEditField());
		add(getFinishButton());
		add(getDeleteButton());
	}
	
	private TextBox getDateEditField() {		
		return new TextBox(getTimeText(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {					
					data.setDateTime(DateUtils.getDateWithTime(event.getValue(), data.getDateTime()));
				} catch (IllegalArgumentException e) {
					tbDate.setText(getTimeText());
				}
			}
		});
	}
	
	private TextBox getNameEditField() {		
		return new TextBox(data.getName(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				data.setName(event.getValue());
			}
		});
	}
	
	private TextBox getCalEditField() {		
		return new TextBox(String.valueOf(data.getCal()), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					data.setCal(Integer.parseInt(event.getValue()));
				} catch (NumberFormatException e) {
					tbCal.setText(String.valueOf(data.getCal()));
				}
			}
		});
	}

	public void focusOnCal() {
		tbCal.setFocus(true);
	}

	public MealService getService() {
		return service;
	}
	

}
