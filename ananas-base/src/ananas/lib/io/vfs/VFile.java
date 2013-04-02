package ananas.lib.io.vfs;

import java.io.IOException;
import java.net.URI;

public interface VFile {

	VFileSystem getVFS();

	boolean exists();

	boolean mkdirs();

	boolean isDirectory();

	boolean createNewFile() throws IOException;

	VFile getParentFile();

	boolean isFile();

	URI getURI();

	String getName();

	class Factory {

		public static VFileSystem getVFS() {
			return VFS.getFactory().defaultFileSystem();
		}
	}

}
