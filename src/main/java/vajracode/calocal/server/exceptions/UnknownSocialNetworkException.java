package vajracode.calocal.server.exceptions;

import vajracode.calocal.shared.SocialNetwork;

public class UnknownSocialNetworkException extends RuntimeException {

	private SocialNetwork sn;
	
	public UnknownSocialNetworkException(SocialNetwork sn) {
		this.sn = sn;
	}

}
