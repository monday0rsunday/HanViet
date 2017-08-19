package com.tc.lang;

public class SelectedInfoModel {

	private SelectedInfoChangeListener listener;
	private Info selectedInfo;

	public void addSelectedInfoChangeListener(
			SelectedInfoChangeListener listener) {
		this.listener = listener;
	}

	public void removeSelectedInfoChangeListener(
			SelectedInfoChangeListener listener) {
		this.listener = null;
	}

	public void fireSelectedInfoChangeListener() {
		if (listener != null) {
			SelectedInfoChangeEvent evt = new SelectedInfoChangeEvent(this,
					getSelectedInfo());
			listener.selectedInfoChanged(evt);
		}
	}

	public Info getSelectedInfo() {
		return selectedInfo;
	}

	public void setSelectedInfo(Info selectedInfo) {
		this.selectedInfo = selectedInfo;
		fireSelectedInfoChangeListener();
	}

}
