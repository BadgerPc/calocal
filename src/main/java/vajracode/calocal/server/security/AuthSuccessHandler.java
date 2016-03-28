package vajracode.calocal.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //private static final Logger LOGGER = LoggerFactory.getLogger(AuthSuccessHandler.class);

    //private final ObjectMapper mapper = new MappingJackson2HttpMessageConverter();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

//        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
//        UserData user = userDetails.getUser();
        //userDetails.setUser(user);

        //LOGGER.info(userDetails.getUsername() + " got is connected ");

//       PrintWriter writer = response.getWriter();
//        mapper.writeValue(writer, user);
//       writer.flush();
    }
}
