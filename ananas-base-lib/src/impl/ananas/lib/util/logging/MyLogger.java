package impl.ananas.lib.util.logging;

import java.util.HashMap;
import java.util.Map;

import ananas.lib.util.logging.Level;
import ananas.lib.util.logging.Logger;

public class MyLogger implements Logger {

	private Level _level = Level.INFO;
	private final String _name;

	public MyLogger(String name) {
		_name = name;
	}

	@Override
	public void setLevel(Level level) {
		if (level != null) {
			_level = level;
		}
	}

	@Override
	public void trace(String string) {
		log(Level.TRACE, string);
	}

	@Override
	public void warn(String string) {
		log(Level.WARN, string);
	}

	@Override
	public void info(String string) {
		log(Level.INFO, string);
	}

	@Override
	public void error(String string) {
		error(string, new RuntimeException(string));
	}

	@Override
	public void error(Throwable e) {
		error(e.getMessage(), e);
	}

	@Override
	public void error(String message, Throwable e) {
		log(Level.ERROR, message);
		e.printStackTrace(System.err);
	}

	final int _err_key = Level.WARN.getNumber();
	private Level _level_limit;

	@Override
	public void log(Level level, String string) {
		final Level levelLimit = this.__getLimit();
		if (level.getNumber() < levelLimit.getNumber()) {
			return;
		}
		final String sp = " ";
		string = this.__timeToString() + sp + this.__levelToString(level) + sp
				+ this.__threadToString() + sp + this._name + sp + " " + string;
		if (level.getNumber() >= _err_key) {
			System.err.println(string);
		} else {
			System.out.println(string);
		}
	}

	private String __threadToString() {
		return Thread.currentThread().toString();
	}

	private String __timeToString() {
		return this.__timeToString(System.currentTimeMillis());
	}

	private String __timeToString(long time) {
		long h, m, s, ms;
		ms = time % 1000;
		s = (time / 1000) % 60;
		m = (time / (1000 * 60)) % 60;
		h = (time / (1000 * 3600)) % 24;
		return (__intToStr(h, 2) + ":" + __intToStr(m, 2) + ":"
				+ __intToStr(s, 2) + "." + __intToStr(ms, 3));
	}

	private String __intToStr(long n, int len) {
		String s = "0000" + n;
		int slen = s.length();
		if (slen > len)
			return s.substring(slen - len);
		else
			return s;
	}

	private String __levelToString(Level level) {
		String s = level.getText() + "    ";
		return s.substring(0, 5);
	}

	private Level __getLimit() {
		Level lv = this._level_limit;
		if (lv == null) {
			String key = Level.class.getName() + ".limit";
			String value = System.getProperty(key) + "";
			Level[] array = { Level.OFF, Level.ALL, Level.TRACE, Level.DEBUG,
					Level.INFO, Level.WARN, Level.ERROR, Level.FATAL };
			Map<String, Level> map = new HashMap<String, Level>();
			for (Level lv2 : array) {
				map.put(lv2.getText(), lv2);
			}
			lv = map.get(value);
			if (lv == null) {
				System.err.println("bad value of property:" + key);
				System.err.println("set level.limit to ALL");
				lv = Level.ALL;
			}
		}
		return lv;
	}

	@Override
	public void debug(String string) {
		log(Level.DEBUG, string);
	}

	@Override
	public void log(String string) {
		log(_level, string);
	}

	@Override
	public void fatal(String string) {
		log(Level.FATAL, string);
	}

}
