package ananas.tools.autocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class AutoCopy implements AutoRunnable {

	private final AutoRunnable _inner;

	public AutoCopy(String dest_project_name) {
		this._inner = new MyInner(dest_project_name);
	}

	public AutoCopy(File file /* Prop */) {
		Properties prop = this.__load(file);
		String type = prop.getProperty("node.type");
		AutoRunnableFactory factory = this.__get_factory(type);
		this._inner = factory.newRunnable(file, prop);
		System.out.println("load " + file + "  (" + type + ")");
	}

	private AutoRunnableFactory __get_factory(String type) {
		Map<String, AutoRunnableFactory> map = this.getFactoryManager();
		AutoRunnableFactory factory = map.get(type);
		return factory;
	}

	private Properties __load(File file) {
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(file);
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void main(String[] arg) {
		AutoContext context = new DefaultRuntimeContext();
		AutoRunnable runn = new AutoCopy(arg[0]);
		runn.run(context);
	}

	class MyInner implements AutoRunnable {

		private final String _dest_project_name;

		public MyInner(String dest_project_name) {
			this._dest_project_name = dest_project_name;
		}

		@Override
		public void run(AutoContext context) {
			try {

				URL url = this.getClass().getProtectionDomain().getCodeSource()
						.getLocation();
				final File file_begin = new File(url.toURI());
				File file = file_begin;
				CopyTask task = null;
				for (; file != null; file = file.getParentFile()) {
					File pfile = new File(file, "autocopy.properties");
					if (pfile.exists()) {
						AutoRunnable runn = new AutoCopy(pfile);
						if ("root".equals(runn.getType())) {
							runn.run(context);
							task = new CopyTask(context,
									this._dest_project_name);
							break;
						}
					}
				}
				if (task == null) {
					String msg = "cannot find 'autocopy.properties' in path : "
							+ file_begin;
					throw new RuntimeException(msg);
				} else {
					task.run();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public File getFile() {
			return null;
		}

		@Override
		public Properties getProperties() {
			return null;
		}

		@Override
		public String getType() {
			return null;
		}
	}

	private static Map<String, AutoRunnableFactory> s_runn_factory_table;

	private Map<String, AutoRunnableFactory> getFactoryManager() {
		Map<String, AutoRunnableFactory> map = s_runn_factory_table;
		if (map == null) {
			map = new Hashtable<String, AutoRunnableFactory>();
			{
				map.put("project", AutoRunnForProject.getFactory());
				map.put("root", AutoRunnForRoot.getFactory());
				map.put("config", AutoRunnForConfig.getFactory());
			}
			s_runn_factory_table = map;
		}
		return map;
	}

	@Override
	public File getFile() {
		return this._inner.getFile();
	}

	@Override
	public Properties getProperties() {
		return this._inner.getProperties();
	}

	@Override
	public void run(AutoContext context) {
		this._inner.run(context);
	}

	@Override
	public String getType() {
		return this._inner.getType();
	}

}
