package vajracode.calocal.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private final Logger log = Logger.getLogger(getClass());
	
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        log.debug("Authentication failure: " + exception.getMessage());
        
//        PrintWriter writer = response.getWriter();
//        mapper.writeValue(writer, new ErrorData(exception.getMessage()));        
//        writer.flush();
    }
}
