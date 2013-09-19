package impl.ananas.lib.task;

import java.util.List;
import java.util.Vector;

import ananas.lib.task.Task;
import ananas.lib.task.TaskStack;

public class TaskStackImpl implements TaskStack {

	private final List<Task> _list = new Vector<Task>();

	@Override
	public int count() {
		return this._list.size();
	}

	@Override
	public Task get(int index) {
		return this._list.get(index);
	}

	@Override
	public Task peek() {
		return this._list.get(this._list.size() - 1);
	}

	@Override
	public Task pop() {
		return this._list.remove(this._list.size() - 1);
	}

	@Override
	public void push(Task task) {
		this._list.add(task);
	}

}
