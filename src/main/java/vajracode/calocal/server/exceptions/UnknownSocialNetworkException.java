package vajracode.calocal.server.exceptions;

import vajracode.calocal.shared.SocialNetwork;

public class UnknownSocialNetworkException extends CalocalException {

	private SocialNetwork sn;
	
	public UnknownSocialNetworkException(SocialNetwork sn) {
		this.sn = sn;
	}

}
