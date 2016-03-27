package vajracode.calocal.client.elements;

import com.google.gwt.user.client.ui.FlowPanel;

import vajracode.calocal.client.resources.Resources;

public class RowFlexPanel extends FlowPanel {
	
	public RowFlexPanel() {
		addStyleName(Resources.INSTANCE.css().flexHorizontal());
	}
	
	public RowFlexPanel(String html) {
		this();
	}

	
}
