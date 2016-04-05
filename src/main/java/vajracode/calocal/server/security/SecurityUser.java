package vajracode.calocal.server.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vajracode.calocal.shared.model.UserData;


/**
 * Spring Security user data
 *
 */
public class SecurityUser implements UserDetails {
	
    private UserData user;
    private Collection<? extends GrantedAuthority> authorities;
    private char[] pass;

    public SecurityUser(UserData user, String password) {
        this.user = user;
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().roleName()));
        pass = password.toCharArray();
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return String.valueOf(pass);
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
