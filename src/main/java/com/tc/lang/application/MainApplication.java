package com.tc.lang.application;

import com.tc.lang.presentation.sound.MainSound;
import com.tc.lang.presentation.ui.MainGUI;

/**
 * @author congnh
 */
public class MainApplication {

	public void start(){
		MainSound mainSound = new MainSound();

		MainGUI mainUI = new MainGUI();

		DatabaseControl dbControl = new DatabaseControl("./dic/chinese.db",
				mainUI.getSearchModel(), mainUI.getSelectedInfoModel(), mainSound.getSoundPlayModel());
		mainUI.setDbControl(dbControl);

		mainSound.start();
		mainUI.start();
	}
}
