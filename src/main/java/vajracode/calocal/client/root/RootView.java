package vajracode.calocal.client.root;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.error.client.ErrorHandler;
import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.service.client.CommandController;
import fr.putnami.pwt.core.service.client.event.CommandRequestEvent;
import fr.putnami.pwt.core.service.client.event.CommandRequestEvent.Handler;
import fr.putnami.pwt.core.service.client.event.CommandResponseEvent;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import vajracode.calocal.client.auth.UserManager;
import vajracode.calocal.client.elements.Body1Light;
import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.FlatButton;
import vajracode.calocal.client.framework.CommonView;
import vajracode.calocal.client.framework.EventBusHolder;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.modals.ChangePasswordModal;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;

/**
 * Application's root view
 *
 */
public class RootView extends CommonView<RootPresenter> implements Handler, fr.putnami.pwt.core.service.client.event.CommandResponseEvent.Handler {
	
	@Inject private EventBusHolder bus;
	@Inject private UserManager userManager;
	@Inject private I18nConstants msgs;
	@Inject private Provider<ChangePasswordModal> changePassProvider;
	
	private HTMLPanel main, username;
	
	public void init(){
		CommandController.get().addCommandRequestHandler(this);
		CommandController.get().addCommandResponseHandler(this);
		
		//initWidget(uiBinder.createAndBindUi(this));
		main = HTMLPanel.wrap(DOM.getElementById("main"));

		ErrorManager.get().registerErrorHandler(new ErrorHandler() {			
			@Override
			public boolean handle(Throwable error) {
				return false;
			}
			
			@Override
			public int getPriority() {				
				return Integer.MAX_VALUE;
			}
		});		
		
		username = HTMLPanel.wrap(DOM.getElementById("username"));
		userManager.addValueChangeHandler(new ValueChangeHandler<UserData>() {			
			@Override
			public void onValueChange(ValueChangeEvent<UserData> event) {
				updateUser(event.getValue());
			}
		});
		
		HTMLPanel logo = HTMLPanel.wrap(DOM.getElementById("logo"));
		logo.addStyleName(Resources.INSTANCE.css().cursorPointer());
		logo.addDomHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bus.getEventBus().main();
			}
		}, ClickEvent.getType());
	}
	
	@Override
	public void onCommandRequest(CommandRequestEvent event) {
		
	}

	@Override
	public void onCommandResponse(CommandResponseEvent event) {
		
	}

	public void setWidget(IsWidget w) {		
		main.add(w);
	}

	public void cleanLowLevel() {
		Element e = main.getElement();
		while(e.getChildCount() > 0)
			e.getChild(0).removeFromParent();	
		e.getStyle().setTextAlign(TextAlign.LEFT);
	};

	public Widget getMainWidget() {
		return main != null ? (main.getWidgetCount() > 0 ? main.getWidget(0) : null) : null;
	}

	public void updateUser(UserData u) {		
		username.clear();		
		if (u != null) {
			if (u.getRole() == Role.ADMIN) {				
				username.add(getAdminLink());
			}
			username.add(new Body1Light(u.getName()));
			username.add(getChangePassButton());
			username.add(getSignOutButton());
		}
	}

	private Widget getSignOutButton() {		
		return getIconButton(IconType.EXIT_TO_APP, new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				bus.getEventBus().signOut();
			}
		});
	}
	
	private Widget getChangePassButton() {
		return getIconButton(IconType.ENHANCED_ENCRYPTION, new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				changePassProvider.get().apply(userManager.getUserData().getId()).show();
			}
		});		
	}

	private Widget getIconButton(IconType icon, ClickHandler clickHandler) {
		Button b = new FlatButton();			
		b.setIconType(icon);
		b.addClickHandler(clickHandler);
		b.setWidth("60px");
		return b;		
	}

	private Widget getAdminLink() {
		MaterialLink admin = new MaterialLink(msgs.admin());
		admin.addStyleName(Resources.INSTANCE.css().margin16px());
		admin.addStyleName("blue-text");
		admin.addClickHandler(new ClickHandler() {					
			@Override
			public void onClick(ClickEvent event) {
				bus.getEventBus().admin();
			}
		});
		return admin;
	}
}
