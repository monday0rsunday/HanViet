package com.tc.lang.hv.dic;

public class Entry {
	private String phonthe;
	private String gianthe;
	private String binham;
	private String hanviet;
	private String nghia;
	private String source;

	public String getPhonthe() {
		return phonthe;
	}

	public void setPhonthe(String phonthe) {
		this.phonthe = phonthe;
	}

	public String getGianthe() {
		return gianthe;
	}

	public void setGianthe(String gianthe) {
		this.gianthe = gianthe;
	}

	public String getBinham() {
		return binham;
	}

	public void setBinham(String binham) {
		this.binham = binham;
	}

	public String getHanviet() {
		return hanviet;
	}

	public void setHanviet(String hanviet) {
		this.hanviet = hanviet;
	}

	public String getNghia() {
		return nghia;
	}

	public void setNghia(String nghia) {
		this.nghia = nghia;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String toString() {
		return getPhonthe() + "\t" + getGianthe() + "\t" + getBinham() + "\t"
				+ getNghia();
	}
}
