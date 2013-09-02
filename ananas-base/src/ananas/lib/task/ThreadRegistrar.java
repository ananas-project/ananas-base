package ananas.lib.task;

public interface ThreadRegistrar {

	void put(IThread thread);

	void remove(IThread thread);

	IThread get(long id);

}
