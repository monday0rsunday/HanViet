package com.tc.lang.presentation.ui;

public class Info {

	private long id;
	private String[] binhams;
	private String hanviet;
	private String phonthe;
	private String nghia;
	private byte[] stroke;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBinhams(String[] binhams) {
		this.binhams = binhams;
	}

	public String[] getBinhams() {
		return binhams;
	}

	public String getHanviet() {
		return hanviet;
	}

	public void setHanviet(String hanviet) {
		this.hanviet = hanviet;
	}

	public String getPhonthe() {
		return phonthe;
	}

	public void setPhonthe(String phonthe) {
		this.phonthe = phonthe;
	}

	public String getNghia() {
		return nghia;
	}

	public void setNghia(String nghia) {
		this.nghia = nghia;
	}

	public byte[] getStroke() {
		return stroke;
	}

	public void setStroke(byte[] stroke) {
		this.stroke = stroke;
	}

	public String toString() {
		return phonthe + ":" + hanviet + ":" + nghia + ":" + binhams;
	}
}
