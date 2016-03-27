package vajracode.calocal.shared.constants;

import com.google.gwt.dom.client.Style.Unit;

public class HTMLConstants {

	public static final String BR = "<br/>";
	
	public static final String IMP = " !important";

	public static final int THUMB_WIDTH = 300;

	public static final int THUMB_HEIGHT = 300;

	public static final String HUNDRED_PCT = "100%";

	public static final String STYLE_HIDE_TAB_LABELS = "hideTabLabels";

	public static final String INNER_SIDE_PADDING = "innerSidePadding";		
	
	private static String getImg(String url) {
		return getImgWithStyle(url, null);
	}
	
	private static String getImg(String url, int size) {
		return getImgWithStyle(url, "text-size: " + size + Unit.PX.toString() + ";");
	}
	
	private static String getImgWithMarginRight(String url, int margin) {
		return getImgWithStyle(url, "margin-right: " + margin + Unit.PX.toString() + ";");
	}
	
	private static String getImgWithStyle(String url, String style) {
		return "<i class=\"p-icon icon-" + url + "\" " + (style != null ? ("style=\"" + style + "\"") : "") + "/>";
	}

	public static String getDiv(String text, String style) {		
		return "<div class=\"" + style + "\">" + text +"</div>";
	}
	
	public static String getTokenAnchor(String text, String token) {	
		return getTokenAnchor(text, token, null);
	}
	
	public static String getTokenAnchor(String text, String token, String cls) {		
		return "<a " + (cls != null ? "class=\"" + cls + "\"" : "") + " target=\"_self\" href=\"#" + token + "\">" + text + "</a>";
	}

	public static String getAnchor(String title, String url) {
		return getAnchor(title, url, true);
	}
	
	public static String getAnchor(String title, String url, boolean newWindow) {
		return "<a target=\"" + (newWindow ? "_blank" : "_self") + "\" href=\"" + url + "\">" + title + "</a>";
	}
	
	public static String getAnchorToken(String title, String url) {
		return getAnchorToken(title, url, false);
	}
	
	public static String getAnchorToken(String title, String url, boolean newWindow) {
		return getAnchor(title, "#" + url, newWindow);
	}
	
	public static String getImage(String url) {		
		return "<img src=\"" + url + "\" />"; 
	}
	
	public static String getImage(String url, String cls) {		
		return "<img src=\"" + url + "\" class=\"" + cls + "\"/>"; 
	}

	public static String getHeader(String text, int level, String style) {		
		return "<h" + level + " class=\"" + style + "\">" + text +"</h" + level + ">";
	}
	
	public static String getHeader(String text, int level) {
		return "<h" + level + ">" + text +"</h" + level + ">";
	}

	public static String italize(String text) {		
		return "<i>" + text + "</i>";
	}

	public static String underline(String text) {
		return "<u>" + text + "</u>";
	}
	
	public static String bolden(String text) {		
		return "<b>" + text + "</b>";
	}

	public static String getLi(String text) {		
		return "<li>" + text + "</li>";
	}

	public static String fixToken(String token) {
		if (token.startsWith("#")) return token;
		if (token.startsWith("!")) return "#" + token;
		return "#!" + token;
	}

	public static String liize(String html) {	
		return "<li>" + html + "</li>";
	}

	public static String smallize(String html) {		
		return "<small>" + html + "</small>";
	}	


}
