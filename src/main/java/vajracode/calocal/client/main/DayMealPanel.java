package vajracode.calocal.client.main;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.ui.MaterialPanel;
import vajracode.calocal.client.elements.CalSummary;
import vajracode.calocal.client.elements.Header;
import vajracode.calocal.client.elements.RowFlexPanel;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.model.MealData;

/**
 * Panel, containing meals table for one day, summary on them and date 
 *
 */
public class DayMealPanel extends MaterialPanel {

	@Inject private CalSummary summary;
	@Inject private MealTable table;
	
	public DayMealPanel apply(Date date) {
		addStyleName(Resources.INSTANCE.css().marginTop32());
		RowFlexPanel header = new RowFlexPanel();		
		header.addStyleName(Resources.INSTANCE.css().justifyContentSpace());		
		add(header);
		header.add(new Header(DateUtils.formatDateSmart(date)));
		header.add(summary);
		
		add(table);
		table.addStyleName(Resources.INSTANCE.css().marginTop16());
		table.addValueChangeHandler(new ValueChangeHandler<Integer>() {				
			@Override
			public void onValueChange(ValueChangeEvent<Integer> event) {
				summary.updateSum(event.getValue());
			}
		});
		return this;
	}

	public void add(MealData meal) {
		table.addToBegining(meal);
	}

	public int getCalSum() {
		return table.getValue();
	}

	public MealTable getMealTable() {
		return table;
	}

}
