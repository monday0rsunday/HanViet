package com.tc.lang.hv;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				SoundPlayModel spModel = new SoundPlayModel();
				SoundPlayControl spControl = new SoundPlayControl();
				spModel.addSoundChangeListener(spControl);

				SearchModeModel smModel = new SearchModeModel();
				SearchBochuModel sbModel = new SearchBochuModel();
				SelectedInfoModel siModel = new SelectedInfoModel();
				SearchModel sModel = new SearchModel();
				DatabaseControl dbControl = new DatabaseControl("./chinese.db",
						sModel, siModel, spModel);
				SearchPanel searchPan = new SearchPanel(sModel, sbModel,
						dbControl);
				smModel.addSearchModeChangeListener(dbControl);
				sbModel.addSearchBochuChangeListener(dbControl);
				siModel.addSelectedInfoChangeListener(searchPan);

				JMenuBar menuBar = new JMenuBar();
				JMenu fMenu = new JMenu("Tệp tin");
				JMenu hMenu = new JMenu("Trợ giúp");
				menuBar.add(fMenu);
				menuBar.add(hMenu);

				JFrame mainFrame = new JFrame();
				// layout main
				mainFrame.setJMenuBar(menuBar);
				mainFrame.add(searchPan, BorderLayout.CENTER);
				// setup main
				mainFrame.setTitle("Từ điển Hán Việt");
				mainFrame.setSize(600, 400);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setVisible(true);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sbModel.addElement(new SearchItem("Tất cả", -1));
				for (SearchItem si : dbControl.loadBochu()) {
					sbModel.addElement(si);
				}
				sModel.setSelectedItem(sModel.getElementAt(0));
			}
		});
	}

}
