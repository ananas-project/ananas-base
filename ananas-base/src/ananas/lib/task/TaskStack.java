package ananas.lib.task;

public interface TaskStack {

	int count();

	Task get(int index);

	Task peek();

	Task pop();

	void push(Task task);

}
