package vajracode.calocal.client.elements;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AnchorPanel extends SimplePanel {
	
    private static final String HREF = "href";
	private static final String POINTER_EVENTS = "pointerEvents";

	public AnchorPanel() {
        super(DOM.createAnchor());
        setTargetSelf();
    }

	public AnchorPanel(Widget w, String href) {
		this();
		setWidget(w);
		setHref(href);
		setTargetBlank();
	}
	
	public void setTargetSelf() {
		setTarget("_self");
	}
	
	public void setTargetBlank() {
		setTarget("_blank");
	}

    public void setToken(String token) {
		setHref("#" + token);
	}
    
    public void setHref(String href) {
        getElement().setAttribute(HREF, href);
    }

    public String getHref() {
        return getElement().getAttribute(HREF);
    }

    public void setTarget(String frameName) {
        getElement().setAttribute("target", frameName);
    }

	public void setEnabled(boolean b) {
		if (b)
			getElement().getStyle().clearProperty(POINTER_EVENTS);
		else
			getElement().getStyle().setProperty(POINTER_EVENTS, "none");
	}
}
