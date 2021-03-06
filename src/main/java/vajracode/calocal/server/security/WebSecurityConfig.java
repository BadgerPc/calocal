package vajracode.calocal.server.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import vajracode.calocal.server.dao.UserDao;
import vajracode.calocal.shared.constants.LoginFields;
import vajracode.calocal.shared.constants.ResourcePaths;
import vajracode.calocal.shared.model.Role;

/**
 * Spring Security configuration. Has it's own context for all injected beans. 
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("vajracode.calocal.server")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	@Lazy
	private UserDao userDao; 
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private HttpLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }   
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(securityUserService());
        authenticationProvider.setPasswordEncoder(new PasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService securityUserService() {
		return new SecurityUserService(userDao);
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        log.debug("DaoAuthenticationProvider added");
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers("/Calocal/**").permitAll()
	                .antMatchers("/favicon.ico").permitAll()
	                .antMatchers("/logo.png").permitAll()
	                .antMatchers("/index.html").permitAll()
	                .antMatchers(ResourcePaths.API_LOGIN).permitAll()	                
	                .antMatchers(HttpMethod.DELETE, ResourcePaths.API_USER).hasAnyRole(Role.ADMIN.name())
	                .antMatchers(HttpMethod.POST, ResourcePaths.API_USER).hasAnyRole(Role.ADMIN.name())
	                .antMatchers(HttpMethod.GET, ResourcePaths.API_USER).hasAnyRole(Role.ADMIN.name())
	                .anyRequest().authenticated()
                .and()
	                .authenticationProvider(authenticationProvider())
	                .exceptionHandling()
	                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
	                .formLogin()
	                .permitAll()
	                .loginProcessingUrl(ResourcePaths.API_LOGIN)
	                .usernameParameter(LoginFields.USERNAME)
	                .passwordParameter(LoginFields.PASSWORD)
	                .successHandler(authSuccessHandler)
	                .failureHandler(authFailureHandler)
                .and()
                	.logout().permitAll()
                	.logoutRequestMatcher(new AntPathRequestMatcher(ResourcePaths.API_LOGIN, "DELETE"))
                	.logoutSuccessHandler(logoutSuccessHandler)
                .and()
	                .sessionManagement()
	                .maximumSessions(1);

        http.authorizeRequests().anyRequest().authenticated();
    }
}
