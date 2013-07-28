package impl.ananas.lib.localization;

import java.util.ArrayList;
import java.util.List;

import ananas.lib.localization.Locale;
import ananas.lib.localization.LocalizationManager;

public class LocalizationManagerImpl implements LocalizationManager {

	private Locale _cur_locale = Locale.worldwide;
	private List<Locale> _lookup_order;

	public LocalizationManagerImpl() {
		List<Locale> order = new ArrayList<Locale>();
		order.add(Locale.en_us);
		order.add(Locale.zn_cn);
		order.add(Locale.worldwide);
		this._lookup_order = order;
	}

	@Override
	public void setCurrentLocale(Locale locale) {
		if (locale != null) {
			this._cur_locale = locale;
		}
	}

	@Override
	public Locale getCurrentLocale() {
		return this._cur_locale;
	}

	@Override
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProperty(Locale local, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(Locale local, String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Locale> listAllLocales() {
		return Locale.Util.listAllLocales();
	}

	@Override
	public List<Locale> getLookupOrder() {
		return new ArrayList<Locale>(this._lookup_order);
	}

	@Override
	public void setLookupOrder(List<Locale> order) {
		List<Locale> list = new ArrayList<Locale>();
		for (Locale loc : order) {
			if (loc != null) {
				list.add(loc);
			}
		}
		this._lookup_order = list;
	}

}
