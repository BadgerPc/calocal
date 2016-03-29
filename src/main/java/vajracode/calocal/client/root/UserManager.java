package vajracode.calocal.client.root;

import javax.inject.Singleton;

import com.google.gwt.core.client.GWT;

import vajracode.calocal.shared.model.UserData;

@Singleton
public class UserManager {

	private UserData user;
	
	public void update(UserData data) {
		this.user = data;
		GWT.log("current user: " + (data == null ? null : data.getName()));
	}

	public void signOut() {
		user = null;
	}

	public UserData getUserData() {
		return user;
	}

}
