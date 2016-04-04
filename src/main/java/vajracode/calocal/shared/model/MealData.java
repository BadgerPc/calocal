package vajracode.calocal.shared.model;

import java.util.Date;

public class MealData extends Data {
	
	private long userId;
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
