package vajracode.calocal.client.modals;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.*;

import fr.putnami.pwt.core.mvp.client.View;
import gwt.material.design.client.ui.*;
import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.FlatButton;

public class CommonModal extends Composite implements View {

	private static ModalContentUiBinder uiBinder = GWT.create(ModalContentUiBinder.class);
	interface ModalContentUiBinder extends UiBinder<MaterialPanel, CommonModal> {}
	
	 //private final CssTypeMixin<ModalType, MaterialModal> typeMixin = new CssTypeMixin<>(this);
	 private int inDuration = 300;
	 private int outDuration = 200;
	 private boolean dismissable = false;
	 private double opacity = 0.5;
	
	@UiField protected MaterialModalContent content;
	@UiField protected MaterialModalFooter footer;
	private MaterialTitle mtitle;
	private HTML mbody;

	public CommonModal() {
		initWidget(uiBinder.createAndBindUi(this));
		addStyleName("modal");
	}
	
	public CommonModal(String title, String body) {	
		this();
		addHeader(title);
		addBody(body);						
	}

	public void addHeader(String title) {
		if (title == null) return;
		createTitle();
		mtitle.setTitle(title);			
	}

	private void createTitle() {
		if (mtitle == null) {
			mtitle = new MaterialTitle();
			content.add(mtitle);
		}
	}
	
	private void createBody() {
		if (mbody == null) {
			mbody = new HTML();
			content.add(mbody);
		}
	}

	public void addBody(String body) {
		if (body == null) return;		
		createBody();
		mbody.setHTML(body);
	}

	public Button addButtonOk() {
		return addButtonClose("OK");		
	}

	public Button addButtonClose(String string) {
		return addButton(string, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});		
	}

	public Button addButton(String text, ClickHandler handler) {
		Button ok = new FlatButton(text, handler);				
		addToFooter(ok);			
		return ok;
	}
	
	@UiChild(tagname="footer")
	public void addToFooter(Widget w) {		
		footer.add(w);
	}

	public Button addButton(String text, final Command command) {
		return addButton(text, new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				command.execute();
			}
		});
	}

	public void show() {
		RootPanel.get().add(this);
		openModal();
	}

	@UiChild(tagname="body")
	public void add(Widget body) {
		content.add(body);
	}

	public void hide() {
		removeFromParent();
		closeModal();
	}
	
	/**
     * Open the modal programatically
     * 
     * <p>
     * Note: the MaterialModal component must be added to the document before
     * calling this method. When declaring this modal on a UiBinder file, the
     * MaterialModal is already added, but if you call it using pure Java, you
     * must add it to a container before opening the modal. You can do it by
     * calling, for example:
     * </p>
     * 
     * <pre>
     * MaterialModal modal = new MaterialModal();
     * RootPanel.get().add(modal);
     * </pre>
     * 
     * @throws IllegalStateException
     *             If the MaterialModal is not added to the document
     */
    public void openModal() {
        // the modal must be added to the document before opening
        if (this.getParent() == null) {
            throw new IllegalStateException(
                "The MaterialModal must be added to the document before calling openModal().");
        }
        openModal(getElement(), opacity, dismissable, inDuration, outDuration);
    }

    /**
     * Open modal with additional properties
     * 
     * @param e
     *            - Modal Component
     * @param opacity
     *            - Opacity of modal background
     * @param dismissable
     *            - Modal can be dismissed by clicking outside of the modal
     * @param inDuration
     *            - Transition in Duration
     * @param outDuration
     *            - Transition out Duration
     */
    private native void openModal(Element e, double opacity, boolean dismissable, int inDuration, int outDuration) /*-{
        var obj = this;
        $wnd.jQuery(e).openModal({
            opacity: opacity,
            dismissible: dismissable,
            in_duration: inDuration,
            out_duration: outDuration,
            complete: function () {  }
        });
    }-*/;
    
    private void onNativeClose(boolean autoClosed) {
        //CloseEvent.fire(this, this, autoClosed);
    }

    /**
     * Close the modal programatically. It is the same as calling
     * {@link #closeModal(boolean)} with <code>false</code> as parameter.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #openModal()}.
     * </p>
     * 
     */
    public void closeModal() {
        closeModal(false);
    }

    /**
     * Close the modal programatically.
     * <p>
     * Note: you may need to remove it MaterialModal from the document if you
     * are not using UiBinder. See {@link #openModal()}.
     * </p>
     * 
     * @param autoClosed
     *            Flag indicating if the modal was automatically dismissed
     * 
     * @see CloseEvent
     */
    public void closeModal(boolean autoClosed) {
        closeModal(getElement(), autoClosed);
    }

    private native void closeModal(Element e, boolean autoClosed) /*-{
        var obj = this;
        $wnd.jQuery(e).closeModal({
            complete: function () { }
        });
    }-*/;
}
