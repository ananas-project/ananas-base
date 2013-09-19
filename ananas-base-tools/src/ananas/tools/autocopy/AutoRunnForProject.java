package ananas.tools.autocopy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AutoRunnForProject extends AbstractAutoRunnable implements
		ProjectNode {

	public AutoRunnForProject(File file, Properties prop) {
		super(file, prop);
	}

	public static AutoRunnableFactory getFactory() {
		return new AutoRunnableFactory() {

			@Override
			public AutoRunnable newRunnable(File file, Properties prop) {
				return new AutoRunnForProject(file, prop);
			}
		};
	}

	@Override
	public void run(AutoContext context) {
		super.run(context);
		ProjectNode project = this;

		String name = project.getName();
		ProjectNode p2 = context.getProject(name, true);
		if (p2 == null) {
			System.out.println("find project : " + name);
		} else {
			String msg = "The project is overlap : " + name;
			throw new RuntimeException(msg);
		}
		context.putProject(project);
	}

	@Override
	public String getName() {
		return this.getProperties().getProperty(Const.project_name, "unknow");
	}

	@Override
	public List<File> listSrcDirectories() {

		String dirs = AutoUtil.getProperty(this.getProperties(),
				Const.cp_src_dir_name_list);
		List<String> words = AutoUtil.stringToWords(dirs, ';');
		File base = this.getFile().getParentFile();
		List<File> list = new ArrayList<File>();
		for (String word : words) {
			File src = new File(base, word);
			list.add(src);
		}
		return list;
	}

	@Override
	public File getDestDirectory() {
		String offs = this.getProperties().getProperty(Const.cp_dest_dir_name,
				"dest-unkonw");
		File base = this.getFile().getParentFile();
		return new File(base, offs);
	}

	@Override
	public List<String> listSrcProjects() {
		String s = this.getProperties().getProperty(Const.cp_src_project_list,
				"");
		List<String> list = AutoUtil.stringToWords(s, ';');
		return list;
	}

	@Override
	public String toString() {
		return "project " + this.getName();
	}

}
