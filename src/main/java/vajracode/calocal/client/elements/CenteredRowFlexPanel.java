package vajracode.calocal.client.elements;

import vajracode.calocal.client.resources.Resources;

public class CenteredRowFlexPanel extends RowFlexPanel {
	
	public CenteredRowFlexPanel() {
		addStyleName(Resources.INSTANCE.css().alignItemsCenter());
		addStyleName(Resources.INSTANCE.css().justifyContentCenter());
	}

}
