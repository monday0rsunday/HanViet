package com.tc.lang.dic;

public class Util {

	private static boolean isInitial(char c) {
		return c != 'j' && c != 'q' && c != 'x' && c != 'b' && c != 'p'
				&& c != 'm' && c != 'd' && c != 't' && c != 'n' && c != 'l'
				&& c != 'y' && c != 'z' && c != 'f' && c != 'g' && c != 'c'
				&& c != 'h' && c != 's' && c != 'w' && c != 'r' && c != 'k';
	}

	public static String encode(String pinyin) {
		StringBuilder sb = new StringBuilder();
		sb.append(pinyin.charAt(0));
		int i = 1;
		if (pinyin.length() > 1) {
			if (("zsc".contains(pinyin.charAt(0) + ""))
					&& pinyin.charAt(1) == 'h') {
				i++;
				sb.append(pinyin.charAt(1));
			}
		}
		for (; i < pinyin.length() - 1; i++) {
			char c = pinyin.charAt(i);
			char n = pinyin.charAt(i + 1);
			if (!isInitial(c)) {
				if (c == 'n') {
					if (n == 'g') {
						i++;
						sb.append("ng");
						sb.append(' ');
					} else if (isInitial(n)) {
						sb.append(' ');
						sb.append(c);
					} else {
						sb.append(c);
					}
				} else if ((c == 'z' || c == 'c' || c == 's') && n == 'h') {
					i++;
					sb.append(' ');
					sb.append(c);
					sb.append(n);
				} else {
					sb.append(' ');
					sb.append(c);
				}
			} else {
				sb.append(c);
			}
		}
		if (i == pinyin.length() - 1)
			sb.append(pinyin.charAt(i));
		return sb.toString();
	}

	public static String encodeAscii(String pinyin) {
		String tmp = pinyin.replace("ā", "a1").replace("á", "a2")
				.replace("ǎ", "a3").replace("à", "a4").replace("ē", "e1")
				.replace("é", "e2").replace("ě", "e3").replace("è", "e4")
				.replace("ī", "i1").replace("í", "i2").replace("ǐ", "i3")
				.replace("ì", "i4").replace("ō", "o1").replace("ó", "o2")
				.replace("ǒ", "o3").replace("ò", "o4").replace("ū", "u1")
				.replace("ú", "u2").replace("ǔ", "u3").replace("ù", "u4");
		StringBuilder rs = new StringBuilder();
		char x = 0;
		for (char c : tmp.toCharArray()) {
			if (Character.isDigit(c)) {
				x = c;
			} else {
				if (c == ' ') {
					if (x > 0) {
						rs.append(x);
						rs.append(c);
						x = 0;
					} else {
						rs.append(c);
					}
				} else {
					rs.append(c);
				}
			}
		}
		return rs.toString();
	}

	public static String decodeAscii(String pinyin) {
		return pinyin.replace("a1", "ā").replace("a2", "á").replace("a3", "ǎ")
				.replace("a4", "à").replace("e1", "ē").replace("e2", "é")
				.replace("e3", "ě").replace("e4", "è").replace("i1", "ī")
				.replace("i2", "í").replace("i3", "ǐ").replace("i4", "ì")
				.replace("o1", "ō").replace("o2", "ó").replace("o3", "ǒ")
				.replace("o4", "ò").replace("u1", "ū").replace("u2", "ú")
				.replace("u3", "ǔ").replace("u4", "ù");
	}

}
