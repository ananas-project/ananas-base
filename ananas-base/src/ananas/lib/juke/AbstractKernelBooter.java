package ananas.lib.juke;

import java.util.Properties;

import ananas.lib.util.PropertiesLoader;

public abstract class AbstractKernelBooter implements KernelBooter {

	private Properties _prop;

	@Override
	public Kernel boot(Kernel kernel) {
		if (kernel == null) {
			kernel = KernelFactory.Agent.getFactory().create();
		}

		this.__load_prop();

		// invoke sub booter
		String s = __get_prop("sub_booter_list");
		String[] list = s.split(";");
		for (String item : list) {
			item = item.trim();
			if (item.length() > 0) {
				String subbooter = __get_prop(item);
				__run_booter(kernel, subbooter);
			}
		}

		// make setter

		// copy to prop
		kernel.getProperties().putAll(this._prop);

		System.out.println(kernel + ".boot(" + this + ") ok");
		return kernel;
	}

	private void __run_booter(Kernel kernel, String cn) {
		try {
			Class<?> cls = Class.forName(cn);
			KernelBooter booter = (KernelBooter) cls.newInstance();
			booter.boot(kernel);
		} catch (Exception e) {
			System.err.println("this = " + this);
			throw new RuntimeException(e);
		}
	}

	private String __get_prop(String key) {
		String v = this._prop.getProperty(key);
		if (v == null) {
			throw new RuntimeException("no value for key : " + key + "@" + this);
		}
		return v;
	}

	private void __load_prop() {
		String name = this.getClass().getSimpleName() + ".properties";
		PropertiesLoader ldr = PropertiesLoader.Util.loaderFor(this, name);
		this._prop = ldr.load();
	}

}
