package com.tc.lang.presentation.sound;

/**
 * @author congnh
 */
public class MainSound {

	private SoundPlayModel soundPlayModel;
	private SoundPlayControl soundPlayControl;

	public MainSound(){
		soundPlayModel = new SoundPlayModel();
		soundPlayControl = new SoundPlayControl();
	}

	public SoundPlayModel getSoundPlayModel() {
		return soundPlayModel;
	}

	public void start() {
		soundPlayModel.addSoundChangeListener(soundPlayControl);
	}
}
