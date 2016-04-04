package vajracode.calocal.shared.model;

import java.util.Date;

public class MealData extends Data {
	
	private Long userId;
	private String name;
	private Integer cal;
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

	public Integer getCal() {
		return cal;
	}

	public void setCal(Integer cal) {
		this.cal = cal;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
