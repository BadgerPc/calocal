package vajracode.calocal.server.service;

import java.io.IOException;

import javax.servlet.*;

import org.apache.log4j.Logger;

public class ExceptionsLoggingFilter implements Filter {

	private final Logger log = Logger.getLogger(getClass());
	
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            log.error("Unhandled exception", e);
            throw e;
        }
    }

    @Override
    public void destroy() {
    }

}