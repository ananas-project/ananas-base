package ananas.tools.autocopy;

import java.io.File;
import java.util.Properties;

public class AbstractAutoRunnable implements AutoRunnable {

	private final Properties _prop;
	private final File _file;

	public AbstractAutoRunnable(File file, Properties prop) {
		this._file = file;
		this._prop = prop;
	}

	@Override
	public File getFile() {
		return this._file;
	}

	@Override
	public Properties getProperties() {
		return this._prop;
	}

	@Override
	public void run(AutoContext context) {

		// File file = this.getFile();
		// System.out.println("" + this + " " + file);

	}

	@Override
	public String getType() {
		return this.getProperties().getProperty("node.type", "unknow");
	}

}
