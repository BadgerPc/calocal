package vajracode.calocal.client.root;

import com.google.gwt.core.client.GWT;

import vajracode.calocal.shared.model.UserData;

public class UserManager {

	private UserData user;
	
	public void update(UserData data) {
		this.user = data;
		GWT.log("current user: " + (data == null ? null : data.getName()));
	}

	public void signOut() {
		user = null;
	}

}
