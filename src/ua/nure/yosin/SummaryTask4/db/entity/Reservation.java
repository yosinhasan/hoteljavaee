package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Reservation entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Reservation extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Number name.
	 */
	private Integer roomId;
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
	 * Status id.
	 */
	private Integer statusId;
	/**
	 * Reservation date.
	 */
	private String reservationDate;

	/**
	 * Get room id.
	 * 
	 * @return the roomId
	 */
	public final Integer getRoomId() {
		return roomId;
	}

	/**
	 * Set room id.
	 * 
	 * @param roomId2
	 *            the roomId to set
	 */
	public final void setRoomId(final Integer roomId2) {
		this.roomId = roomId2;
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
	 * Get stautus id.
	 * 
	 * @return the statusId
	 */
	public final Integer getStatusId() {
		return statusId;
	}

	/**
	 * Set status id.
	 * 
	 * @param statusId2
	 *            the statusId to set
	 */
	public final void setStatusId(final Integer statusId2) {
		this.statusId = statusId2;
	}

	/**
	 * Get reservation date.
	 * 
	 * @return the reservationDate
	 */
	public final String getReservationDate() {
		return reservationDate;
	}

	/**
	 * Set reservation date.
	 * 
	 * @param reservationDate2
	 *            the reservationDate to set
	 */
	public final void setReservationDate(final String reservationDate2) {
		this.reservationDate = reservationDate2;
	}

	@Override
	public String toString() {
		return "Reservation [room id =" + roomId + ", getId()=" 
			+ getId() + " checkIn = " + checkIn + ", checkOut = "
			+  checkOut + ", userId = " + userId 
			+ " statusId = " + statusId + "]";
	}

}
