package ananas.lib.util;

import java.io.InputStream;
import java.util.Properties;

import ananas.lib.util.logging.Logger;

public class ClassPropertiesLoader implements PropertiesLoader {

	final static Logger log = Logger.Agent.getLogger();

	private final Class<?> _class;

	public ClassPropertiesLoader(Class<?> aClass) {
		this._class = aClass;
	}

	public ClassPropertiesLoader(Object object) {
		this._class = object.getClass();
	}

	@Override
	public Properties load() {
		String suffix = ".properties";
		String name = this._class.getSimpleName() + suffix;
		String longName = this._class.getName() + suffix;
		try {
			Properties prop = new Properties();
			InputStream in = this._class.getResourceAsStream(name);
			prop.load(in);
			in.close();
			return prop;
		} catch (Exception e) {
			log.warn("cannot load propeties file : " + longName);
			log.error(e);
			return null;
		}
	}
}
