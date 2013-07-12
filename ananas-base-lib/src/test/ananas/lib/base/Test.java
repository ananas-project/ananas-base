package test.ananas.lib.base;

import ananas.lib.io.Connector;
import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFileSystemFactory;

public class Test {

	public static void main(String[] arg) {

		// System.setProperty("ananas.lib.io.Connector", "abc.com");

		Connector connector = Connector.Factory.getDefault();
		connector.getConnectionFactoryRegistrar();

		VFileSystemFactory vfsf = VFS.getDefaultFactory();
		vfsf.defaultFileSystem();

	}

}
