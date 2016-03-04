package ua.nure.yosin.SummaryTask4.db.bean;

import ua.nure.yosin.SummaryTask4.db.entity.Finance;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class FinanceBean extends Finance {

	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Check in.
	 */
	private String checkIn;
	/**
	 * Check out.
	 */
	private String checkOut;
	/**
	 * Username.
	 */
	private String username;
	/**
	 * Type.
	 */
	private String type;
	/**
	 * Number.
	 */
	private Integer number;

	/**
	 * Get check in.
	 * 
	 * @return the checkIn
	 */
	public final String getCheckIn() {
		return checkIn;
	}

	/**
	 * Set check in.
	 * 
	 * @param checkIn2
	 *            the checkIn to set
	 */
	public final void setCheckIn(final String checkIn2) {
		this.checkIn = checkIn2;
	}

	/**
	 * Get check out.
	 * 
	 * @return the checkOut
	 */
	public final String getCheckOut() {
		return checkOut;
	}

	/**
	 * Set check out.
	 * 
	 * @param checkOut2
	 *            the checkOut to set
	 */
	public final void setCheckOut(final String checkOut2) {
		this.checkOut = checkOut2;
	}

	/**
	 * Get username.
	 * 
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * Set username.
	 * 
	 * @param username2
	 *            the username to set
	 */
	public final void setUsername(final String username2) {
		this.username = username2;
	}

	/**
	 * Get type.
	 * 
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Set type.
	 * 
	 * @param type2
	 *            the type to set
	 */
	public final void setType(final String type2) {
		this.type = type2;
	}

	/**
	 * Get number.
	 * 
	 * @return the number
	 */
	public final Integer getNumber() {
		return number;
	}

	/**
	 * Set number.
	 * 
	 * @param number2
	 *            the number to set
	 */
	public final void setNumber(final Integer number2) {
		this.number = number2;
	}

	@Override
	public final String toString() {
		return "FinanceBean [username = " + username + " type = " + type + " checkIn = " + checkIn + " checkOut = "
				+ checkOut + " bill = " + getBill() + "]";
	}
}
