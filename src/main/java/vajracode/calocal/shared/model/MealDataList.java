package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.List;

public class MealDataList implements Serializable {

	private List<MealData> data;
	private int offset, total;		
	
	public MealDataList() {
	}
}
