package ananas.lib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class CommandLinePropertiesUtil {

	public static String[] propertiesToArguments(Properties prop) {
		return CommandLinePropertiesUtil._p2a(prop);
	}

	public static Properties argumentsToProperties(String[] args) {
		return CommandLinePropertiesUtil._a2p(args, null);
	}

	public static Properties argumentsToProperties(String[] args,
			Properties prop) {
		return CommandLinePropertiesUtil._a2p(args, prop);
	}

	private final static String list_key = "[LIST]";

	private static String[] _p2a(Properties prop) {
		final List<String> list = new ArrayList<String>();
		for (Object k : prop.keySet()) {
			final String key = k.toString().trim();
			final String val = prop.getProperty(key).trim();
			if (!key.equals(list_key)) {
				// the map
				if (val.length() > 0) {
					list.add(key + "=" + val);
				} else {
					list.add(key);
				}
			} else {
				// the list
				list.add(val);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	private static Properties _a2p(String[] args, Properties prop) {
		if (prop == null) {
			prop = new Properties();
		}
		StringBuilder sb = new StringBuilder();
		for (String str : args) {
			str = str.trim();
			if (str.startsWith("-")) {
				// the map
				final int i = str.indexOf('=');
				final String key, value;
				if (i > 0) {
					key = str.substring(0, i);
					value = str.substring(i + 1);
				} else {
					key = str;
					value = "";
				}
				prop.setProperty(key.trim(), value.trim());
			} else {
				// the list
				sb.append(" " + str);
			}
		}
		if (sb.length() > 0) {
			prop.setProperty(list_key, sb.toString().trim());
		}
		return prop;
	}

	public static void main(String args[]) {

		System.out.println("==========================================");
		for (String s : args) {
			System.out.println("arg[x] = " + s);
		}

		System.out.println("==========================================");

		Properties prop = CommandLinePropertiesUtil.argumentsToProperties(args);
		for (Object k : prop.keySet()) {
			String key = k.toString();
			String val = prop.getProperty(key);
			System.out.println("'" + key + "' = '" + val + "'");
		}

		System.out.println("==========================================");
		String[] args2 = CommandLinePropertiesUtil.propertiesToArguments(prop);
		for (String s : args2) {
			System.out.println("arg2[x] = " + s);
		}
	}

}
