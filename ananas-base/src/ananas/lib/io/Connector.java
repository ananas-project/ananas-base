package ananas.lib.io;

import java.io.IOException;
import java.net.URI;

import ananas.lib.util.SingletonLoader;

public interface Connector {

	Connection open(URI uri) throws IOException;

	Connection open(String uri) throws IOException;

	ConnectionFactoryRegistrar getConnectionFactoryRegistrar();

	class Factory {

		private static Connector _s_inst;

		public static Connector getDefault() {
			Connector inst = _s_inst;
			if (inst == null) {
				inst = (Connector) SingletonLoader.load(Connector.class);
				_s_inst = inst;
			}
			return inst;
		}

		public static Connector getInstance(String classname) {
			return (Connector) SingletonLoader.load(classname);
		}

	}

}
