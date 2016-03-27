package vajracode.calocal.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialTitle;
import vajracode.calocal.client.framework.CommonView;


public class MainView extends CommonView<MainPresenter> {

	private static ViewUiBinder uiBinder = GWT.create(ViewUiBinder.class);
	interface ViewUiBinder extends UiBinder<Widget, MainView> {}	
	
	@UiField MaterialTitle title;	

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));		
	}	
		
	public void reset() {
		title.setDescription("");
	}
	
		
}
