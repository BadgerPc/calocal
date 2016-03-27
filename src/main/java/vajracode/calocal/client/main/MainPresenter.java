package vajracode.calocal.client.main;

import com.mvp4g.client.annotation.Presenter;

import vajracode.calocal.client.framework.CommonPresenter;


@Presenter(view = MainView.class)
public class MainPresenter extends CommonPresenter<MainView> {
		
	//@InjectService AuthService service;
	
	public void onMain(){
		view.reset();
		eventBus.setBody(view);		
	}
		
}
