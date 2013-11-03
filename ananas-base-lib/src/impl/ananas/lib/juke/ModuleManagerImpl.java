package impl.ananas.lib.juke;

import java.util.Hashtable;
import java.util.Map;

import ananas.lib.juke.Component;
import ananas.lib.juke.ComponentFactory;
import ananas.lib.juke.ComponentManager;
import ananas.lib.juke.Kernel;

public class ModuleManagerImpl implements ComponentManager {

	final Map<String, String> _map_class;
	final Map<String, String> _map_inst;

	public ModuleManagerImpl() {
		_map_inst = new Hashtable<String, String>();
		_map_class = new Hashtable<String, String>();
	}

	@Override
	public Component loadComponent(Kernel kernel, Class<?> api, String name) {
		String key = this.__key_for(api, name);
		String rlt = this._map_inst.get(key);
		if (rlt == null) {
			throw new RuntimeException("the instance of module not reg : "
					+ key);
		}
		return this.getComponentFactory(kernel, api).create(kernel);
	}

	@Override
	public ComponentFactory getComponentFactory(Kernel kernel, Class<?> api) {
		try {
			String apiName = api.getName();
			String cn = this._map_class.get(apiName);
			if (cn == null) {
				cn = kernel.getProperties().getProperty(apiName);
			}
			if (cn == null) {
				throw new RuntimeException("no impl for api " + api);
			}
			Class<?> cls = Class.forName(cn);
			return (ComponentFactory) cls.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void registerInstance(Kernel kernel, Class<?> api, String name) {
		String key = __key_for(api, name);
		this._map_inst.put(key, "true");
	}

	private String __key_for(Class<?> api, String name) {
		return (api + "#" + name);
	}

	@Override
	public void registerClass(Kernel kernel, Class<?> api, String className) {
		this._map_class.put(api.getName(), className);
	}

}
