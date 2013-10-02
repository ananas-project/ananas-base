package ananas.lib.task;

import java.util.List;

public interface Runner extends Runnable {

	Task current();

	void add(Task task);

	void add(Runnable runn);

	boolean remove(Task task);

	List<Task> listAll();

	void start();

	class Factory {

		public static Runner newInstance() {
			return new RunnerImpl();
		}
	}

}
