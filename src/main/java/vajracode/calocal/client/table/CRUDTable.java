package vajracode.calocal.client.table;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import vajracode.calocal.client.elements.ColFlexPanel;
import vajracode.calocal.shared.model.Data;

@SuppressWarnings("rawtypes")
public abstract class CRUDTable<T extends Data, R extends CRUDRow> extends ColFlexPanel {

	@Inject
	protected Provider<R> rowProvider;
	
	public CRUDTable() {
		setWidth("100%");
	}
	
	public CRUDTable<T, R> apply(List<T> data) {
		for (T m : data) {
			addToBegining(m);
		}
		return this;
	}
	
	public R addToBegining(T meal) {
		R r = getRow(meal);
		insert(r, 0);		
		update();
		return r;
	}
	
	public void update() {
	}

	public R addToEnd(T meal) {
		R r = getRow(meal);
		add(r);
		update();
		return r;
	}

	@SuppressWarnings("unchecked")
	private R getRow(T data) {
		R r = (R) rowProvider.get().apply(data);
		r.setTable(this);
		return r;
	}

	public R addAndEdit(T meal) {
		R r = addToEnd(meal);
		r.edit();
		return r;
	}
	
}
