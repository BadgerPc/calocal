package vajracode.calocal.client.admin;

import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;
import vajracode.calocal.shared.service.UserService;

@Presenter(view = AdminView.class)
public class AdminPresenter extends CommonPresenter<AdminView> {
		
	@InjectService UserService service;	
	
	public void onAdmin(){
		view.reset();
		service.list(0, Integer.MAX_VALUE);
		eventBus.setBody(view);
	}
		
	@AsyncHandler
	public void onList(UserDataList list){
		view.apply(list);		
	}

	public void addUser(String name) {
		UserData user = new UserData();
		user.setName(name);
		service.create(user);
	}
	
	@AsyncHandler
	public void onCreate(UserData user){
		view.addUser(user);
	}
	
	@AsyncHandler
	public void onCreateThrowable(Throwable t){
		view.onAddUserFailed();
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}
		
}
