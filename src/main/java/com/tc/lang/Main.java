package com.tc.lang;

import com.tc.lang.application.DatabaseControl;
import com.tc.lang.presentation.MainSound;
import com.tc.lang.presentation.MainGUI;

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

		DatabaseControl databaseControl = new DatabaseControl("./dic/chinese.db",
				mainUI.getSearchModel(), mainUI.getSelectedInfoModel(), mainSound.getSoundPlayModel());
		mainUI.setDbControl(databaseControl);

		mainSound.start();
		mainUI.start();
	}

}
