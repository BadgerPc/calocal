package vajracode.calocal.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.ui.MaterialButton;
import vajracode.calocal.client.resources.Resources;

public class Button extends MaterialButton {

	public static final String TYPE_FLAT = "flat";

	public Button() {
		this("", "", "light");		
	}

	public Button(String text, String type, String icon, String iconPosition, String size, String tooltip) {
		super(text, type, icon, iconPosition, size, tooltip);
	}

	public Button(String icon, String color, String type, String waves, String tooltip) {
		super(icon, color, type, waves, tooltip);
	}

	public Button(String text, String color, String waves) {
		super(text, color, waves);
		addStyleName("valign-wrapper");
		addStyleName(Resources.INSTANCE.css().marginStandard());		
	}

	public Button(String text) {
		this(text, "", "light");		
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
