package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.List;

/**
 * Root for the DTOs list, representing resources list on the client-side. 
 * Supports pagination
 *
 */
public class DataList<T> implements Serializable {

	private List<T> data;
	private long total;
	private int offset, limit;
	
	public DataList() {
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
