package vajracode.calocal.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import vajracode.calocal.server.dto.MealDTO;
import vajracode.calocal.server.exceptions.UnauthorizedException;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;

@Component
public class AccessManager {
	
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
        	throw new UnauthorizedException();
        return user;
    }

	public boolean hasAccessToMeal(MealDTO meal) {
		UserData u = getLoggedInUser();
		return u.getRole() == Role.ADMIN || u.getId() == meal.getUser().getId();
	}

	public boolean isAdmin() {
		return getLoggedInUser().getRole() == Role.ADMIN;
	}

	public long getLoggedInUserId() {
		return getLoggedInUser().getId();
	}
    
}
