package vajracode.calocal.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;

public class FlatButton extends Button {

	public FlatButton() {
		super();
		setType(TYPE_FLAT);
	}

	public FlatButton(String text, ClickHandler clickHandler) {
		super(text, clickHandler);
		setType(TYPE_FLAT);
	}

	public FlatButton(String text) {
		super(text);
		setType(TYPE_FLAT);
	}

	
	
}
