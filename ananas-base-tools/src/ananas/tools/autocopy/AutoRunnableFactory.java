package ananas.tools.autocopy;

import java.io.File;
import java.util.Properties;

public interface AutoRunnableFactory {

	AutoRunnable newRunnable(File file, Properties prop);

}
