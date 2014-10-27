package com.tc.lang.hv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

public class SearchPanel extends JPanel implements SelectedInfoChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 122806250826237859L;

	private JTextField searchField;
	private JComboBox searchBox;
	private JComboBox bochuOptBox;
	private JTextPane nghiaLabel;
	private JTextPane binhamLabel;
	private JTextPane hanvietLabel;
	private JTextPane phontheLabel;
	/**
	 * SearchBoxModel
	 */
	private SearchModel sModel;
	private SearchBochuModel sbModel;

	private DatabaseControl dbControl;

	public SearchPanel(SearchModel sModel, SearchBochuModel sbModel,
			DatabaseControl dbControl) {
		this.sModel = sModel;
		this.sbModel = sbModel;
		this.dbControl = dbControl;
		initComponents();
	}

	private void initComponents() {
		searchField = new JTextField();

		searchBox = new JComboBox();
		searchBox.setModel(sModel);

		searchField.addKeyListener(new KeyAdapter() {
			String lastSearch = null;

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == 40) {// Down arrow
					searchBox.requestFocus();
					searchBox.setSelectedIndex(0);
				}
				if (!searchField.getText().equals(lastSearch)) {
					dbControl.findByText(searchField.getText());
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							searchBox.setPopupVisible(true);
							searchBox.firePopupMenuWillBecomeVisible();
						}
					});
					lastSearch = searchField.getText();
				}
			}
		});
		searchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Up arrow
				if (e.getKeyCode() == 38 && searchBox.getSelectedIndex() == 0) {
					searchField.requestFocus();
				}
			}
		});
		searchBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					dbControl.updateSelectedInfo(((SearchItem) e.getItem())
							.getId());
				}
			}
		});

		bochuOptBox = new JComboBox(sbModel);
		bochuOptBox.setMaximumSize(new Dimension(36, 100));

		hanvietLabel = new JTextPane();
		// hanvietLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		// hanvietLabel.setFont(font);
		hanvietLabel.setContentType("text/html");
		hanvietLabel.setBackground(null);
		hanvietLabel.setBorder(null);
		hanvietLabel.setEditable(false);

		phontheLabel = new JTextPane();
		phontheLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
		phontheLabel.setContentType("text/html");
		phontheLabel.setBackground(null);
		phontheLabel.setBorder(null);
		phontheLabel.setEditable(false);

		phontheLabel.addMouseListener(new MouseAdapter() {

			private JPopupMenu popupMenu;
			{
				popupMenu = new JPopupMenu();
				JMenuItem showMenuItem = new JMenuItem("Xem stroke");
				popupMenu.add(showMenuItem);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// popupMenu.show(phontheLabel, e.getX(), e.getY());
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		phontheLabel.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == EventType.ENTERED) {
				}
				try {
					if (e != null && e.getURL() != null
							&& e.getEventType() == EventType.ACTIVATED) {
						Desktop.getDesktop().browse(e.getURL().toURI());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		nghiaLabel = new JTextPane();
		// nghiaLabel.setFont(font);
		nghiaLabel.setContentType("text/html");
		nghiaLabel.setBackground(null);
		nghiaLabel.setBorder(null);
		nghiaLabel.setEditable(false);

		binhamLabel = new JTextPane();
		// nghiaLabel.setFont(font);
		binhamLabel.setContentType("text/html");
		binhamLabel.setBackground(null);
		binhamLabel.setBorder(null);
		binhamLabel.setEditable(false);
		binhamLabel.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e != null && e.getURL() != null
						&& e.getEventType() == EventType.ACTIVATED) {
					String query = e.getURL().getQuery();
					if (query.startsWith("pinyin=")) {
						dbControl.updateSound(query.substring(7));
					}

				}

			}
		});

		int gridy = 0;
		GridBagLayout gbLayout = new GridBagLayout();
		JPanel cenPan = new JPanel(gbLayout);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = gridy;
		constraints.gridwidth = 2;
		constraints.weightx = 1.0;
		// constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		cenPan.add(phontheLabel, constraints);

		// reset special
		// constraints.fill = GridBagConstraints.NONE;
		// constraints. = GridBagConstraints.
		constraints.gridwidth = 1;
		constraints.weightx = 0.0;
		int ipadx = 11;

		constraints.anchor = GridBagConstraints.BASELINE_LEADING;
		constraints.gridx = 0;
		constraints.gridy = ++gridy;
		constraints.ipadx = ipadx;
		cenPan.add(new JLabel("Nghĩa:"), constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		// constraints.weighty = 1.0;
		constraints.ipadx = 0;
		cenPan.add(nghiaLabel, constraints);

		// row 2
		constraints.gridx = 0;
		constraints.gridy = ++gridy;
		constraints.ipadx = ipadx;
		cenPan.add(new JLabel("Hán Việt:"), constraints);
		constraints.gridx = 1;
		constraints.ipadx = 0;
		cenPan.add(hanvietLabel, constraints);

		// row 3
		constraints.gridx = 0;
		constraints.gridy = ++gridy;
		constraints.ipadx = ipadx;
		cenPan.add(new JLabel("Bính Âm:"), constraints);
		constraints.ipadx = 0;
		constraints.gridx = 1;
		cenPan.add(binhamLabel, constraints);

		// row 4
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = ++gridy;
		constraints.fill = GridBagConstraints.VERTICAL;
		cenPan.add(Box.createVerticalGlue(), constraints);

		// layout components
		setLayout(new BorderLayout());
		// setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel topBoxPan = new JPanel();
		topBoxPan.setLayout(new BoxLayout(topBoxPan, BoxLayout.LINE_AXIS));
		topBoxPan.add(searchBox);
		topBoxPan.add(Box.createHorizontalStrut(5));
		topBoxPan.add(bochuOptBox);
		searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField
				.getMinimumSize().height));

		JPanel topPan = new JPanel();
		topPan.setLayout(new BoxLayout(topPan, BoxLayout.PAGE_AXIS));
		topPan.add(searchField);
		topPan.add(topBoxPan);
		JScrollPane cenScrollPan = new JScrollPane(cenPan,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(topPan, BorderLayout.PAGE_START);
		add(cenScrollPan, BorderLayout.CENTER);

		// add(topPan);
		// add(cenPan);

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		revalidate();
	}

	@Override
	public void selectedInfoChanged(SelectedInfoChangeEvent evt) {
		final Info info = evt.getInfo();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				String linkable = "<a href='file:/"
						+ System.getProperty("user.dir")
						+ "/stroke.html' style='text-decoration: none;'>"
						+ info.getPhonthe() + "</a>";
				if (info.getStroke() == null) {
					linkable = info.getPhonthe();
				}
				phontheLabel
						.setText("<html style='background-color: blue;'><div style='font-size: 108px; text-align: center; margin: 0; padding: 0; background-color: none;'>"
								+ linkable + "</div></html>");
				String nghias[] = info.getNghia().replaceAll("^\\*?/", "")
						.replace("*/", "|").replace("/", "|").split("\\|");
				String nghiaHtml = "";
				for (int i = 0; i < nghias.length; i++) {
					nghiaHtml += (i + 1) + "" + nghias[i] + "<br/>";
				}
				nghiaLabel.setText("<html><ol style='margin: 0;'>" + nghiaHtml
						+ "</ol></html>");
				hanvietLabel
						.setText("<html><div style='margin: 0; padding: 0 0 -15px 0;'>"
								+ info.getHanviet() + "</div></html>");
				String binhamHtml = "";
				for (String binham : info.getBinhams()) {
					binhamHtml += "<li><a href='http://localhost/sound?pinyin="
							+ binham + "'>" + binham + "</a></li>";
				}
				binhamLabel
						.setText("<html><ol style='margin: 0; display: inline; float: right;'>"
								+ binhamHtml + "</ol></html>");
				if (info.getStroke() != null) {
					try {
						OutputStream os = new FileOutputStream(
								"stroke_files/stroke.swf");
						os.write(info.getStroke());
						os.flush();
						os.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				revalidate();
			}
		});
	}
}
