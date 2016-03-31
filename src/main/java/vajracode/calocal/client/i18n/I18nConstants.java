package vajracode.calocal.client.i18n;

import com.google.gwt.i18n.client.Constants;

public interface I18nConstants extends Constants{

	@DefaultStringValue("Ok")
	String ok();

	@DefaultStringValue("Cancel")
	String cancel();

	@DefaultStringValue("Application updated: refresh this page")
	String updateApplication();

	@DefaultStringValue("Network error")
	String networkError();
	
	@DefaultStringValue("Unknown error")
	String unknownError();

	@DefaultStringValue("Welcome to Calories Calculator")
	String pleaseSignIn();
	
	@DefaultStringValue("Sign in to proceed please")
	String needBeAuth();
	
	@DefaultStringValue("Not found")
	String notFound();
	
	@DefaultStringValue("Wrong field value")
	String badRequest();
	
	@DefaultStringValue("Such entity exists already")
	String conflict();
	
	@DefaultStringValue("Wrong username/password")
	String badCredentials();
	
	@DefaultStringValue("Meal description")
	String mealName();

	@DefaultStringValue("Today")
	String today();

	@DefaultStringValue("All time")
	String allTime();

	@DefaultStringValue("Hi! What where your meals today?")
	String todayWelcome();

	@DefaultStringValue("No access")
	String noAccess();


	

	

	

	
}
