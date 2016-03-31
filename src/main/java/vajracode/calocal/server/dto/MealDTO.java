package vajracode.calocal.server.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vajracode.calocal.shared.model.MealData;

@Entity
@Table(name = "meal")
public class MealDTO extends DTO {

	private String name;	
	private Date consumed;
	private int cal;
	
	@ManyToOne
	private UserDTO user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getConsumed() {
		return consumed;
	}

	public void setConsumed(Date consumed) {
		this.consumed = consumed;
	}

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public MealData getMealData() {
		MealData data = new MealData();
		data.setId(getId());
		data.setName(name);
		data.setCal(cal);
		data.setDateTime(consumed);
		data.setUserId(user.getIdDirect());
		return data;
	} 	
		
	
}
