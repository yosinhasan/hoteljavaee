package ua.nure.yosin.SummaryTask4.db;

/**
 * Request status entity.
 * 
 * @author Hasan Yosin
 * 
 */
public enum RequestStatus {
	/**
	 * Unprocessed status.
	 */
	UNPROCESSED,
	/**
	 * Processed status.
	 */
	PROCESSED;
	/**
	 * Get name.
	 * 
	 * @return String
	 */
	public final String getName() {
		return name().toLowerCase();
	}
}