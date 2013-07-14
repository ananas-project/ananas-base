package ananas.lib.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoader {

	public Properties loadProperties(Object ref, String fileName) {
		Properties prop = new Properties();
		try {
			InputStream in = ref.getClass().getResourceAsStream(fileName);
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public void loadPropertiesToSystem(Object ref, String fileName) {
		Properties prop = this.loadProperties(ref, fileName);
		Set<Object> keys = prop.keySet();
		for (Object k : keys) {
			String key = k.toString();
			String value = prop.getProperty(key);
			System.setProperty(key, value);
		}
		// for show
		String la = prop.getProperty("system.properties.list_all");
		if ("true".equalsIgnoreCase(la)) {
			keys = System.getProperties().keySet();
			List<String> keylist = new ArrayList<String>();
			for (Object k : keys) {
				String key = k.toString();
				keylist.add(key);
			}
			Collections.sort(keylist);
			for (String key : keylist) {
				String value = System.getProperty(key);
				System.out.println(key + " = " + value);
			}
		}
	}

}
