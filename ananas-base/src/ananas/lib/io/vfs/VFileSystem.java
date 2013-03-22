package ananas.lib.io.vfs;

public interface VFileSystem {

	VFileSystemFactory getFactory();

	VFileSystemConfiguration getConfiguration();

	class Factory {

		public static VFileSystemFactory getFactory() {
			return VFileSystemBootstrap.getFactory(null);
		}

		public static VFileSystemFactory getFactory(String classname) {
			return VFileSystemBootstrap.getFactory(classname);
		}
	}

}
