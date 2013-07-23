package ananas.lib.util.logging;

public interface Logger {

	// base

	void setLevel(Level level);

	void log(String string);

	void log(Level level, String string);

	// levels

	void trace(String string);

	void debug(String string);

	void fatal(String string);

	void warn(String string);

	void info(String string);

	void error(String string);

	void error(Throwable e);

	void error(String message, Throwable e);

	// agent

	class Agent {

		public static Logger getLogger() {
			return getLogger(null);
		}

		public static Logger getLogger(String name) {
			return LogManager.Factory.getDefault().getLogger(name);
		}

	}

}
