package test.ananas.lib.base;

import ananas.lib.io.Connector;
import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFileSystemFactory;
import ananas.lib.util.logging.Logger;

public class Test {

	static final Logger logger = Logger.Agent.getLogger();

	public static void main(String[] arg) {

		// System.setProperty("ananas.lib.io.Connector", "abc.com");
		logger.info("ananas.lib.io.Connector" + " -> " + "abc.com");

		Connector connector = Connector.Factory.getDefault();
		connector.getConnectionFactoryRegistrar();

		VFileSystemFactory vfsf = VFS.getDefaultFactory();
		vfsf.defaultFileSystem();

	}

}
