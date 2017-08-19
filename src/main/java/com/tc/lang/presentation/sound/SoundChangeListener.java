package com.tc.lang.presentation.sound;

import java.util.EventListener;

public interface SoundChangeListener extends EventListener {

	public void soundChanged(SoundChangeEvent evt);

}
