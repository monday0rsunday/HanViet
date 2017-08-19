package com.tc.lang.presentation;

import com.tc.lang.presentation.sound.SoundPlayModel;
import com.tc.lang.presentation.sound.SoundPlayer;

/**
 * @author congnh
 */
public class MainSound {

	private SoundPlayModel soundPlayModel;
	private SoundPlayer soundPlayControl;

	public MainSound(){
		soundPlayModel = new SoundPlayModel();
		soundPlayControl = new SoundPlayer();
	}

	public SoundPlayModel getSoundPlayModel() {
		return soundPlayModel;
	}

	public void start() {
		soundPlayModel.addSoundChangeListener(soundPlayControl);
	}
}
