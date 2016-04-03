package vajracode.calocal.client.modals;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.ui.MaterialDatePicker;
import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.constants.DateConstants;

public class FilterDateModal extends CommonModal {
	
	@Inject EventBusHolder bus;
	
	private I18nConstants msgs = GWT.create(I18nConstants.class);
	
	private MaterialDatePicker from, to; 
	
	public FilterDateModal() {
		add(from = new MaterialDatePicker());
		add(to = new MaterialDatePicker());
		addButton(msgs.apply(), new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				FieldVerifier.checkFilterDates(from.getDate(), to.getDate());
				bus.getEventBus().filter(from.getPickerDate(), to.getPickerDate(), null, null);
				hide();
			}
		});
		addButtonClose(msgs.cancel());
		from.setPlaceholder(msgs.from());		
		to.setPlaceholder(msgs.to());
		from.setDateMax(new Date());
		to.setDateMax(new Date());
	}
	
	public FilterDateModal init(Date start, Date end) {				
		from.setDate(start != null ? start : new Date(new Date().getTime() - DateConstants.MILLIS_WEEK));
		to.setDate(end != null ? end : new Date());
		return this;
	}
	
	

}
