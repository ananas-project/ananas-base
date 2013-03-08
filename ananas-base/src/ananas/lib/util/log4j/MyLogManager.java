package ananas.lib.util.log4j;

import org.apache.log4j.Logger;

public class MyLogManager {

	static {
		s_inst = new MyLogManager();
	}

	private final static MyLogManager s_inst;

	public static Logger getLogger(String name) {
		return s_inst.doGetLogger(name);
	}

	private MyLogManager() {
	}

	private Logger doGetLogger(String name) {
		return new MyLogger( name ) ;
	}
}
