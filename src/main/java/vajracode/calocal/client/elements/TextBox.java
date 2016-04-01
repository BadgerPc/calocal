package vajracode.calocal.client.elements;

import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialTextBox;

public class TextBox extends MaterialTextBox {
	
	public TextBox() {
	}
	
	public TextBox(String text) {
		this();
		setText(text);
		
	}

	public TextBox(String text, ValueChangeHandler<String> vch) {
		this(text);
		addValueChangeHandler(vch);		
	}

	public int getInt() {
		return Integer.parseInt(getText());
	}

	@Override
	public void setType(InputType type) {
		super.setType(type);
		removeStyleName("validate");
	}
}
