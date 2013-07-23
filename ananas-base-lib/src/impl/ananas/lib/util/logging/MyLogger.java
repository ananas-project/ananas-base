package impl.ananas.lib.util.logging;

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

	@Override
	public void log(Level level, String string) {
		string = level.getText() + " : " + this._name + " : " + string;
		if (level.getNumber() >= _err_key) {
			System.err.println(string);
		} else {
			System.out.println(string);
		}
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
