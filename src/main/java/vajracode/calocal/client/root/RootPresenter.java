package vajracode.calocal.client.root;

import javax.inject.Singleton;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorHandler;
import fr.putnami.pwt.core.error.client.ErrorManager;
import vajracode.calocal.client.error.MainErrorHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.modals.ModalUtils;

@Presenter( view = RootView.class )
@Singleton
public class RootPresenter extends CommonPresenter<RootView> {

	@Inject private EventBusHolder busHolder;	
	private IsWidget currentView;
	private boolean landingPresent = true;
	
	public void onInitRoot() {
		busHolder.set(eventBus);
				
		for (ErrorHandler eh : ErrorManager.get().getErrorHandlers()){
			if (eh instanceof MainErrorHandler)
				((MainErrorHandler) eh).setEventBus(eventBus);
		}		
		
		view.init();
		eventBus.oAuth();			
	}
		
	public void onInitHistory(){
		eventBus.main();
	}	

	public void onStartProgressBar(){
		view.startProgressBar();
	}
	
	public void onStopProgressBar(){
		view.stopProgressBar();
	}
	
	public void onSkipNextProgressBar() {
		view.setSkipNextProgressBar(true);
	}
		
	public void onSetBody(IsWidget w) {		
		ModalUtils.hide();
		if (currentView != null) {
			currentView.asWidget().removeFromParent();
		} else {
			view.cleanLowLevel();
		}
		landingPresent = false;
		if (w instanceof BeforeAttach)
			((BeforeAttach)w).beforeAttach();		
		view.setWidget(w);
		currentView = w;
	}
	
	public boolean isLandingPresent() {
		return landingPresent;
	}

}
