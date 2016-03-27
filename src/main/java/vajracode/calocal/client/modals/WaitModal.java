package vajracode.calocal.client.modals;

import gwt.material.design.client.ui.MaterialLoader;
import vajracode.calocal.client.elements.LoaderCircle;

public class WaitModal {

	private static LoaderCircle loader;
	private static boolean visible;

	public static void show() {
		setVisible(true);		
	}

	private static void setVisible(boolean b) {
		visible = b;
		updateLoader();
		MaterialLoader.showLoading(b);
	}

	private static void updateLoader() {
		setLoaderVisible(!visible);
	}

	private static void setLoaderVisible(boolean b) {
		if (loader != null)
			loader.setVisible(b);
	}

	public static void hide() {
		setVisible(false);
	}

	public static LoaderCircle getLoaderCircle() {
		if (loader != null)
			loader.removeFromParent();
		loader = new LoaderCircle();
		updateLoader();
		return loader;
	}

}
