package ananas.tools.autocopy;

import java.io.File;
import java.util.List;

public class CopyTask implements Runnable {

	private final AutoContext _context;
	private final String _dest_project_name;

	public CopyTask(AutoContext context, String dest_project_name) {
		this._context = context;
		this._dest_project_name = dest_project_name;
	}

	public void run() {

		final AutoContext context = this._context;
		ProjectNode dest_prj = context.getProject(this._dest_project_name);
		this.__do_clear(dest_prj.getDestDirectory());

		List<String> src_prj_list = dest_prj.listSrcProjects();
		for (String src_prj_name : src_prj_list) {
			ProjectNode src_prj = context.getProject(src_prj_name);

			System.out
					.println("do copy project " + src_prj + " >> " + dest_prj);
			this.__do_copy(src_prj, dest_prj);
		}
	}

	private void __do_copy(ProjectNode src, ProjectNode dest) {
		List<File> src_dirs = src.listSrcDirectories();
		File dest_dir = dest.getDestDirectory();

		for (File src_dir : src_dirs) {
			System.out.println("do copy directory " + dest_dir + " << "
					+ src_dir);
		}

	}

	private void __do_clear(File file) {

		System.out.println("do clear " + file);

	}
}
