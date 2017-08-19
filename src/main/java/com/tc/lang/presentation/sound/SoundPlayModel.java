package com.tc.lang.presentation.sound;

public class SoundPlayModel {

	private SoundChangeListener listener;
	private byte[] sound;

	public void addSoundChangeListener(SoundChangeListener listener) {
		this.listener = listener;
	}

	public void removeSoundChangeListener(SoundChangeListener listener) {
		this.listener = null;
	}

	public void fireSoundChangeListener() {
		if (listener != null) {
			SoundChangeEvent evt = new SoundChangeEvent(this, getSound());
			listener.soundChanged(evt);
		}
	}

	public byte[] getSound() {
		return sound;
	}

	public void setSound(byte[] sound) {
		this.sound = sound;
		fireSoundChangeListener();
	}

}
