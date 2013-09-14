package impl.ananas.lib.task;

import ananas.lib.task.IThread;

public class MyThreadWrapper1 implements IThread, Runnable {

	private final Thread _thread;
	private final IThreadRegistrar _reg;
	private Runnable _runn;

	public MyThreadWrapper1(Runnable runn, IThreadRegistrar reg, String name) {
		this._runn = runn;
		this._reg = reg;
		this._thread = new Thread(this, name);
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
		return this._thread.getId();
	}

	@Override
	public void run() {
		final Runnable runn = this._runn;
		this._runn = null;
		this._reg.put(this);
		try {
			if (runn != null)
				runn.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this._reg.remove(this);
	}

}
