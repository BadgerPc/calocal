package vajracode.calocal.shared.exceptions;

import vajracode.calocal.shared.SocialNetwork;

public class UnknownSocialNetworkException extends CalocalException {

	private SocialNetwork sn;
	
	public UnknownSocialNetworkException(SocialNetwork sn) {
		this.sn = sn;
	}

}
