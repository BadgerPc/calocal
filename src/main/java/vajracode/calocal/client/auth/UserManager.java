package vajracode.calocal.client.auth;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;

import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.shared.model.UserData;

@Singleton
public class UserManager {

	@Inject EventBusHolder bus;; 
	
	private UserData user;
	
	public void update(UserData data) {
		this.user = data;
		bus.getEventBus().updateUser();
		GWT.log("current user: " + (data == null ? null : data.getName()));
	}

	public void signOut() {
		user = null;
		bus.getEventBus().updateUser();
	}

	public UserData getUserData() {
		return user;
	}

}
