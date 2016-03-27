package vajracode.calocal.client.framework;

import com.google.gwt.user.client.ui.Widget;

import vajracode.calocal.client.root.BeforeAttach;

public abstract class LazyCommonView extends CommonView implements BeforeAttach {
	
	public void beforeAttach() {
		initView();
	}

	public void initView() {
		if (getWidget() != null) return;		
		initWidget(getUi());
	}

	protected abstract Widget getUi();

}
