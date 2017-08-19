package com.tc.lang.dic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LacvietReader {

	private File file;
	private BufferedReader br;

	public LacvietReader(String filePath) {
		file = new File(filePath);
	}

	public void open() throws Exception {
		br = new BufferedReader(
				new InputStreamReader(new FileInputStream(file)));
	}

	public void close() throws Exception {
		br.close();
	}

	static Pattern ptn = Pattern.compile("\\[(.*)\\]\\s+(.*)");

	public Entry read() {
		try {
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				Entry entry = new Entry();
				String[] ss = line.split("=");
				entry.setPhonthe(ss[0]);
				String[] means = ss[1].split("✚");
				for (String mean : means) {
					Matcher mtc = ptn.matcher(mean);
					if (mtc.find()) {
						entry.setBinham(Util.encodeAscii(Util.encode(mtc
								.group(1).replace("'", "").replace("·", "")
								.toLowerCase())));
						// entry.setHanviet(mtc.group(4));
						entry.setNghia(mtc.group(2));
						entry.setSource("lacviet");
					}
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
		LacvietReader ois = new LacvietReader("./dic/lacviet.txt");
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
