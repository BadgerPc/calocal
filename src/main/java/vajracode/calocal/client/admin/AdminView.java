package vajracode.calocal.client.admin;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.ColFlexPanel;
import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.framework.CommonView;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.model.UserDataList;


public class AdminView extends CommonView<AdminPresenter> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, AdminView> {}	
	
	@Inject private I18nConstants msgs;
	@Inject private Provider<UserTable> userTableProvider;
	
	@UiField ColFlexPanel content;
	@UiField TextBox userName;	
	@UiField Button addUser;
	
	private UserTable table;
	
	public AdminView() {		
	}	
		
	public void reset() {
		init();
		content.clear();
	}

	private void init() {
		if (getWidget() == null)
			initWidget(uiBinder.createAndBindUi(this));
	}

	public void apply(UserDataList list) {
		table = (UserTable) userTableProvider.get().apply(list.getData());
		content.add(table);
	}
				
	@UiHandler("addUser")
	public void addUserHandler(ClickEvent event) {		
		if (userName.getText().length() == 0)
			return;
		addUser.setEnabled(false);
		getPresenter().addUser(userName.getText());
		userName.clear();
	}

	@UiHandler("userName")
	public void addUserNameHandler(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER && addUser.isEnabled())
			addUserHandler(null);
	}
	
	public void addUser(UserData user) {	
		if (table == null || !table.isAttached())
			return;			
		table.addAndEdit(user);
		addUser.setEnabled(true);		
	}	

	public void onAddUserFailed() {
		addUser.setEnabled(true);
	}
		
}
