package vajracode.calocal.client.framework;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.*;
import com.mvp4g.client.annotation.module.AfterLoadChildModule;
import com.mvp4g.client.annotation.module.BeforeLoadChildModule;
import com.mvp4g.client.event.EventBusWithLookup;

import vajracode.calocal.client.auth.AuthPresenter;
import vajracode.calocal.client.auth.OAuthHandler;
import vajracode.calocal.client.main.MainPresenter;
import vajracode.calocal.client.root.RootPresenter;

@Events( startPresenter = RootPresenter.class, ginModules = {MainGinModule.class} )
@Debug(logger = CustomLogger.class)
public interface CalocalEventBus extends EventBusWithLookup {

	@Start	
	@Event(handlers = RootPresenter.class)
	void initRoot();
	
	@InitHistory
	@Event(handlers = RootPresenter.class)
	void initHistory();

	@BeforeLoadChildModule
	@Event(handlers = RootPresenter.class)
	void startProgressBar();
	
	@AfterLoadChildModule
	@Event(handlers = RootPresenter.class)
	void stopProgressBar();
	
	@Event(handlers = RootPresenter.class)
	void skipNextProgressBar();

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

}
