package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.Date;

public class MealData implements Serializable {
	
	private long id, userId;
	private String name;
	private int cal;
	private Date dateTime;
	
	public MealData() {
	}
	
	public MealData(String name, int cal, Date dateTime) {
		this.name = name;
		this.cal = cal;
		this.dateTime = dateTime;
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

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
