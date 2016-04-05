package vajracode.calocal.shared.model;

/**
 * User role
 *
 */
public enum Role {
	
	DISABLED, USER, ADMIN;

	/**
	 * @return Role string representation for the Spring Security
	 * @see vajracode.calocal.server.security.SecurityUser
	 */
	public String roleName() {
		return "ROLE_" + name();
	}
}
