package ananas.lib.io.vfs;

public interface VPathAbsolute extends VPath {

	boolean isSubOf(VPathAbsolute base);

	VPathRelative getOffset(VPathAbsolute base);

}
