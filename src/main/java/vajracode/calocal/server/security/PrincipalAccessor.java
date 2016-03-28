package vajracode.calocal.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import vajracode.calocal.server.exceptions.NotAuthException;
import vajracode.calocal.shared.model.UserData;

@Component
public class PrincipalAccessor {
	
    public UserData getLoggedInUser() {
        UserData user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // principal can be "anonymousUser" (String)
            if (principal instanceof SecurityUser) {
                SecurityUser userDetails = (SecurityUser) principal;
                user = userDetails.getUser();
            }
        }
        if (user == null)
        	throw new NotAuthException();
        return user;
    }
    
}
