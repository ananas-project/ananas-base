package impl.ananas.lib.util.logging;

import ananas.lib.util.logging.Level;
import ananas.lib.util.logging.Logger;

public class MyLogger implements Logger {

	public MyLogger(String name) {
	}

	@Override
	public void setLevel(Level level) {
	}

	@Override
	public void trace(String string) {
		System.out.println(string);
	}

	@Override
	public void warn(String string) {
		System.err.println(string);
	}

	@Override
	public void info(String string) {
		System.out.println(string);
	}

	@Override
	public void error(String string) {
		System.err.println(string);
	}

	@Override
	public void error(Throwable e) {
		System.err.println(e);
		e.printStackTrace(System.err);
	}

	@Override
	public void error(String message, Throwable e) {
		System.err.println(e + ":" + message);
		e.printStackTrace(System.err);
	}

}
