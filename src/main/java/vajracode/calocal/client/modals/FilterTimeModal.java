package vajracode.calocal.client.modals;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.utils.DateUtils;
import vajracode.calocal.shared.FieldVerifier;

public class FilterTimeModal extends CommonModal {
	
	@Inject EventBusHolder bus;

	private I18nConstants msgs = GWT.create(I18nConstants.class);	
	
	//private TimePicker from, to; 
	private TextBox from = new TextBox(), to = new TextBox();
	
	public FilterTimeModal() {
		add(from);		
		add(to);
		from.setPlaceholder(msgs.from());		
		to.setPlaceholder(msgs.to());
		addButton(msgs.apply(), new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {			
				//GWT.log(from.getText() + " - " + to.getText());
				FieldVerifier.checkFilterTimesUi(from.getText(), to.getText());
				bus.getEventBus().filter(null, null, from.getText(), to.getText());
				hide();
			}
		});
		addButtonClose(msgs.cancel());
	}


	public FilterTimeModal init(String start, String end) {				
		from.setText(start == null ? DateUtils.timeUi.format(new Date()) : start);
		to.setText(end == null ? DateUtils.timeUi.format(new Date()) : end);
		return this;
	}
}
