package vajracode.calocal.client.modals;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import gwt.material.design.client.ui.MaterialTimePicker;
import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.i18n.I18nConstants;

public class FilterTimeModal extends CommonModal {
	
	@Inject EventBusHolder bus;

	private I18nConstants msgs = GWT.create(I18nConstants.class);
	
	private static final DateTimeFormat time = DateTimeFormat.getFormat("hh:mm");
	
	private MaterialTimePicker from, to; 
	
	public FilterTimeModal() {
		add(from = new MaterialTimePicker());		
		add(to = new MaterialTimePicker());
		addButton(msgs.apply(), new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bus.getEventBus().filter(getFromTime(), getToTime(), null, null);
			}
		});
		addButtonClose(msgs.cancel());
	}
	
	protected Date getToTime() {
		return time.parse(to.getTime());
	}

	protected Date getFromTime() {
		return time.parse(from.getTime());
	}

	public FilterTimeModal init(Date start, Date end) {		
		from.setPlaceholder(msgs.from());		
		to.setPlaceholder(msgs.to());
		from.setTime(start != null ? time.format(start) : "00:00");
		to.setTime(end != null ? time.format(end) : "00:00");
		return this;
	}
}
