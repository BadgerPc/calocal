package vajracode.calocal.client.root;

/**
 * Applicable to the main widget, attached via EvenBus.setBody(). 
 *
 */
public interface BeforeAttach {

	/**
	 * Executed before attachment
	 */
	void beforeAttach();
}
