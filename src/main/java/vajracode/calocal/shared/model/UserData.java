package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.Date;

public class UserData implements Serializable {

	private Long id;
	private String name;	
	private Date created;	
	private Role role;

	public UserData() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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


}
