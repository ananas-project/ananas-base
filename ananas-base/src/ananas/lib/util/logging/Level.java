package ananas.lib.util.logging;

public interface Level {

	Level ALL = Facotry.newInst(0, "ALL");
	Level TRACE = Facotry.newInst(1, "TRACE");
	Level DEBUG = Facotry.newInst(2, "DEBUG");
	Level INFO = Facotry.newInst(3, "INFO");
	Level WARN = Facotry.newInst(4, "WARN");
	Level ERROR = Facotry.newInst(5, "ERROR");
	Level FATAL = Facotry.newInst(6, "FATAL");
	Level OFF = Facotry.newInst(7, "OFF");

	int getNumber();

	String getText();

	class Facotry {

		public static Level newInst(int level, String string) {
			return new MyImpl(level, string);
		}

		private static class MyImpl implements Level {

			private final String mText;
			private final int mLevel;

			public MyImpl(int level, String string) {
				this.mText = string;
				this.mLevel = level;
			}

			public String toString() {
				return "Level" + this.mLevel + ":" + this.mText;
			}

			@Override
			public int getNumber() {
				return this.mLevel;
			}

			@Override
			public String getText() {
				return this.mText;
			}
		}
	}
}
