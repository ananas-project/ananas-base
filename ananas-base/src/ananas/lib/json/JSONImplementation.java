package ananas.lib.json;

public interface JSONImplementation {

	class Factory {

		public static JSONImplementation getImplementation() {
			return JSONImplementationLoader.getImplementation(null);
		}

		public static JSONImplementation getImplementation(String classname) {
			return JSONImplementationLoader.getImplementation(classname);
		}

	}

	JSONArray createArray();

	JSONObject createObject();

}
