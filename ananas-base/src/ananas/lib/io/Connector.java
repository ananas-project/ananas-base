package ananas.lib.io;

import java.io.IOException;

public interface Connector {

	Connection open(String uri) throws IOException;

	ConnectionFactoryRegistrar getConnectionFactoryRegistrar();

	class Factory {

		public static Connector getConnector() {
			return ConnectorBootstrap.getConnector(null);
		}

		public static Connector getConnector(String classname) {
			return ConnectorBootstrap.getConnector(classname);
		}

	}

}
