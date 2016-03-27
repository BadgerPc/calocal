package vajracode.calocal.client.utils;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window.Location;

public class NativeUtils {

	public static void removeLoader() {
		Element e = DOM.getElementById("loader");
		if (e != null)
			e.removeFromParent();
	}
	
	public static boolean hasLoader() {
		return DOM.getElementById("loader") != null;		
	}

	public static void cleanLocationUrl() {
		Location.assign(getUrlWithoutQuery());
	}
	
	private static String getUrlWithoutQuery() {
		String href = Location.getHref();
		int i = href.indexOf("?");		
		return i > 0 ? href.substring(0, i) : href;
	}


}
