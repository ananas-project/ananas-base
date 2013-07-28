package ananas.lib.localization;

import java.util.List;

import ananas.lib.util.SingletonLoader;

public interface LocalizationManager {

	void setCurrentLocale(Locale local);

	Locale getCurrentLocale();

	String getProperty(String key);

	String getProperty(Locale local, String key);

	void setProperty(Locale local, String key, String value);

	List<Locale> listAllLocales();

	List<Locale> getLookupOrder();

	void setLookupOrder(List<Locale> order);

	class Agent {

		private static LocalizationManager _inst;
		private static LocalizationManager _inst_safe;

		public static LocalizationManager getManager() {
			LocalizationManager inst = _inst;
			if (inst == null) {
				_inst = inst = getManagerSafe();
			}
			return inst;
		}

		private synchronized static LocalizationManager getManagerSafe() {
			LocalizationManager inst = _inst_safe;
			if (inst == null) {
				_inst_safe = inst = (LocalizationManager) SingletonLoader
						.load(LocalizationManager.class);
			}
			return inst;
		}
	}

}
