package ua.nure.yosin.SummaryTask4;

import java.util.Locale;

/**
 * Locales.
 * 
 * @author Hasan Yosin
 * 
 */
public final class Locales {
	/**
	 * Ru locale.
	 */
	private static Locale ru = new Locale("ru", "RU");
	/**
	 * En locale.
	 */
	private static Locale en = Locale.ENGLISH;

	/**
	 * Get locale.
	 * 
	 * @param locale
	 *            locale to set
	 * @return Locale
	 */
	public static Locale getLocale(final String locale) {
		Locale loc = en;
		if (locale.equalsIgnoreCase("ru")) {
			loc = ru;
		}

		return loc;
	}
	/**
	 * Private constructor.
	 */
	private Locales() {
		
	}
}