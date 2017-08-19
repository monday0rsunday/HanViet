package com.tc.lang.application;

import com.tc.lang.presentation.sound.SoundPlayModel;
import com.tc.lang.presentation.ui.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseControl implements SearchBochuChangeListener
{

	Connection connection;

	private SearchModel searchModel;
	private SelectedInfoModel selectedInfoModel;
	private SoundPlayModel soundPlayModel;

	public DatabaseControl(String dbLoc, SearchModel searchModel,
						   SelectedInfoModel selectedInfoModel,
						   SoundPlayModel soundPlayModel) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbLoc);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.searchModel = searchModel;
		this.selectedInfoModel = selectedInfoModel;
		this.soundPlayModel = soundPlayModel;
	}

	public void findByText(String sText) {
		List<SearchItem> result = new ArrayList<SearchItem>();
		Statement s = null;
		try {
			s = connection.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT id, trung, pinyin FROM stroke WHERE pinyin LIKE \"%"
							+ sText
							+ "%\" OR trung LIKE '"
							+ sText
							+ "' OR han LIKE '%" + sText + "%' ORDER BY pinyin");
			while (rs.next()) {
				SearchItem kv = new SearchItem();
				kv.setKey(rs.getString("trung") + " " + rs.getString("pinyin"));
				kv.setId(rs.getLong("id"));
				result.add(kv);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		searchModel.setItemṣ̣(result);
	}

	public List<SearchItem> loadBochu() {
		List<SearchItem> result = new ArrayList<SearchItem>();
		Statement s = null;
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM radical");
			while (rs.next()) {
				SearchItem kv = new SearchItem();
				kv.setKey(rs.getLong("id") + ". " + rs.getString("bothu") + " "
						+ rs.getString("hanviet") + "(" + rs.getInt("sonet")
						+ ")");
				kv.setId(rs.getLong("id"));
				result.add(kv);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void updateSelectedInfo(long id) {
		Info info = null;
		Statement s;
		try {
			s = connection.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT * FROM stroke WHERE id=" + id);
			while (rs.next()) {
				info = new Info();
				info.setHanviet(rs.getString("han"));
				info.setNghia(rs.getString("nghia"));
				if (rs.getString("pinyin") != null) {
					info.setBinhams(rs.getString("pinyin").replace(" ", "")
							.split(","));
				}
				info.setPhonthe(rs.getString("trung"));
				info.setStroke(rs.getBytes("stroke"));
				break;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectedInfoModel.setSelectedInfo(info);
	}

	public void updateSound(String binham) {
		byte[] sound = null;
		Statement s;
		try {
			s = connection.createStatement();
			ResultSet rs = s
					.executeQuery("SELECT sound FROM sound WHERE pinyin='"
							+ binham + "'");
			while (rs.next()) {
				sound = rs.getBytes("sound");
				break;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		soundPlayModel.setSound(sound);
	}

	@Override
	public void searchBochuChanged(SearchBochuChangeEvent evt) {
		long id = evt.getId();
		if (id < 0) {
			findByText("");
		} else {
			List<SearchItem> result = new ArrayList<SearchItem>();
			Statement s = null;
			try {
				s = connection.createStatement();
				ResultSet rs = s
						.executeQuery("SELECT id, trung, pinyin FROM stroke WHERE bothu="
								+ id);
				while (rs.next()) {
					SearchItem kv = new SearchItem();
					kv.setKey(rs.getString("trung") + " "
							+ rs.getString("pinyin"));
					kv.setId(rs.getLong("id"));
					result.add(kv);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (s != null) {
					try {
						s.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			searchModel.setItemṣ̣(result);
		}
	}

}
