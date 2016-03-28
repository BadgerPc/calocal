package vajracode.calocal.client.auth;

import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.client.utils.NativeUtils;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.service.AuthService;


@Presenter(view = AuthView.class)
public class AuthPresenter extends CommonPresenter<AuthView> {
		
	@InjectService AuthService service;
	
	private String suLogin;
	private String suPass;
	
	public void onAuth(){
		view.reset();
		eventBus.setBody(view);
		NativeUtils.removeLoader();
	}
	
	public void onAuthError(String msg) {
		onAuth();
		view.setTitle(msg);
	}

	public void signIn(String login, String pass) {
		FieldVerifier.checkName(login);
		FieldVerifier.checkPass(pass);
		service.login(login, pass);		
	}

	@AsyncHandler
	public void onLogin(Void v) {
		eventBus.checkLoggedIn();
	}

	public void signUp(String login, String pass, String passConfirm) {
		FieldVerifier.checkName(suLogin = login);
		FieldVerifier.checkPass(suPass = pass);
		FieldVerifier.checkPassEqual(pass, passConfirm);
		service.register(new RegistrationData(login, pass));
	}
	
	@AsyncHandler
	public void onRegister(Void v) {
		signIn(suLogin, suPass);
	}
	
}
