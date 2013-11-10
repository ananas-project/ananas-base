package ananas.lib.io.vfs;

import java.io.IOException;
import java.net.URI;

public interface VFileSystem {

	VFileSystemFactory getFactory();

	VFileSystemConfiguration getConfiguration();

	// new file

	VFile newFile(VFile dir, String string);

	VFile newFile(String dir, String string);

	VFile newFile(String path);

	VFile newFile(URI uri);

	VFile createTempFile(String prefix, String suffix) throws IOException;

	VFile createTempFile(String prefix, String suffix, VFile directory) throws IOException;

	VFile[] listRoots();

	// separator

	String pathSeparator();

	String separator();

	char pathSeparatorChar();

	char separatorChar();

	VPathAbsolute newAbsolutePath(String string);

	VPathRelative newRelativePath(String string);

}
