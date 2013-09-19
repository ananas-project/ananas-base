package ananas.tools.autocopy;

import java.io.File;
import java.util.Properties;

public interface AutoRunnable {

	void run(AutoContext context);

	File getFile();

	Properties getProperties();

	String getType();
}
