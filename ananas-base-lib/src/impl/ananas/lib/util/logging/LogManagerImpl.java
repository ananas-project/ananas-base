package impl.ananas.lib.util.logging;

import ananas.lib.util.logging.LogManager;
import ananas.lib.util.logging.Logger;

public class LogManagerImpl implements LogManager {

	@Override
	public Logger getLogger(String name) {
		return new MyLogger(name);
	}

}
