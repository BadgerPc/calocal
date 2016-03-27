package vajracode.calocal.client.elements;

import com.google.gwt.user.client.ui.Label;

public class AdvancedLabel extends Label {

	public AdvancedLabel() {
	}
	
	public AdvancedLabel(String text) {
		super(text);
	}
	
	public void setHTML(String html) {
		getElement().setInnerHTML(html);
	}
	
	public String getHTML() {
		return getElement().getInnerHTML();
	}	
	

}
