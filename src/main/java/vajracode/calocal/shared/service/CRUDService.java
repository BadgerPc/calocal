package vajracode.calocal.shared.service;

import vajracode.calocal.shared.model.Data;

public interface CRUDService<T extends Data> {
	
	 T create(T data);
	    	    
	 void delete(long id);

	 T get(long id);
	    	  
	 T update(long id, T data);

}
