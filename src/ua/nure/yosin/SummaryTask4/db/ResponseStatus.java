package ua.nure.yosin.SummaryTask4.db;

/**
 * Response status entity.
 * 
 * @author Hasan Yosin
 * 
 */
public enum ResponseStatus {
	/**
	 * New.
	 */
	NEW,
	/**
	 * Unconfirmed.
	 */
	UNCONFIRMED,
	/**
	 * Confirmed.
	 */
	CONFIRMED;
	/**
	 * Get name.
	 * 
	 * @return String
	 */
	public final String getName() {
		return name().toLowerCase();
	}
}