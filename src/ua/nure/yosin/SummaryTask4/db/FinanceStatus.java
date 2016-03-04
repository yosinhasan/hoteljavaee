package ua.nure.yosin.SummaryTask4.db;

/**
 * Finance status entity.
 * 
 * @author Hasan Yosin
 * 
 */
public enum FinanceStatus {
	/**
	 * Paid.
	 */
	PAID,
	/**
	 * Unpaid.
	 */
	UNPAID,
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