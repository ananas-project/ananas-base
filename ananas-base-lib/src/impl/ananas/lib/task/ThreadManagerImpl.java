package impl.ananas.lib.task;

import java.util.Hashtable;
import java.util.Map;

import ananas.lib.task.IThread;
import ananas.lib.task.ThreadManager;

public class ThreadManagerImpl implements ThreadManager {

	private final IThreadRegistrar _reg = new MyThreadRegistrar();

	@Override
	public IThread newThread(Runnable runn, String name) {
		MyThreadWrapper1 thd = new MyThreadWrapper1(runn, _reg, name);
		return thd;
	}

	@Override
	public IThread newThread(Runnable runn) {
		String sn = runn.getClass().getSimpleName();
		return this.newThread(runn, sn);
	}

	@Override
	public IThread currentThread() {
		Thread thd1 = Thread.currentThread();
		long id = thd1.getId();
		IThread thd2 = this._reg.get(id);
		if (thd2 == null) {
			thd2 = new MyThreadWrapper2(thd1);
		}
		return thd2;
	}

	class MyThreadRegistrar implements IThreadRegistrar {

		private final Map<Long, IThread> _table = new Hashtable<Long, IThread>();

		@Override
		public void put(IThread thread) {
			this._table.put(thread.getId(), thread);
		}

		@Override
		public void remove(IThread thread) {
			this._table.remove(thread.getId());
		}

		@Override
		public IThread get(long id) {
			return this._table.get(id);
		}
	}

}
