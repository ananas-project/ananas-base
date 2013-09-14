package ananas.tools.autocopy;

import java.io.File;
import java.util.List;

public interface ProjectNode {

	String getName();

	List<File> listSrcDirectories();

	List<String> listSrcProjects();

	File getDestDirectory();

}
