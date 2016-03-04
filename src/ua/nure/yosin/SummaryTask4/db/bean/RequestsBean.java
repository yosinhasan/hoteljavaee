package ua.nure.yosin.SummaryTask4.db.bean;

import ua.nure.yosin.SummaryTask4.db.entity.Requests;
import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class RequestsBean extends Requests {

	/**
	 * Version.
	 */
	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * User.
	 */
	private User user = null;

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
		return "RequestsBean [user = " + user + ", getId() = " 
				+ getId() + ", amount = " + getAmount() + ", checkIn = "
				+ getCheckIn() + ", checkOut = " + getCheckOut() 
				+ ", requestStatus = " + getStatus() + "]";
	}
}
