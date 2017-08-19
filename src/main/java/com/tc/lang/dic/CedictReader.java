package com.tc.lang.dic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CedictReader {

	private File file;
	private BufferedReader br;

	public CedictReader(String filePath) {
		file = new File(filePath);
	}

	public void open() throws Exception {
		br = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
	}

	public void close() throws Exception {
		br.close();
	}

	static Pattern ptn = Pattern
			.compile("([^\\s]+)\\s([^\\s]+)\\s\\[(.*)\\]\\s+(.*)");

	public Entry read() {
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				Entry entry = new Entry();
				Matcher mtc = ptn.matcher(line);
				if (mtc.find()) {
					entry.setPhonthe(mtc.group(1));
					entry.setGianthe(mtc.group(2));
					entry.setBinham(mtc.group(3).toLowerCase());
					// entry.setHanviet(mtc.group(4));
					entry.setNghia(mtc.group(4));
					entry.setSource("cedict");
				}
				return entry;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CedictReader ois = new CedictReader("./dic/cedict_ts.u8");
		Entry lr = null;
		try {
			ois.open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while ((lr = ois.read()) != null) {
			System.out.println(lr);
		}
		try {
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
