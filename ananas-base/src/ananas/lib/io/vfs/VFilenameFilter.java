package ananas.lib.io.vfs;

public interface VFilenameFilter {

	boolean accept(VFile file, String name);
}
