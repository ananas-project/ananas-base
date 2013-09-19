package ananas.tools.autocopy;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class AutoRunnForConfig extends AbstractAutoRunnable {

	public AutoRunnForConfig(File file, Properties prop) {
		super(file, prop);
	}

	public static AutoRunnableFactory getFactory() {
		return new AutoRunnableFactory() {

			@Override
			public AutoRunnable newRunnable(File file, Properties prop) {
				return new AutoRunnForConfig(file, prop);
			}
		};
	}

	@Override
	public void run(AutoContext context) {

		super.run(context);

		// find all projects
		final File file0 = this.getFile();
		for (File pfile = file0; pfile != null; pfile = pfile.getParentFile()) {
			final File file1 = new File(pfile, "autocopy.properties");
			if (file1.exists()) {
				if (!this.__isEqual(file1, file0)) {
					AutoCopy runn = new AutoCopy(file1);
					runn.run(context);
					break;
				}
			}
		}

		// do copy
		{
			String s = this.getProperties().getProperty(Const.cp_dest_project);
			ProjectNode dest_prj = context.getProject(s);

			File dest_dir = dest_prj.getDestDirectory();
			this.__do_clear(dest_dir);

			List<String> src_prj_name_list = dest_prj.listSrcProjects();
			for (String src_name : src_prj_name_list) {
				ProjectNode src_prj = context.getProject(src_name);
				List<File> src_dir_list = src_prj.listSrcDirectories();
				for (File src_dir : src_dir_list) {
					this.__do_copy(src_dir, dest_dir);
				}
			}

		}
	}

	private void __do_copy(File src, File dest) {
		System.out.println("do copy " + dest + " << " + src);

	}

	private void __do_clear(File dest) {
		System.out.println("do clear " + dest);
	}

	private boolean __isEqual(File file1, File file0) {
		String p0 = file0.getPath() + "";
		String p1 = file1.getPath() + "";
		return p0.equals(p1);
	}
}
