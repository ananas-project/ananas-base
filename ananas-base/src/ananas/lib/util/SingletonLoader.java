package ananas.lib.util;

import java.io.InputStream;
import java.util.Properties;

public class SingletonLoader {

	public static Object load(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object load(Class<?> apiClass) {

		// method 1
		final String apiName = apiClass.getName();
		String implName = System.getProperty(apiName);
		if (implName != null) {
			Object inst = load(implName.trim());
			if (inst != null) {
				return inst;
			}
		}

		// method 2
		try {
			String sn = apiClass.getSimpleName();
			InputStream in = apiClass.getResourceAsStream(sn + ".properties");
			if (in != null) {
				Properties prop = new Properties();
				prop.load(in);
				in.close();
				implName = prop.getProperty(sn);
				if (implName != null) {
					Object inst = load(implName);
					if (inst != null) {
						return inst;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("cannot find implementation of " + apiClass);
		return null;
	}

}
