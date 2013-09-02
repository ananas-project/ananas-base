package ananas.lib.task;

public interface IThread {

	Thread toJavaThread();

	void start();

	void join() throws InterruptedException;

	long getId();

}
