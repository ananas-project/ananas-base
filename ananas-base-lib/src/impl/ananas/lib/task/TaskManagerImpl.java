package impl.ananas.lib.task;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import ananas.lib.task.IThread;
import ananas.lib.task.Task;
import ananas.lib.task.TaskManager;
import ananas.lib.task.TaskStack;
import ananas.lib.task.ThreadManager;

public class TaskManagerImpl implements TaskManager {

	private final Map<IThread, TaskStack> _table = new Hashtable<IThread, TaskStack>();

	@Override
	public void run(Task task) {
		final IThread thd = ThreadManager.Agent.getInstance().currentThread();
		final TaskStack ts = this.__taskStackForThread(thd);
		ts.push(task);
		try {
			task.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ts.pop();
		if (ts.count() <= 0) {
			this._table.remove(thd);
		}
	}

	@Override
	public List<TaskStack> listAllTaskStack() {
		return new ArrayList<TaskStack>(this._table.values());
	}

	@Override
	public TaskStack currentTaskStack() {
		IThread thd = ThreadManager.Agent.getInstance().currentThread();
		TaskStack ts = this._table.get(thd);
		return ts;
	}

	private TaskStack __taskStackForThread(IThread thd) {
		TaskStack ts = this._table.get(thd);
		if (ts == null) {
			ts = new TaskStackImpl();
			this._table.put(thd, ts);
		}
		return ts;
	}

}
