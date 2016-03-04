package ua.nure.yosin.SummaryTask4.db.entity;

import ua.nure.yosin.SummaryTask4.db.FinanceStatus;

/**
 * Finance entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Finance extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;
	/**
	 * Reservation id.
	 */
	private Integer reservationId;
	/**
	 * Bill.
	 */
	private Long bill;
	/**
	 * Finance status.
	 */
	private FinanceStatus status;
	/**
	 * User id.
	 */
	private Long userId;

	/**
	 * Get reservation id.
	 * 
	 * @return Integer the reservationId
	 */
	public final Integer getReservationId() {
		return reservationId;
	}

	/**
	 * Set reservation id.
	 * 
	 * @param reservationId2
	 *            the reservationId to set
	 */
	public final void setReservationId(final Integer reservationId2) {
		this.reservationId = reservationId2;
	}

	/**
	 * Get bill.
	 * 
	 * @return Integer the bill
	 */
	public final Long getBill() {
		return bill;
	}

	/**
	 * Set bill.
	 * 
	 * @param bill2
	 *            the bill to set
	 */
	public final void setBill(final Long bill2) {
		this.bill = bill2;
	}

	/**
	 * Get status.
	 * 
	 * @return FinanceStatus the status
	 */
	public final FinanceStatus getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * 
	 * @param status2
	 *            the status to set
	 */
	public final void setStatus(final FinanceStatus status2) {
		this.status = status2;
	}

	/**
	 * Get user id.
	 * 
	 * @return Long the userId
	 */
	public final Long getUserId() {
		return userId;
	}

	/**
	 * Set user id.
	 * 
	 * @param userId2
	 *            the userId to set
	 */
	public final void setUserId(final Long userId2) {
		this.userId = userId2;
	}

	@Override
	public String toString() {
		return "Finance [userId=" + userId + ", getId()=" + getId() 
		+ "bill" + bill + "]";
	}

}
