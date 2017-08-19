package com.tc.lang;

import java.util.EventObject;

public class SoundChangeEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 598356459741373380L;

	private byte[] sound;

	public SoundChangeEvent(Object source, byte[] sound) {
		super(source);
		this.sound = sound;
	}

	public byte[] getSound() {
		return sound;
	}

}
