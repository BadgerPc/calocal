package vajracode.calocal.client.elements;

public class Subheader extends Header {

	public Subheader() {
		this("");
	}
	
	public Subheader(String text) {
		super("h6", text);
		//add(label = new Label(text));
	}
	
}
