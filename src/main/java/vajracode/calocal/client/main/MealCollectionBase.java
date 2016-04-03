package vajracode.calocal.client.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;

import vajracode.calocal.client.auth.UserManager;
import vajracode.calocal.client.elements.ColFlexPanel;
import vajracode.calocal.shared.model.UserData;

public abstract class MealCollectionBase extends ColFlexPanel implements HasValueChangeHandlers<Integer> {

	@Inject UserManager userManager;
	
	private List<HandlerRegistration> handlers = new ArrayList<>();
	
	@Override
	protected void onLoad() {
		super.onLoad();
		handlers.add(userManager.addValueChangeHandler(new ValueChangeHandler<UserData>() {			
			@Override
			public void onValueChange(ValueChangeEvent<UserData> event) {
				update();
			}
		}));
	}

	@Override
	protected void onUnload() {
		super.onUnload();
		for (HandlerRegistration hr : handlers)
			hr.removeHandler();		
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {		
		HandlerRegistration ret = addHandler(handler, ValueChangeEvent.getType());
		handlers.add(ret);
		update();
		return ret;
	}
	
	public void update() {
		ValueChangeEvent.fire(this, getValue());		
	}

	public abstract int getValue();
}
