package vajracode.calocal.client.modals;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;


public class ModalUtils {

	public static void show(String title) {
		show(title, (String)null);
	}

	public static void show(String title, String body) {
		final CommonModal m = new CommonModal(title, body);
		m.addButtonOk();		
		m.show();
	}

	public static void show(String title, Widget body) {		
		final CommonModal m = new CommonModal(title, null);
		m.add(body);
		m.addButtonOk();		
		m.show();
	}

	public static void showInitialError(String msg, String button) {
		final CommonModal m = new CommonModal(msg, null);
		m.addButton(button, new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				m.hide();
				Window.Location.reload();
			}
		});
		m.show();
	}

	public static void show(Widget body) {
		show(null, body);
	}


}
