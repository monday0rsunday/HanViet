package com.tc.lang;

import com.tc.lang.application.DatabaseControl;
import com.tc.lang.presentation.sound.MainSound;
import com.tc.lang.presentation.ui.MainGUI;

/**
 * @author congnh
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainSound mainSound = new MainSound();

		MainGUI mainUI = new MainGUI();

		DatabaseControl dbControl = new DatabaseControl("./dic/chinese.db",
				mainUI.getSearchModel(), mainUI.getSelectedInfoModel(), mainSound.getSoundPlayModel());
		mainUI.setDbControl(dbControl);

		mainSound.start();
		mainUI.start();
	}

}
