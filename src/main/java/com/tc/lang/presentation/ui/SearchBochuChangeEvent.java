package com.tc.lang.presentation.ui;

import java.util.EventObject;

public class SearchBochuChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7347148181623728405L;
	private long id;

	public SearchBochuChangeEvent(Object source, SearchItem item) {
		super(source);
		this.id = item.getId();
	}

	public long getId() {
		return id;
	}

}
