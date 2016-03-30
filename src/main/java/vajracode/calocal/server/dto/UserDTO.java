package vajracode.calocal.server.dto;

import java.util.Date;

import javax.persistence.*;

import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;

@Entity
@Table(name = "user")
public class UserDTO extends DTO {

	private String name, password;	
	private Date created;
	private int dailyCalories;
	
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserData getUserData() {
		UserData ret = new UserData();
		ret.setId(getId());
		ret.setName(name);
		ret.setCreated(created);
		ret.setRole(role);
		ret.setDailyCalories(dailyCalories);
		return ret;
	}

	public int getDailyCalories() {
		return dailyCalories;
	}

	public void setDailyCalories(int dailyCalories) {
		this.dailyCalories = dailyCalories;
	}
		
	
}
