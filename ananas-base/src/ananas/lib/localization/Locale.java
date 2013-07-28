package ananas.lib.localization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Locale {

	Locale worldwide = F._new("worldwide", "worldwide");

	Locale en_us = F._new("en", "US");
	Locale zn_cn = F._new("zh", "CN");
	Locale zn_hk = F._new("zh", "HK");
	Locale zn_tw = F._new("zh", "TW");

	String getLanguage();

	String getZone();

	class Util {

		public static Locale fromString(String s) {
			return F.fromString(s);
		}

		public static List<Locale> listAllLocales() {
			Locale[] array = F.allLocale();
			List<Locale> list = new ArrayList<Locale>();
			for (Locale l : array) {
				list.add(l);
			}
			return list;
		}
	}

	class F {

		private static final Locale[] allLocale() {
			Locale[] all = { en_us, zn_cn, worldwide };
			return all;
		}

		private static Map<String, Locale> _locale_table;

		private static Locale fromString(String s) {
			Map<String, Locale> map = _locale_table;
			if (map == null) {
				map = new HashMap<String, Locale>();
				for (Locale local : allLocale()) {
					map.put(local.getLanguage(), local);
					map.put(local.getZone(), local);
					map.put(local.toString(), local);
				}
				_locale_table = map;
			}
			return map.get(s);
		}

		private static Locale _new(String lang, String zone) {
			return new MyImpl(lang, zone);
		}

		private static class MyImpl implements Locale {

			private final String _zone;
			private final String _lang;
			private final String _string;

			public MyImpl(String lang, String zone) {
				lang = lang.toLowerCase();
				zone = zone.toUpperCase();
				this._lang = lang;
				this._zone = zone;
				this._string = lang + "-" + zone;
			}

			@Override
			public String toString() {
				return this._string;
			}

			@Override
			public String getLanguage() {
				return this._lang;
			}

			@Override
			public String getZone() {
				return this._zone;
			}

		}

	}

}
