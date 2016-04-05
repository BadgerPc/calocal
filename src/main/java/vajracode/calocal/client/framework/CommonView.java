package vajracode.calocal.client.framework;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Composite;
import com.mvp4g.client.view.ReverseViewInterface;

import fr.putnami.pwt.core.mvp.client.View;

/**
 * Base for the main widget. Has injected event bus and presenter.
 *
 */
public class CommonView<T extends CommonPresenter> extends Composite 
	implements View, ReverseViewInterface<T> {

	@Inject protected EventBusHolder bus;
	
	private T presenter;
	
	public CommonView() {
	}

	@Override
	public void setPresenter(T presenter) {
		this.presenter = presenter; 
	}

	@Override
	public T getPresenter() {
		return presenter;
	}
}
