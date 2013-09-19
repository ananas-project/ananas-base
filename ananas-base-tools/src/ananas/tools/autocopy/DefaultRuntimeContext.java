package ananas.tools.autocopy;

import java.util.HashMap;
import java.util.Map;

public class DefaultRuntimeContext implements AutoContext {

	private final Map<String, ProjectNode> _map_prj;

	public DefaultRuntimeContext() {
		this._map_prj = new HashMap<String, ProjectNode>();
	}

	@Override
	public void putProject(ProjectNode project) {
		String name = project.getName();
		this._map_prj.put(name, project);
	}

	@Override
	public ProjectNode getProject(String name) {
		return this.getProject(name, false);
	}

	@Override
	public ProjectNode getProject(String name, boolean canBeNull) {
		ProjectNode prj = this._map_prj.get(name);
		if (prj == null)
			if (!canBeNull) {
				throw new RuntimeException("cannot find project : " + name);
			}
		return prj;
	}

}
