package ananas.lib.juke;

import java.util.Properties;

/**
 * juke(java micro-kernel) is a lite weight java component framework. it private
 * a mini singleton environment for kinds of other extends.
 * */

public interface Kernel {

	Module getModule(Class<?> api);

	ModuleManager getModuleManager();

	Properties getProperties();

	class Factory {

		public static Kernel create() {
			return KernelFactory.Agent.getFactory().create();
		}

	}

}
