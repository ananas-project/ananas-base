package ananas.tools.autocopy;

import java.io.File;
import java.util.Properties;

public class AutoRunnForRoot extends AbstractAutoRunnable {

	public AutoRunnForRoot(File file, Properties prop) {
		super(file, prop);
	}

	public static AutoRunnableFactory getFactory() {
		return new AutoRunnableFactory() {

			@Override
			public AutoRunnable newRunnable(File file, Properties prop) {
				return new AutoRunnForRoot(file, prop);
			}
		};
	}

	@Override
	public void run(AutoContext context) {
		super.run(context);

		File root = this.getFile().getParentFile();

		this.__find(root, 100, context);

	}

	private void __find(File file, int depthLimit, AutoContext context) {

		if (file == null) {
			return;
		}

		if ("autocopy.properties".equals(file.getName())) {
			AutoCopy runn = new AutoCopy(file);
			String type = runn.getType();
			if ("project".equals(type)) {
				runn.run(context);
			}
		}

		File[] list = file.listFiles();
		if (list != null) {
			for (File child : list) {
				__find(child, depthLimit - 1, context);
			}
		}

	}

}
