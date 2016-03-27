package vajracode.calocal.client.modals;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.mvp.client.View;
import gwt.material.design.client.ui.*;
import vajracode.calocal.client.elements.Button;
import vajracode.calocal.client.elements.FlatButton;

public class CommonModal extends Composite implements View {

	private static ModalContentUiBinder uiBinder = GWT.create(ModalContentUiBinder.class);
	interface ModalContentUiBinder extends UiBinder<MaterialPanel, CommonModal> {}
	
	@UiField protected MaterialModalContent content;
	@UiField protected MaterialModalFooter footer;
	private MaterialTitle mtitle;
	private HTML mbody;

	public CommonModal() {
		initWidget(uiBinder.createAndBindUi(this));
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
				MaterialModal.closeModal();
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
		MaterialModal.showModal(true, this);
	}

	@UiChild(tagname="body")
	public void add(Widget body) {
		content.add(body);
	}

	public void hide() {
		MaterialModal.closeModal();
	}
}
