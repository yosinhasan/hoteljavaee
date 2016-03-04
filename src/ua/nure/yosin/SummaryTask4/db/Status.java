package ua.nure.yosin.SummaryTask4.db;

/**
 * Status entity.
 * 
 * @author Hasan Yosin
 * 
 */
public enum Status {
	/**
	 * Availabe.
	 */
	AVAILABLE,
	/**
	 * Not available.
	 */
	NOT_AVAILABLE,
	/**
	 * Booked.
	 */
	BOOKED,
	/**
	 * Busy.
	 */
	BUSY;
	/**
	 * Get name.
	 * 
	 * @return String
	 */
	public final String getName() {
		return name().toLowerCase();
	}
}