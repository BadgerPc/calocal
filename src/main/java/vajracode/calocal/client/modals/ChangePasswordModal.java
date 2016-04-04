package vajracode.calocal.client.modals;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import gwt.material.design.client.constants.InputType;
import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.UserService;

public class ChangePasswordModal extends CommonModal implements HasValueChangeHandlers<String> {
	
	@InjectService UserService service;
	
	private I18nConstants msgs = GWT.create(I18nConstants.class);	
	
	private TextBox pass1 = new TextBox(), pass2 = new TextBox();

	private long userId;
	
	public ChangePasswordModal() {
		add(pass1);		
		add(pass2);
		pass1.setPlaceholder(msgs.pass());		
		pass2.setPlaceholder(msgs.confPass());
		pass1.setType(InputType.PASSWORD);
		pass2.setType(InputType.PASSWORD);
		addButton(msgs.apply(), new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				update();
				hide();
			}
		});
		addButtonClose(msgs.cancel());
	}
	
	protected void update() {
		String p1 = pass1.getText();
		String p2 = pass2.getText();
		FieldVerifier.checkPass(p1);
		FieldVerifier.checkPass(p2);
		FieldVerifier.checkPassEqual(p1, p2);
		
		UserData u = new UserData();
		u.setId(userId);
		u.setPassword(p1);
		service.update(userId, u);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {		
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public ChangePasswordModal apply(long id) {
		userId = id;
		return this;
	}
}
