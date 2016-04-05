package vajracode.calocal.shared.service;

import vajracode.calocal.shared.model.Data;

/**
 * Service needs to extend this to be supported by data-table widgets.
 * @see vajracode.calocal.client.table.CRUDRow
 * @see vajracode.calocal.client.table.CRUDTable
 *
 * @param <T>
 */
public interface CRUDService<T extends Data> {
	
	 T create(T data);
	    	    
	 void delete(long id);

	 T get(long id);
	    	  
	 T update(long id, T data);

}
