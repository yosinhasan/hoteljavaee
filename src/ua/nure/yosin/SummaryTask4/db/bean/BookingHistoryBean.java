package ua.nure.yosin.SummaryTask4.db.bean;

import ua.nure.yosin.SummaryTask4.db.Status;
import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class BookingHistoryBean extends Reservation {

	/**
	 * Version.
	 */
	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Status.
	 */
	private Status status;
	/**
	 * Room number.
	 */
	private Integer number;
	/**
	 * Room type.
	 */
	private String type;
	/**
	 * User.
	 */
	private User user = null;

	/**
	 * Get status.
	 * 
	 * @return the status
	 */
	public final Status getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * 
	 * @param status2
	 *            the status to set
	 */
	public final void setStatus(final Status status2) {
		this.status = status2;
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
	 * Get user.
	 * 
	 * @return the user
	 */
	public final User getUser() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	/**
	 * Set user.
	 * 
	 * @param user2
	 *            the user to set
	 */
	public final void setUser(final User user2) {
		this.user = user2;
	}

	@Override
	public final String toString() {
		return "BookingHistoryBean [userId = " 
				+ getUserId() + ", getId() = " + getId() + ",number = " + number
				+ ",type = " + type + ",status = " + status + "]";
	}
}
