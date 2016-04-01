package vajracode.calocal.client.elements;

import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.constants.ButtonType;

public class FlatButton extends Button {

	public FlatButton() {
		super();
		setType(ButtonType.FLAT);
	}

	public FlatButton(String text, ClickHandler clickHandler) {
		super(text, clickHandler);
		setType(ButtonType.FLAT);
	}

	public FlatButton(String text) {
		super(text);
		setType(ButtonType.FLAT);
	}

	
	
}
