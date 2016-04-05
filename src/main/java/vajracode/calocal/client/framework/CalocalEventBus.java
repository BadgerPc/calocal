package vajracode.calocal.client.framework;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.event.EventBusWithLookup;

import vajracode.calocal.client.admin.AdminPresenter;
import vajracode.calocal.client.auth.AuthPresenter;
import vajracode.calocal.client.auth.OAuthHandler;
import vajracode.calocal.client.main.MainPresenter;
import vajracode.calocal.client.root.RootPresenter;

/**
 * Application's MVP4G event bus
 *
 */
@Events( startPresenter = RootPresenter.class, ginModules = {MainGinModule.class} )
@Debug(logger = CustomLogger.class)
public interface CalocalEventBus extends EventBusWithLookup {

	@Start	
	@Event(handlers = RootPresenter.class)
	void initRoot();
	
	@InitHistory
	@Event(handlers = RootPresenter.class)
	void initHistory();

	@Event(handlers = RootPresenter.class)
	void setBody(IsWidget w);

	
	@Event(handlers = OAuthHandler.class)
	void oAuth();
	
	/*@Event(handlers = OAuthHandler.class)
	void startOAuthDanceSN(SocialNetwork sn);

	@Event(handlers = OAuthHandler.class)
	void startOAuthDanceURL(String url);*/
	
	@Event(handlers = OAuthHandler.class)
	void signOut();
	
	@Event(handlers = OAuthHandler.class)
	void checkLoggedIn();
	
	
	@Event(handlers = AuthPresenter.class) 
	void auth();

	@Event(handlers = AuthPresenter.class)
	void authError(String error);
	

	@Event(handlers = MainPresenter.class)
	void main();

	@Event(handlers = MainPresenter.class)
	void filter(Date fromDate, Date toDate, String fromTime, String toTime);

	
	@Event(handlers = AdminPresenter.class)
	void admin();


}
