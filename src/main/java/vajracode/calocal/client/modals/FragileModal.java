package vajracode.calocal.client.modals;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.i18n.I18nConstants;

public class FragileModal extends CommonModal {

	@Inject I18nConstants msgs;
	@Inject EventBusHolder bus;
	
	public FragileModal() {					
	}

	public void show(final String eventName, final Object[] params) {
		addHeader(msgs.warning());
		addBody(msgs.confirmFragile());
		
		addButton(msgs.ok(), new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hide();
				bus.getEventBus().dispatch(eventName, params);
			}			
		});		
		addButtonClose(msgs.noStay());
		super.show();
	}
}