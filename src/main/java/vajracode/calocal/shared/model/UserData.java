package vajracode.calocal.shared.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData implements Serializable {

	private long id;
	private int dailyCalories;
	private String name;
	private String password;	
	private Role role;

	public UserData() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getDailyCalories() {
		return dailyCalories;
	}

	public void setDailyCalories(int dailyCalories) {
		this.dailyCalories = dailyCalories;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
