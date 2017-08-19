package com.tc.lang.presentation.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DictionaryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5294373929513056733L;

	private JList suggestList = new JList();
	private JTextField searchField;

	public DictionaryPanel() {
		initComponents();
	}

	private void initComponents() {
		searchField = new JTextField();

		searchField.addKeyListener(new KeyAdapter() {
			String lastSearch = null;

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == 40) {// Down arrow
					// TODO
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							// System.out.println(suggestPopup
							// .requestFocusInWindow());
							suggestList.requestFocusInWindow();
						}
					});
				}
				if (!searchField.getText().equals(lastSearch)) {
					// TODO
					lastSearch = searchField.getText();
				}
			}
		});

		// layout
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		add(searchField, BorderLayout.PAGE_START);
		add(suggestList, BorderLayout.PAGE_END);
		revalidate();
	}

}
