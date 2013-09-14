package ananas.lib.task;

public class CThread implements IThread {

	private final IThread _impl;

	public CThread(Runnable runnable) {
		this._impl = ThreadManager.Agent.getInstance().newThread(runnable);
	}

	public CThread(Runnable runnable, String name) {
		this._impl = ThreadManager.Agent.getInstance()
				.newThread(runnable, name);
	}

	@Override
	public Thread toJavaThread() {
		return _impl.toJavaThread();
	}

	@Override
	public void start() {
		_impl.start();
	}

	@Override
	public void join() throws InterruptedException {
		_impl.join();
	}

	@Override
	public long getId() {
		return _impl.getId();
	}

}
