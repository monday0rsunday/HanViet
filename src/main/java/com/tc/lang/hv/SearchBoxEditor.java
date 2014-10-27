package com.tc.lang.hv;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;

public class SearchBoxEditor implements ComboBoxEditor {

	private JTextField editor;
	private List<ActionListener> alList;

	public SearchBoxEditor() {
		editor = new JTextField();
		alList = new ArrayList<ActionListener>();
		editor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				ActionEvent evt = new ActionEvent(this, 2345, editor.getText());
				for (ActionListener listener : alList) {
					listener.actionPerformed(evt);
				}
			}
		});
		editor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		editor.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// editor.setEnabled(false);
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// editor.setEnabled(true);
			}
		});
	}

	@Override
	public void addActionListener(ActionListener arg0) {
		alList.add(arg0);
	}

	@Override
	public Component getEditorComponent() {
		return editor;
	}

	@Override
	public Object getItem() {
		return editor.getText();
	}

	@Override
	public void removeActionListener(ActionListener arg0) {
		alList.remove(arg0);
	}

	@Override
	public void selectAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setItem(Object arg0) {
		if (arg0 != null)
			editor.setText(arg0.toString());
		else
			editor.setText(null);
	}
}
