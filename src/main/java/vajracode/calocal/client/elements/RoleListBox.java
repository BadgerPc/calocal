package vajracode.calocal.client.elements;

import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.ui.MaterialListBox;
import vajracode.calocal.shared.model.Role;

public class RoleListBox extends MaterialListBox {

	public RoleListBox(Role role, ValueChangeHandler<String> handler) {
		for (Role r : Role.values())
			addItem(r.name());
		setSelectedValue(role.name());
		addValueChangeHandler(handler);
	}

}
