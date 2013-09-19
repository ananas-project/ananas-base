package impl.ananas.lib.task;

import ananas.lib.task.IThread;

public interface IThreadRegistrar {

	void put(IThread thread);

	void remove(IThread thread);

	IThread get(long id);

}
