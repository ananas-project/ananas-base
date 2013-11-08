package ananas.lib.juke;

public interface ModuleManager {

	Kernel getKernel();

	Module getModule(Class<?> api);

	void register(Class<?> api, ModuleFactory factory);

	void register(Class<?> api, Class<?> factoryClass);

	void register(String api, String factoryClass);

}
