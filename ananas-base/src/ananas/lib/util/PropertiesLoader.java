package ananas.lib.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import ananas.lib.util.logging.Logger;

public interface PropertiesLoader {

	Properties load();

	class Util {

		final static Logger log = Logger.Agent.getLogger();

		public static PropertiesLoader loaderFor(Object ref, String fileName) {

			class MyLoader implements PropertiesLoader {

				private final String _file;
				private final Class<? extends Object> _class;

				public MyLoader(Class<? extends Object> class1, String fileName) {
					this._class = class1;
					this._file = fileName;
				}

				@Override
				public Properties load() {
					Properties prop = new Properties();
					try {
						InputStream in = _class.getResourceAsStream(_file);
						prop.load(in);
						in.close();
					} catch (Exception e) {
						log.warn("cannot load properties : " + _class + "#"
								+ _file);
						log.error(e);
					}
					return prop;
				}
			}
			return new MyLoader(ref.getClass(), fileName);
		}

		public static void loadPropertiesToSystem(Object ref, String fileName) {
			Properties prop = loaderFor(ref, fileName).load();
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
}