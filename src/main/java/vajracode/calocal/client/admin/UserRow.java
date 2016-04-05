package vajracode.calocal.client.admin;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.inject.client.annotation.InjectService;
import gwt.material.design.client.constants.IconType;
import vajracode.calocal.client.elements.RoleListBox;
import vajracode.calocal.client.elements.TextBox;
import vajracode.calocal.client.modals.ChangePasswordModal;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.client.table.CRUDRow;
import vajracode.calocal.shared.model.Role;
import vajracode.calocal.shared.model.UserData;
import vajracode.calocal.shared.service.CRUDService;
import vajracode.calocal.shared.service.UserService;

public class UserRow extends CRUDRow<UserData> {
	
	@InjectService UserService service;
	
	@Inject private Provider<ChangePasswordModal> changePassProvider;

	private TextBox tbName, tbCal;
	private RoleListBox lbRole;
	private Widget lName, lCal, lRole;	
	
	public UserRow() {
	}
	
	@Override
	protected void update() {
		clear();		
		add(lName = getWidget(data.getName()));
		lName.addStyleName(Resources.INSTANCE.css().marginRight64());
		lName.setWidth("100%");
		add(lCal = getWidget(String.valueOf(data.getDailyCalories())));		
		lCal.getElement().getStyle().setMarginRight(32, Unit.PX);
		add(lRole = getWidget(String.valueOf(data.getRole().name())));		
		lRole.setWidth("100px");
		lRole.getElement().getStyle().setMarginRight(8, Unit.PX);
		add(getEditButton());
	}

	@Override
	public void edit() {
		clear();	
		add(tbName = getNameEditField());
		tbName.setWidth("65%");
		add(tbCal = getCalEditField());
		add(lbRole = getRoleEditField());
		add(getChangePassButton());
		add(getFinishButton());
		add(getDeleteButton());
	}
	
	private Widget getChangePassButton() {
		return getIcoButton(IconType.ENHANCED_ENCRYPTION, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				changePassProvider.get().apply(data.getId()).show();				
			}
		});
	}

	private TextBox getNameEditField() {		
		return new TextBox(data.getName(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				data.setName(event.getValue());
			}
		});
	}
	
	private TextBox getCalEditField() {		
		return new TextBox(String.valueOf(data.getDailyCalories()), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				try {
					data.setDailyCalories(Integer.parseInt(event.getValue()));
				} catch (NumberFormatException e) {
					tbCal.setText(String.valueOf(data.getDailyCalories()));
				}
			}
		});
	}
	
	private RoleListBox getRoleEditField() {		
		return new RoleListBox(data.getRole(), new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {				
				data.setRole(Role.valueOf(event.getValue()));				
			}
		});
	}
	
	@Override
	protected CRUDService<UserData> getService() {
		return service;
	}
	
}
