package vajracode.calocal.server.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.server.dto.UserDTO;

@Service
public class SecurityUserService implements UserDetailsService {
    
	@Autowired
	private BeanFactory beanFactory;

	@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = beanFactory.getBean(UserDao.class).getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
    
        return new SecurityUser(user.getUserData(), user.getPassword());
    }
}
