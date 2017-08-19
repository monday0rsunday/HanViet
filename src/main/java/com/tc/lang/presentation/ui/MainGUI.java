package com.tc.lang.presentation.ui;

import com.tc.lang.application.DatabaseControl;
import com.tc.lang.infrastructure.dic.DictionaryPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author congnh
 */
public class MainGUI {

	private SearchPanel searchPan;
	private DictionaryPanel dicPan;
	private JMenuBar menuBar;
	private JFrame mainFrame;
	private DatabaseControl dbControl;
	private SearchBochuModel sbModel = new SearchBochuModel();
	private SelectedInfoModel siModel = new SelectedInfoModel();
	private SearchModel sModel = new SearchModel();

	public MainGUI(){
		sbModel = new SearchBochuModel();
		siModel = new SelectedInfoModel();
		sModel = new SearchModel();
	}

	public void setDbControl(DatabaseControl dbControl){
		this.dbControl = dbControl;
	}

	public SearchModel getSearchModel(){
		return sModel;
	}

	public SelectedInfoModel getSelectedInfoModel(){
		return siModel;
	}

	public void start(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				searchPan = new SearchPanel(sModel, sbModel,
						dbControl);
				sbModel.addSearchBochuChangeListener(dbControl);
				siModel.addSelectedInfoChangeListener(searchPan);

				dicPan = new DictionaryPanel();

				menuBar = new JMenuBar();
				JMenu fMenu = new JMenu("Tệp tin");
				JMenu hMenu = new JMenu("Trợ giúp");
				menuBar.add(fMenu);
				menuBar.add(hMenu);

				mainFrame = new JFrame();
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
