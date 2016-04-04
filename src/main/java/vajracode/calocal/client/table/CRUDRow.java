package vajracode.calocal.client.table;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.inject.client.Injected;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import gwt.material.design.client.constants.IconType;
import vajracode.calocal.client.elements.*;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.shared.model.Data;
import vajracode.calocal.shared.service.CRUDService;

public abstract class CRUDRow<T extends Data> extends CenteredRowFlexPanel implements Injected {
	
	private CRUDTable table;
	
	protected T data;
	
	private Button bFinish, bDelete;
	
	public CRUDRow() {
		addStyleName(Resources.INSTANCE.css().justifyContentStart());
	}
	

	public void setTable(CRUDTable table) {
		this.table = table;
	}
	
	public void updateTable(){
		table.update();
	}
	
	public CRUDRow<T> apply (T data) {
		this.data = data;
		update();
		return this;
	}

	protected abstract void update();
	
	public abstract void edit();
	
	protected Widget getEditButton() {
		return getIcoButton(IconType.MODE_EDIT, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				edit();
			}
		});
	}

	protected Button getIcoButton(IconType icon, ClickHandler clickHandler) {		
		Button b = new FlatButton();		
		b.setIconType(icon);
		b.addClickHandler(clickHandler);
		b.setWidth("60px");
		return b;
	}

	protected Widget getWidget(String text) {
		return new Body1(text);		
	}
	
	protected Button getDeleteButton() {
		return bDelete = getIcoButton(IconType.DELETE, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bDelete.setEnabled(false);
				getService().delete(data.getId());
			}
		});
	}
	
	protected abstract CRUDService<T> getService();


	protected Button getFinishButton() {
		return bFinish = getIcoButton(IconType.DONE, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				bFinish.setEnabled(false);
				done();
			}
		});
	}

	protected void done() {
		getService().update(data.getId(), data);
	}
	
	@AsyncHandler
	public void onUpdate(T data) {
		this.data = data;
		update();
		updateTable();
	}
	
	@AsyncHandler
	public void onUpdateThrown(Throwable t){
		bFinish.setEnabled(true);
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}
	
	@AsyncHandler
	public void onDelete(Void v) {
		removeFromParent();
		updateTable();
	}
	
	@AsyncHandler
	public void onDeleteThrown(Throwable t){
		bDelete.setEnabled(true);
		ErrorManager.get().getErrorHandlers().get(0).handle(t);
	}


	public T getData() {
		return data;
	}



}
