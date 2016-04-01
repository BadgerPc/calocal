package vajracode.calocal.client.auth;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.shared.model.UserData;

@Singleton
public class UserManager extends Widget implements HasValueChangeHandlers<UserData> {

	@Inject EventBusHolder bus;
	
	private UserData user;
	
	public void update(UserData data) {
		this.user = data;
		ValueChangeEvent.fire(this, data);
		GWT.log("current user: " + (data == null ? null : data.getName()));
	}

	public void signOut() {
		update(null);
	}

	public UserData getUserData() {
		return user;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<UserData> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

}
