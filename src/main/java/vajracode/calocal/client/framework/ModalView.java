package vajracode.calocal.client.framework;

import vajracode.calocal.client.modals.CommonModal;

public abstract class ModalView extends CommonView {

	private CommonModal modal;

	public abstract CommonModal constructModal();
	
	public ModalView() {
		modal = constructModal();
	}
	
	public void show() {
		reset();
		modal.show();
	}

	public void reset() {
	}
	
}
