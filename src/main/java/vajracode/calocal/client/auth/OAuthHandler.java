package vajracode.calocal.client.auth;

import javax.inject.Inject;

import com.google.gwt.user.client.Window.Location;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.event.BaseEventHandler;

import fr.putnami.pwt.core.inject.client.Injected;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CalocalEventBus;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.root.UserManager;
import vajracode.calocal.client.utils.NativeUtils;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.AuthService;

@EventHandler
public class OAuthHandler extends BaseEventHandler<CalocalEventBus> implements Injected {

	@InjectService AuthService service;	
	@Inject UserManager userManager;
	@Inject I18nConstants msgs;
	
	public void onOAuth() {
		final String code = Location.getParameter("code");
        final String error = Location.getParameter("error_reason");
 
        if (null != error && error.equals("user_denied")) {
            getEventBus().authError(Location.getParameter("error_description"));
            return;
        }
        
        if (code == null) {        	        	
	        getEventBus().skipNextProgressBar();
	        onCheckLoggedIn();	            	
        	return;
        }
        	
        //service.oAuthCallback(code);        	        
    }	
	
	public void onCheckLoggedIn() {
		service.getCurrentUser();
	}

	@AsyncHandler
	public void onGetCurrentUser(UserData data) {
		NativeUtils.removeLoader();
		if (data != null) {
			userManager.update(data);				
			eventBus.main();	
		} else {
			eventBus.authError(msgs.unknownError());
		}
		
		/*if (History.getToken().length() > 0) {
			History.fireCurrentHistoryState();
		} else {
			eventBus.main();		
		}*/
	}
	
	public void onSignOut() {
		if (userManager.getUserData() == null) {
			eventBus.auth();
		} else {
			service.logout();
			userManager.signOut();
		}
	}
	
	@AsyncHandler
	public void onLogout(Void v) {
		eventBus.auth();
	}

	/*@AsyncHandler
	public void onOAuthCallback(OAuthResult oar) {		
		if (oar == null || oar.sn == null) {
			NativeUtils.cleanLocationUrl();			
		} else {
			// user was already in
		}
	}

	public void onStartOAuthDanceSN(SocialNetwork sn) {		
		StorageUtils.setSkipLanding(true);
		service.getOAuthUrl(sn);
		Tracker.goal("authStart");
	}
	
	@AsyncHandler
	public void onGetOAuthUrl(String url) {
		Location.assign(url);
	}
	
	public void onStartOAuthDanceURL(String url) {
		if (url == null) {
			getEventBus().auth();
			return;
		}
		Location.assign(url);
	}*/
	
//	private SocialNetwork getCurrentOAuthSocialNetwork() {
//		String c = Cookies.getCookie(CookieConstants.currentOAuthSocialNetwork);
//		try {
//			return SocialNetwork.valueOf(c);
//		} catch(Exception e) {
//			return null;
//		}
//	}
}
