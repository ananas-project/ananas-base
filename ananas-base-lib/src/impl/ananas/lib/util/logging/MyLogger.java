package impl.ananas.lib.util.logging;

import ananas.lib.util.logging.Level;
import ananas.lib.util.logging.Logger;

public class MyLogger implements Logger {

	public MyLogger(String name) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLevel(Level level) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void info(String string) {
		// TODO Auto-generated method stub

		StackTraceElement[] array = Thread.currentThread().getStackTrace();
		for (StackTraceElement ste : array) {
			System.out.println("StackTraceElement : " + ste);
		}

	}

	@Override
	public void error(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(Throwable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void error(String message, Throwable e) {
		// TODO Auto-generated method stub

	}

}
