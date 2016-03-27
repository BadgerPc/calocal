package vajracode.calocal.client.elements;

public class Body1 extends AdvancedLabel {

	public Body1() {
		setStyleName("body1");
	}

	public Body1(String text) {
		this();
		setText(text);
	}

	public Body1(String text, String style) {
		this(text);
		addStyleName(style);
	}
	
	

}
