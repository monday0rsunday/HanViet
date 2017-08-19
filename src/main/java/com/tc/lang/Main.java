package com.tc.lang;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.tc.lang.application.DatabaseControl;
import com.tc.lang.infrastructure.dic.DictionaryPanel;
import com.tc.lang.presentation.sound.SoundPlayControl;
import com.tc.lang.presentation.sound.SoundPlayModel;
import com.tc.lang.presentation.ui.SearchBochuModel;
import com.tc.lang.presentation.ui.SearchItem;
import com.tc.lang.presentation.ui.SearchModel;
import com.tc.lang.presentation.ui.SearchPanel;
import com.tc.lang.presentation.ui.SelectedInfoModel;

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

				SearchBochuModel sbModel = new SearchBochuModel();
				SelectedInfoModel siModel = new SelectedInfoModel();
				SearchModel sModel = new SearchModel();
				DatabaseControl dbControl = new DatabaseControl("./dic/chinese.db",
						sModel, siModel, spModel);
				SearchPanel searchPan = new SearchPanel(sModel, sbModel,
						dbControl);
				sbModel.addSearchBochuChangeListener(dbControl);
				siModel.addSelectedInfoChangeListener(searchPan);
				
				DictionaryPanel dicPan = new DictionaryPanel();

				JMenuBar menuBar = new JMenuBar();
				JMenu fMenu = new JMenu("Tệp tin");
				JMenu hMenu = new JMenu("Trợ giúp");
				menuBar.add(fMenu);
				menuBar.add(hMenu);

				JFrame mainFrame = new JFrame();
				// layout main
				mainFrame.setJMenuBar(menuBar);
				JTabbedPane tabbedPane = new JTabbedPane();
				tabbedPane.add(searchPan,"Tìm từ Hán Việt");
				tabbedPane.add(dicPan,"Tra từ");
				mainFrame.add(tabbedPane, BorderLayout.CENTER);
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
