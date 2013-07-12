package ananas.lib.util.logging;

public interface Logger {

	void setLevel(Level level);

	void trace(String string);

	void warn(String string);

	void info(String string);

	void error(String string);

	void error(Throwable e);

	void error(String message, Throwable e);

	class Manager {

		public static Logger getLogger() {
			return getLogger(null);
		}

		public static Logger getLogger(String name) {
			return LogManager.Factory.getDefault().getLogger(name);
		}

	}

}
