package ananas.lib.util.logging;

import ananas.lib.util.SingletonLoader;

public interface LogManager {

	Logger getLogger(String name);

	class Factory {

		private static LogManager _inst;

		public static LogManager getDefault() {
			LogManager inst = _inst;
			if (inst == null) {
				_inst = inst = (LogManager) SingletonLoader
						.load(LogManager.class);
			}
			return inst;
		}

	}

}
