package com.tc.lang.presentation.ui;

import javax.swing.DefaultComboBoxModel;

public class SearchBochuModel extends DefaultComboBoxModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 488479505345413207L;
	private SearchBochuChangeListener listener;

	public void addSearchBochuChangeListener(SearchBochuChangeListener listener) {
		this.listener = listener;
	}

	public void removeSearchBochuChangeListener(
			SearchBochuChangeListener listener) {
		this.listener = null;
	}

	public void fireSearchBochuChangeListener() {
		if (listener != null) {
			SearchBochuChangeEvent evt = new SearchBochuChangeEvent(this,
					(SearchItem) getSelectedItem());
			listener.searchBochuChanged(evt);
		}
	}

	@Override
	protected void fireContentsChanged(Object source, int index0, int index1) {
		super.fireContentsChanged(source, index0, index1);
		if (index0 == -1 && index1 == -1) {// change selected item
			fireSearchBochuChangeListener();

		}
	}
}
