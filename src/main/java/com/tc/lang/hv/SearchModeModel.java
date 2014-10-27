package com.tc.lang.hv;

import javax.swing.DefaultComboBoxModel;

public class SearchModeModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5304934885400499058L;

	private SearchModeChangeListener listener;

	public void addSearchModeChangeListener(SearchModeChangeListener listener) {
		this.listener = listener;
	}

	public void removeSoundChangeListener(SearchModeChangeListener listener) {
		this.listener = null;
	}

	public void fireSoundChangeListener() {
		if (listener != null) {
			SearchModeChangeEvent evt = new SearchModeChangeEvent(this,
					(SearchMode) getSelectedItem());
			listener.searchModeChanged(evt);
		}
	}

	@Override
	protected void fireContentsChanged(Object source, int index0, int index1) {
		super.fireContentsChanged(source, index0, index1);
		if (index0 == -1 && index1 == -1) {// change selected item
			fireSoundChangeListener();

		}
	}

}
