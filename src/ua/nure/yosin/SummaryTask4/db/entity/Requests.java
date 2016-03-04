package ua.nure.yosin.SummaryTask4.db.entity;

import ua.nure.yosin.SummaryTask4.db.RequestStatus;

/**
 * Requests entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Requests extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Amount.
	 */
	private Integer amount;
	/**
	 * Room types id.
	 */
	private Integer roomTypesId;
	/**
	 * Check in.
	 */
	private String checkIn;
	/**
	 * Check out.
	 */
	private String checkOut;
	/**
	 * User id.
	 */
	private Long userId;
	/**
	 * Request status id.
	 */
	private Integer requestStatusId;
	/**
	 * Request date.
	 */
	private String date;
	/**
	 * Request status.
	 */
	private RequestStatus status;

	/**
	 * Get amount.
	 * 
	 * @return the amount
	 */
	public final Integer getAmount() {
		return amount;
	}

	/**
	 * Set amount.
	 * 
	 * @param amount2
	 *            the amount to set
	 */
	public final void setAmount(final Integer amount2) {
		this.amount = amount2;
	}

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
	 * Get user id.
	 * 
	 * @return the userId
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

	/**
	 * Get request stautus id.
	 * 
	 * @return the requestStatusId
	 */
	public final Integer getRequestStatusId() {
		return requestStatusId;
	}

	/**
	 * Set request status id.
	 * 
	 * @param statusId
	 *            the statusId to set
	 */
	public final void setRequestStatusId(final Integer statusId) {
		this.requestStatusId = statusId;
	}

	/**
	 * Get date.
	 * 
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}

	/**
	 * Set date.
	 * 
	 * @param date2
	 *            the date to set
	 */
	public final void setDate(final String date2) {
		this.date = date2;
	}

	/**
	 * Get room type id.
	 * 
	 * @return the roomTypesId
	 */
	public final Integer getRoomTypesId() {
		return roomTypesId;
	}

	/**
	 * Set room type id.
	 * 
	 * @param roomTypesId2
	 *            the roomTypesId to set
	 */
	public final void setRoomTypesId(final Integer roomTypesId2) {
		this.roomTypesId = roomTypesId2;
	}

	/**
	 * Get status.
	 * 
	 * @return the status
	 */
	public final RequestStatus getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * 
	 * @param status2
	 *            the status to set
	 */
	public final void setStatus(final RequestStatus status2) {
		this.status = status2;
	}

	@Override
	public String toString() {
		return "Requests [user id =" + userId + ", getId()=" + getId() 
			+ " checkIn = " + checkIn + ", checkOut = "
			+ checkOut + ", amount = " + amount + " status = " + status + "]";
	}

}
