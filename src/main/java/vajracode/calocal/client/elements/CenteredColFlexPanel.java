package vajracode.calocal.client.elements;

import vajracode.calocal.client.resources.Resources;

public class CenteredColFlexPanel extends ColFlexPanel {
	
	public CenteredColFlexPanel() {
		addStyleName(Resources.INSTANCE.css().alignItemsCenter());
		addStyleName(Resources.INSTANCE.css().justifyContentCenter());
	}

}
