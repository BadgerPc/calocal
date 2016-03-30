package vajracode.calocal.server.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends ShaPasswordEncoder {

	public PasswordEncoder() {
		super(256);
	}

	public String encodePassword(String pass) {
		return encodePassword(pass, null);
	}
}
