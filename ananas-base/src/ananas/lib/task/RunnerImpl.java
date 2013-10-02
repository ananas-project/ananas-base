package ananas.lib.task;

import java.util.List;
import java.util.Vector;

final class RunnerImpl implements Runner {

	private final Object _notify = new Object();
	private final List<Task> _list = new Vector<Task>();
	private Task _current = null;
	private int _cnt_working_thread = 0;

	@Override
	public synchronized void run() {
		this._cnt_working_thread++;
		final int limit = 5000;
		for (;;) {
			try {
				Task task = null;
				if (_list.size() > 0)
					task = _list.remove(0);
				if (task == null) {
					try {
						synchronized (this._notify) {
							this._notify.wait(limit);
						}
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				} else {
					this._current = task;
					try {
						task.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
					this._current = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this._cnt_working_thread--;
	}

	@Override
	public void add(Runnable runn) {
		if (runn == null)
			return;
		Task task = new MyRunnableWrapper(runn);
		this.add(task);
	}

	private static class MyRunnableWrapper implements Task {

		private final Runnable _runn;

		public MyRunnableWrapper(Runnable runn) {
			this._runn = runn;
		}

		@Override
		public void run() {
			this._runn.run();
		}

		@Override
		public void cancel() {
		}
	}

	@Override
	public void add(Task task) {
		if (task != null) {
			_list.add(task);
			synchronized (this._notify) {
				this._notify.notify();
			}
		}
	}

	@Override
	public boolean remove(Task task) {
		if (task == null)
			return false;
		return _list.remove(task);
	}

	@Override
	public Task current() {
		return this._current;
	}

	@Override
	public List<Task> listAll() {
		return _list;
	}

	@Override
	public void start() {
		if (this._cnt_working_thread < 1) {
			(new Thread(this)).start();
		}
	}

}
