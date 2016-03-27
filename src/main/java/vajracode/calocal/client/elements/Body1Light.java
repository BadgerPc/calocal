package vajracode.calocal.client.elements;

public class Body1Light extends AdvancedLabel {

	public Body1Light() {
		setStyleName("body1Light");
	}

	public Body1Light(String text) {
		this();
		setHTML(text);
	}

	public Body1Light(String text, String style) {
		this();
		setHTML(text);
		addStyleName(style);
	}

	

}
