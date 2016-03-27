package vajracode.calocal.client.elements;

import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ParagraphPanel extends HTMLPanel {

	public ParagraphPanel() {
		super(ParagraphElement.TAG, "");
	}
	
	public ParagraphPanel(String html) {
		super(ParagraphElement.TAG, html);
	}
	
	public void setText(String text) {
		getElement().setInnerText(text);
	}

	public void setHTML(String html) {
		getElement().setInnerHTML(html);
	}
	
}
