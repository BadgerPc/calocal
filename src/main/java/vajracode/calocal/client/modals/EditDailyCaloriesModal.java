package vajracode.calocal.client.modals;

import javax.inject.Inject;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.auth.UserManager;
import vajracode.calocal.client.elements.Header;
import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.exceptions.FieldException;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.UserService;

public class EditDailyCaloriesModal extends CommonModal {
	
	@InjectService UserService service;
	@Inject private UserManager userManager;
	
	private I18nConstants msgs = GWT.create(I18nConstants.class);
	
	private TextBox cal = new TextBox();
	
	public EditDailyCaloriesModal() {
		add(new Header(msgs.enterDailyCalories()));
		add(cal);
		addButton(msgs.save(), new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				update();				
			}
		});
		addButtonClose(msgs.cancel());
	}
	
	protected void update() {
		UserData user = userManager.getUserData();
		try {
			user.setDailyCalories(cal.getInt());
			service.update(user.getId(), user);
			hide();
		} catch (NumberFormatException e) {
			throw new FieldException(e.getMessage());
		}
	}
	
	@AsyncHandler
	public void onUpdate(UserData user) {
		userManager.update(user);	
	}

	@Override
	public void show() {
		cal.setText(String.valueOf(userManager.getUserData().getDailyCalories()));
		super.show();
	}

}
