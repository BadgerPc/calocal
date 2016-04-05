package vajracode.calocal.client.framework;

import javax.inject.Singleton;

/**
 * Singleton, holding the application's event bus
 *
 */
@Singleton
public class EventBusHolder {

	private CalocalEventBus eventBus;
	
	public EventBusHolder() {	
	}
	
	public void set(CalocalEventBus eventBus) {
		this.eventBus = eventBus;
	}

	public CalocalEventBus getEventBus() {
		return eventBus;
	}

	public CalocalEventBus getTokenGenerator() {
		eventBus.setTokenGenerationModeForNextEvent();
		return eventBus;
	}

}
