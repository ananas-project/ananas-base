package ananas.tools.autocopy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AutoUtil {

	public static List<String> stringToWords(String str, char sp) {

		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		char[] chs = str.toCharArray();
		for (char ch : chs) {
			if (ch == sp) {
				if (sb.length() > 0) {
					list.add(sb.toString());
					sb.setLength(0);
				}
			} else if (ch == ' ' || ch == 0x09 || ch == 0x0a || ch == 0x0d) {
				// skip
			} else {
				sb.append(ch);
			}
		}
		if (sb.length() > 0) {
			list.add(sb.toString());
		}
		return list;
	}

	public static String getProperty(Properties properties, String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("need property named : " + key);
		}
		return value;
	}

}
