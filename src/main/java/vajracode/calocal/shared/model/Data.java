package vajracode.calocal.shared.model;

import java.io.Serializable;

/**
 * Root for the DTOs, representing resources on the client-side
 *
 */
public class Data implements Serializable {
	
	protected long id;
	
	public Data() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
