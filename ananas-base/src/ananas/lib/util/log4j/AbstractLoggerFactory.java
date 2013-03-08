package ananas.lib.util.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractLoggerFactory {

	public Logger getLogger() {
		boolean normal = true;
		if (normal) {
			Logger logger = LogManager.getLogger(this.getClass().getName());
			logger.setLevel(Level.ALL);
			return logger;
		} else {
			Logger logger = MyLogManager.getLogger(this.getClass().getName());
			return logger;
		}
	}

}
