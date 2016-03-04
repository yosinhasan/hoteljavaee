package ua.nure.yosin.SummaryTask4.db.entity;

import ua.nure.yosin.SummaryTask4.db.ResponseStatus;

/**
 * Responses entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Responses extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;
	/**
	 * To user id.
	 */
	private Long toUserId;
	/**
	 * Reservation id.
	 */
	private Integer reservationId;
	/**
	 * Message.
	 */
	private String msg;
	/**
	 * Status.
	 */
	private ResponseStatus status;

	/**
	 * Get toUserId.
	 * 
	 * @return the toUserId
	 */
	public final Long getToUserId() {
		return toUserId;
	}

	/**
	 * Set toUserId.
	 * 
	 * @param toUserId2
	 *            the toUserId to set
	 */
	public final void setToUserId(final Long toUserId2) {
		this.toUserId = toUserId2;
	}

	/**
	 * Get reservation id.
	 * 
	 * @return the reservationId
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
	 * Get message.
	 * 
	 * @return the msg
	 */
	public final String getMsg() {
		return msg;
	}

	/**
	 * Set message.
	 * 
	 * @param msg2
	 *            the msg to set
	 */
	public final void setMsg(final String msg2) {
		this.msg = msg2;
	}

	/**
	 * Get status.
	 * 
	 * @return the status
	 */
	public final ResponseStatus getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * 
	 * @param status2
	 *            the status to set
	 */
	public final void setStatus(final ResponseStatus status2) {
		this.status = status2;
	}

	@Override
	public String toString() {
		return "Response [to user id =" + toUserId + ", getId()=" 
				+ getId() + ", reservationId = " + reservationId
				+ " status = " + status + " msg " + msg + "]";
	}

}
