package vajracode.calocal.client.modals;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;

import vajracode.calocal.client.i18n.I18nConstants;

public class ConfirmModal extends CommonModal {
	
	@Inject I18nConstants msgs;
	
	public ConfirmModal() {					
	}
	
	public void show(String header, String message, final Command command) {
		addHeader(header);
		addBody(message);
		
		addButton(msgs.ok(), new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hide();
				command.execute();
			}			
		});		
		addButtonClose(msgs.cancel());
		super.show();
	}
}