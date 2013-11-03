package ananas.lib.juke;

import ananas.lib.util.SingletonLoader;

/**
 * juke(java micro-kernel) is a lite weight java component framework. it private
 * a mini singleton environment for kinds of other extends.
 * */

public interface KernelFactory {

	Kernel create();

	class Agent {

		public static KernelFactory getFactory() {
			return (KernelFactory) SingletonLoader.load(KernelFactory.class);
		}

	}
}
