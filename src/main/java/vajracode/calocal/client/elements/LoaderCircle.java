package vajracode.calocal.client.elements;

import com.google.gwt.user.client.ui.HTML;

public class LoaderCircle extends HTML {
	
	public LoaderCircle() {
		super("<div class=\"spinner-layer spinner-red-only\" style=\"width:50px;height:50px;\"><div class=\"circle-clipper left\"><div class=\"circle\"></div></div><div class=\"gap-patch\"><div class=\"circle\"></div></div><div class=\"circle-clipper right\"><div class=\"circle\"></div></div></div>");
		addStyleName("setpreloader-wrapper active");
	}

}
