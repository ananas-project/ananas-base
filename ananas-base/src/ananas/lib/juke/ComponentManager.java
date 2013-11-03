package ananas.lib.juke;

public interface ComponentManager {

	Component loadComponent(Kernel kernel, Class<?> api, String name);

	ComponentFactory getComponentFactory(Kernel kernel, Class<?> api);

	void registerInstance(Kernel kernel, Class<?> api, String name);

	void registerClass(Kernel kernel, Class<?> api, String className);

}
