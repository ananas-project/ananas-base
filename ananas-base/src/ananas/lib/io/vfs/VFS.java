package ananas.lib.io.vfs;

import ananas.lib.util.SingletonLoader;

public final class VFS {

	private VFS() {
	}

	private static VFileSystemFactory _default_factory;

	public static VFileSystemFactory getDefaultFactory() {
		if (_default_factory == null) {
			_default_factory = (VFileSystemFactory) SingletonLoader
					.load(VFileSystemFactory.class);
		}
		return _default_factory;
	}

	public static VFileSystemFactory getFactory(String className) {
		return (VFileSystemFactory) SingletonLoader.load(className);
	}

}
