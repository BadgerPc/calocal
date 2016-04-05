package vajracode.calocal.server.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;
import vajracode.calocal.shared.model.Role;

/**
 * Spring Security UserDetailsService implementation for {@link vajracode.calocal.server.dto.UserDTO} 
 *
 */
@Component
public class SecurityUserService implements UserDetailsService {
    
	private final Logger log = Logger.getLogger(getClass());
		
	private UserDao userDao;
	
	@Autowired
	public SecurityUserService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername(" + username + ")");
        UserDTO user = userDao.getUserByName(username);
        
        if (user == null) 
            throw new UsernameNotFoundException(username);
        
        if (user.getRole() == Role.DISABLED)
        	throw new DisabledException(username);
    
        return new SecurityUser(user.getUserData(), user.getPassword());
    }
}
