package vajracode.calocal.client.auth;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import vajracode.calocal.client.framework.CommonView;


public class AuthView extends CommonView<AuthPresenter> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, AuthView> {}	
	
	@UiField MaterialTitle title;
	@UiField MaterialTextBox siLogin, siPass, suLogin, suPass, passConfirm;

	public AuthView() {
		initWidget(uiBinder.createAndBindUi(this));		
	}	
		
	public void reset() {
		title.setDescription("");
	}
	
	/*@UiHandler("fb")
	public void onFbButton(ClickEvent event) {
		//bus.getEventBus().startOAuthDanceSN(SocialNetwork.FB);
	}
	
	@UiHandler("vk")
	public void onVkButton(ClickEvent event) {
		//bus.getEventBus().startOAuthDanceSN(SocialNetwork.VK);
	}*/
	
	public void setTitle(String msg) {
		title.setDescription(msg);
	}
	
	@UiHandler("signIn")
	public void onSignInButton(ClickEvent event) {		
		getPresenter().signIn(siLogin.getText(), siPass.getText());
	}
	
	@UiHandler("signUp")
	public void onSignUpButton(ClickEvent event) {		
		getPresenter().signUp(suLogin.getText(), suPass.getText(), passConfirm.getText());
	}

	public void resetPass() {
		siPass.setText("");
		suPass.setText("");
		passConfirm.setText("");
		suLogin.setText("");
	}
	
}
