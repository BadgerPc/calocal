package vajracode.calocal.client.elements;

import javax.inject.Inject;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;

import vajracode.calocal.client.auth.UserManager;
import vajracode.calocal.client.i18n.I18nConstants;
import vajracode.calocal.client.resources.Resources;
import vajracode.calocal.shared.model.UserData;

public class CalSummary extends HTML {

	@Inject UserManager userManager;
	@Inject I18nConstants msgs;
	private HandlerRegistration hr;
	private int sum;
	
	public CalSummary() {
		addStyleName(Resources.INSTANCE.css().summary()); 
	}
	
	public void update(int sum) {
		this.sum = sum;
		int daily = userManager.getUserData().getDailyCalories();
		setHTML("<h5 class=" + (sum > daily ? "red-text" : "green-text") + ">" 
				+ sum + "</h5> <h6>" + msgs.outOf() + "</h6> <h5>" + daily + "</h5>");
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		hr = userManager.addValueChangeHandler(new ValueChangeHandler<UserData>() {			
			@Override
			public void onValueChange(ValueChangeEvent<UserData> event) {
				update(sum);
			}
		});
	}

	@Override
	protected void onUnload() {
		super.onUnload();
		if (hr != null)
			hr.removeHandler();
	}
}
