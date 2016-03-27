package vajracode.calocal.client.elements;

import com.google.gwt.user.client.ui.FlowPanel;

import vajracode.calocal.client.resources.Resources;

public class ColFlexPanel extends FlowPanel {
	
	public ColFlexPanel() {
		addStyleName(Resources.INSTANCE.css().flexVertical());
	}
	
	public ColFlexPanel(String html) {
		this();
	}

	
}
