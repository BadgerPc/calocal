package vajracode.calocal.client;

import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.mvp4g.client.Mvp4gModule;

import fr.putnami.pwt.core.error.client.ErrorHandler;
import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.Module;
import vajracode.calocal.client.error.CustomExceptionMapper;
import vajracode.calocal.client.error.MainErrorDisplayer;
import vajracode.calocal.client.error.MainErrorHandler;
import vajracode.calocal.client.resources.Resources;


public class CalocalEntryPoint implements EntryPoint, Module {	
	
	public void onModuleLoad() {
		Defaults.setServiceRoot("/api");
		Defaults.setDateFormat(null);
		Defaults.setExceptionMapper(new CustomExceptionMapper());
		
		final ErrorHandler eh = new MainErrorHandler();
		ErrorManager.get().registerErrorHandler(eh);
		
		final MainErrorDisplayer errorDisplayer = new MainErrorDisplayer();		
		ErrorManager.get().setErrorDisplayer(errorDisplayer);
		
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable throwable) {
				eh.handle(throwable);				
			}
		});
		
		Resources.INSTANCE.css().ensureInjected();
		
		Mvp4gModule module = GWT.create(Mvp4gModule.class);
		module.createAndStartModule();				
		//DOM.getElementById("preloader").removeFromParent();
		//RootPanel.get().add((Widget) module.getStartView());
		module.getStartView();
		//RootPanel.get().add(injector.getRootView().init());
	}
}
