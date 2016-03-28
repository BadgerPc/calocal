package vajracode.calocal.shared.model;

import java.io.Serializable;
import java.util.List;

import org.fusesource.restygwt.client.Json;

public class UserListData implements Serializable {

	private int page;

	@Json(name = "per_page")
	private int perPage;
	private int total;

	@Json(name = "total_pages")
	private int totalPages;

	private List<UserData> data;

	public UserListData() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<UserData> getData() {
		return data;
	}

	public void setData(List<UserData> data) {
		this.data = data;
	}

}
