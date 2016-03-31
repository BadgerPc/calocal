package vajracode.calocal.server.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dto.UserDTO;

@Component
public class UserDao extends Dao<UserDTO> {

	@Transactional(readOnly = true)
	public UserDTO getUserByName(String name) {
		return getSingleResultOrNull(
			em.createQuery("from UserDTO where name = ?1", UserDTO.class)
			.setParameter(1, name));			
	}
	
	public UserDTO getById(long id) {
		return em.find(UserDTO.class, id);
	}

	public UserDTO getReference(long id) {
		return em.getReference(UserDTO.class, id);
	}

}
