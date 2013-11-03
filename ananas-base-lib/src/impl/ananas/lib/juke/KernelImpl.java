package impl.ananas.lib.juke;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import ananas.lib.juke.Kernel;
import ananas.lib.juke.Component;
import ananas.lib.juke.ComponentManager;

class KernelImpl implements Kernel {

	private final Properties _prop = new Properties();
	private final Map<String, Component> _module_inst;
	private final ComponentManager _mm = new ModuleManagerImpl();

	public KernelImpl() {
		this._module_inst = new Hashtable<String, Component>();
	}

	@Override
	public Component getComponent(Class<?> api, String name) {
		final String key = (api + "#" + name);
		Component mo = this._module_inst.get(key);
		if (mo == null) {
			mo = this.getComponentManager().loadComponent(this, api, name);
			if (mo != null) {
				this._module_inst.put(key, mo);
			}
		}
		return mo;
	}

	@Override
	public Component getComponent(Class<?> api) {
		return this.getComponent(api, null);
	}

	@Override
	public ComponentManager getComponentManager() {
		return this._mm;
	}

	@Override
	public Properties getProperties() {
		return this._prop;
	}

}
