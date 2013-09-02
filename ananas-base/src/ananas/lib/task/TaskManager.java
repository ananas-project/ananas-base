package ananas.lib.task;

import java.util.List;

import ananas.lib.util.SingletonLoader;

public interface TaskManager {

	class Agent {

		private static TaskManager _inst;

		public static TaskManager getInstance() {
			TaskManager inst = _inst;
			if (inst == null) {
				inst = (TaskManager) SingletonLoader.load(TaskManager.class);
				_inst = inst;
			}
			return inst;
		}
	}

	void run(Task task);

	List<TaskStack> listAllTaskStack();

	TaskStack currentTaskStack();

}
