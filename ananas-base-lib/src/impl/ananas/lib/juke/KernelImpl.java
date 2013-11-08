package impl.ananas.lib.juke;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import ananas.lib.juke.Kernel;
import ananas.lib.juke.Module;
import ananas.lib.juke.ModuleFactory;
import ananas.lib.juke.ModuleManager;

class KernelImpl implements Kernel {

	private final Properties _prop;
	private final ModuleManager _mm;

	public KernelImpl() {
		this._mm = new MyModuleManager();
		this._prop = new Properties();
	}

	@Override
	public Module getModule(Class<?> api) {
		return this._mm.getModule(api);
	}

	@Override
	public ModuleManager getModuleManager() {
		return this._mm;
	}

	@Override
	public Properties getProperties() {
		return this._prop;
	}

	private class MyModuleManager implements ModuleManager {

		private final Map<String, Module> _module_inst;
		private final Map<String, ModuleFactory> _module_fact;

		public MyModuleManager() {
			this._module_inst = new Hashtable<String, Module>();
			this._module_fact = new Hashtable<String, ModuleFactory>();
		}

		@Override
		public Kernel getKernel() {
			return KernelImpl.this;
		}

		@Override
		public Module getModule(Class<?> api) {
			String name = api.getName();
			Module mod = this._module_inst.get(name);
			if (mod != null)
				return mod;
			ModuleFactory factory = this._module_fact.get(name);
			mod = factory.create(getKernel());
			this._module_inst.put(name, mod);
			return mod;
		}

		private void __reg(String api, ModuleFactory mf) {
			this._module_fact.put(api, mf);
		}

		@Override
		public void register(Class<?> api, ModuleFactory mf) {
			this.__reg(api.getName(), mf);
		}

		@Override
		public void register(Class<?> api, Class<?> factoryClass) {
			try {
				ModuleFactory mf = (ModuleFactory) factoryClass.newInstance();
				this.__reg(api.getName(), mf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void register(String api, String factoryClass) {
			try {
				ModuleFactory mf = (ModuleFactory) Class.forName(factoryClass)
						.newInstance();
				this.__reg(api, mf);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
