package com.tc.lang.presentation.ui;

import com.tc.lang.application.Info;

import java.util.EventObject;

public class SelectedInfoChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5580630744125986270L;
	private Info info;

	public SelectedInfoChangeEvent(Object source, Info info) {
		super(source);
		this.info = info;
	}

	public Info getInfo() {
		return info;
	}

}
