package ananas.tools.autocopy;

public interface AutoContext {

	void putProject(ProjectNode project);

	ProjectNode getProject(String name);

	ProjectNode getProject(String name, boolean canBeNull);

}
