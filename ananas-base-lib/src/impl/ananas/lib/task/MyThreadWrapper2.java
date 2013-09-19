package impl.ananas.lib.task;

import ananas.lib.task.IThread;

public class MyThreadWrapper2 implements IThread {

	private final Thread _thread;
	private final long _id;

	public MyThreadWrapper2(Thread thread) {
		this._thread = thread;
		this._id = thread.getId();
	}

	@Override
	public Thread toJavaThread() {
		return this._thread;
	}

	@Override
	public void start() {
		this._thread.start();
	}

	@Override
	public void join() throws InterruptedException {
		this._thread.join();
	}

	@Override
	public long getId() {
		return this._id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (_id ^ (_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyThreadWrapper2 other = (MyThreadWrapper2) obj;
		if (_id != other._id)
			return false;
		return true;
	}

}
