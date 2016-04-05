package vajracode.calocal.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Spring Security authenification handler
 * @see vajracode.calocal.server.security.WebSecurityConfig
 *
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private final Logger log = Logger.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();               
        log.debug(userDetails.getUsername() + " passed authentication");

//       PrintWriter writer = response.getWriter();
//       mapper.writeValue(writer, user);
//       writer.flush();
    }
}
