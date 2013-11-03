package impl.ananas.lib.juke;

import ananas.lib.juke.Kernel;
import ananas.lib.juke.KernelFactory;

public class KernelFactoryImpl implements KernelFactory {

	@Override
	public Kernel create() {
		
		return  new KernelImpl()   ;
		
	}

}
