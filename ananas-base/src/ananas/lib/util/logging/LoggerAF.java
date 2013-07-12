package ananas.lib.util.logging;

/**
 * 
 * LoggerAF = LoggerAbstractFactory
 * 
 * */

public abstract class LoggerAF {

	private final static Logger logger = (new LoggerAF() {
	}).getLogger();

	public Logger getLogger() {
		Logger logger = LogManager.getLogger(this.getClass().getName());
		logger.setLevel(Level.ALL);
		return logger;
	}

	public static void main(String[] arg) {
		logger.info("hello, world");
	}

}
