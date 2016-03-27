package vajracode.calocal.client.elements;

public class Heading extends AdvancedLabel {

	public Heading() {
		setStyleName("heading");
	}

	public Heading(String text) {
		this();
		setText(text);
	}

	public Heading(String text, String style) {
		this(text);
		addStyleName(style);
	}
	
	

}
