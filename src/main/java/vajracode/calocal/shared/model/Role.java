package vajracode.calocal.shared.model;

public enum Role {
	
	DISABLED, USER, ADMIN;

	public String roleName() {
		return "ROLE_" + name();
	}
}
