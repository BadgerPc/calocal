package vajracode.calocal.client.elements;

public class Title extends AdvancedLabel {

	public Title() {
		setStyleName("title");
	}

	public Title(String text) {
		this();
		setText(text);
	}

	public Title(String text, String style) {
		this(text);
		addStyleName(style);
	}
	
	

}
