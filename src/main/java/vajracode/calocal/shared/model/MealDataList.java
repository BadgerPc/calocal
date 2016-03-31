package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.List;

public class MealDataList implements Serializable {

	private List<MealData> data;
	private long offset, total;		
	
	public MealDataList() {
	}

	public List<MealData> getData() {
		return data;
	}

	public void setData(List<MealData> data) {
		this.data = data;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
