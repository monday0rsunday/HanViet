package com.tc.lang.hv;

import java.util.EventObject;

public class SearchModeChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7347148181623728405L;
	private SearchMode mode;

	public SearchModeChangeEvent(Object source, SearchMode mode) {
		super(source);
		this.mode = mode;
	}

	public SearchMode getSearchMode() {
		return mode;
	}

}
