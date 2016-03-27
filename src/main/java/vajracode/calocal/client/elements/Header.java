package vajracode.calocal.client.elements;

import com.google.gwt.user.client.ui.HTMLPanel;

public class Header extends HTMLPanel {

	public Header() {
		this("");
	}
	
	public Header(String text) {
		super("h5", text);
		//add(label = new Label(text));
	}

	public Header(String tag, String html) {
		super(tag, html);
	}

	public void setText(String text) {
		//label.setText(text);
		getElement().setInnerText(text);
	}

}
