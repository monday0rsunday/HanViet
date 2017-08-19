package com.tc.lang.presentation.ui;

public class SearchItem {

	private String key;
	private long id;

	public SearchItem() {

	}

	public SearchItem(String key, long id) {
		setId(id);
		setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String toString() {
		return key;
	}
}
