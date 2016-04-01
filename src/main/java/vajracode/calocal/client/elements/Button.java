package vajracode.calocal.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.ui.MaterialButton;
import vajracode.calocal.client.resources.Resources;

public class Button extends MaterialButton {

	public Button() {		
	}

	public Button(String text) {
		setText(text);		
	}

	public Button(String text, ClickHandler clickHandler) {
		this(text);
		addClickHandler(clickHandler);
	}
	
	@Override
	public void setVisible(boolean visible) {
		//super.setVisible(visible);
		if (visible)
			removeStyleName(Resources.INSTANCE.css().displayNoneImportant());
		else
			addStyleName(Resources.INSTANCE.css().displayNoneImportant());			
	}
	
}
