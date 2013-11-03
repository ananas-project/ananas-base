package ananas.lib.juke;

import java.util.Properties;

/**
 * juke(java micro-kernel) is a lite weight java component framework. it private
 * a mini singleton environment for kinds of other extends.
 * */

public interface Kernel {

	Component getComponent(Class<?> api, String var_name);

	Component getComponent(Class<?> api);

	ComponentManager getComponentManager();

	Properties getProperties();

	class Factory {

		public static Kernel create() {
			return KernelFactory.Agent.getFactory().create();
		}

	}

}
