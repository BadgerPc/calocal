package vajracode.calocal.shared.model;

import java.io.Serializable;

public class RegistrationData implements Serializable {

	private String login, pass;	
	
	public RegistrationData() {
	}

	public RegistrationData(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
