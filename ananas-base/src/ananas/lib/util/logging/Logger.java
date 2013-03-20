package ananas.lib.util.logging;

public interface Logger {

	void setLevel(Level level);

	void trace(String string);

	void warn(String string);

	void info(String string);

}
