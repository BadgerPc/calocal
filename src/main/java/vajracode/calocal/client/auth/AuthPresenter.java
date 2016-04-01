package vajracode.calocal.client.auth;

import javax.inject.Inject;

import com.google.gwt.http.client.Response;
import com.mvp4g.client.annotation.Presenter;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import gwt.material.design.client.ui.MaterialToast;
import vajracode.calocal.client.framework.CommonPresenter;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.utils.NativeUtils;
import vajracode.calocal.client.utils.RestUtils;
import vajracode.calocal.shared.FieldVerifier;
import vajracode.calocal.shared.model.RegistrationData;
import vajracode.calocal.shared.service.AuthService;


@Presenter(view = AuthView.class)
public class AuthPresenter extends CommonPresenter<AuthView> {
		
	@InjectService AuthService service;
	@Inject UserManager userManager;
	@Inject I18nConstants msgs;
	
	private String suLogin;
	private String suPass;
	
	public void onAuth(){
		view.reset();
		eventBus.setBody(view);
		NativeUtils.removeLoader();
	}
	
	public void onAuthError(String msg) {
		userManager.signOut();
		onAuth();
		view.setTitle(msg);
	}

	public void signIn(String login, String pass) {
		FieldVerifier.checkUserName(login);
		FieldVerifier.checkPass(pass);
		service.login(login, pass);		
	}

	@AsyncHandler
	public void onLogin(Void v) {
		eventBus.checkLoggedIn();
	}
	
	@AsyncHandler
	public void onLoginThrown(Throwable e) {
		if (RestUtils.getStatuCode(e) == Response.SC_UNAUTHORIZED) {
			MaterialToast.fireToast(msgs.badCredentials());
			view.resetPass();
		}
		ErrorManager.get().getErrorHandlers().get(0).handle(e);
	}

	public void signUp(String login, String pass, String passConfirm) {
		FieldVerifier.checkUserName(suLogin = login);
		FieldVerifier.checkPass(suPass = pass);
		FieldVerifier.checkPassEqual(pass, passConfirm);
		service.register(new RegistrationData(login, pass));
	}
	
	@AsyncHandler
	public void onRegister(Void v) {
		signIn(suLogin, suPass);
	}
	
}
