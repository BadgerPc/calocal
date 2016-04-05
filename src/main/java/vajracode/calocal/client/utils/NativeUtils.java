package vajracode.calocal.client.utils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class NativeUtils {

	public static void removeLoader() {
		Element e = DOM.getElementById("loader");
		if (e != null)
			e.removeFromParent();
	}
	
	public static boolean hasLoader() {
		return DOM.getElementById("loader") != null;		
	}


}
