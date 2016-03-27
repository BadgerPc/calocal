package vajracode.calocal.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import vajracode.calocal.shared.exceptions.UnknownSocialNetworkException;

public enum SocialNetwork implements IsSerializable {
	VK {
		@Override
		public String getProviderId() {
			return "vkontakte";
		}
		@Override
		public String getScope() {
			return "friends,offline";
		}
		@Override
		public String getTitle() {
			return "ВКонтакте";
		}
	}, FB {
		@Override
		public String getProviderId() {
			return "facebook";
		}
		@Override
		public String getScope() {
			return "public_profile,user_friends";
		}
		@Override
		public String getTitle() {
			return "Facebook";
		}
	};
	
	private SocialNetwork() {
	}

	public String getProviderId() {
		return null;
	}

	public static SocialNetwork fromProviderId(String providerId) {
		if ("vkontakte".equalsIgnoreCase(providerId)) return VK;
		if ("facebook".equalsIgnoreCase(providerId)) return FB;
		throw new UnknownSocialNetworkException(null);
	}

	public String getScope() {
		return "";
	}

	public String getTitle() {
		return null;
	}
}
