package ananas.lib.io.vfs;

public interface VFileSystemFactory {

	VFileSystem createFileSystem(VFileSystemConfiguration config);
}
