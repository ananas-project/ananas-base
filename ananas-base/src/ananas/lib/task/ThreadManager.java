package ananas.lib.task;

import ananas.lib.util.SingletonLoader;

public interface ThreadManager {

	IThread newThread(Runnable runn, String name);

	IThread newThread(Runnable runn);

	IThread currentThread();

	ThreadRegistrar getThreadRegistrar();

	class Agent {

		private static ThreadManager _inst;

		public static ThreadManager getInstance() {
			ThreadManager inst = _inst;
			if (inst == null) {
				inst = (ThreadManager) SingletonLoader
						.load(ThreadManager.class);
				_inst = inst;
			}
			return inst;
		}
	}

}
