package vajracode.calocal.client.elements;

public class TitleLight extends AdvancedLabel {

	public TitleLight() {
		setStyleName("titleLight");
	}

	public TitleLight(String text) {
		this();
		setText(text);
	}

	public TitleLight(String text, String style) {
		this(text);
		addStyleName(style);
	}
	
	

}
