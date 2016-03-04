package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Room entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Rooms extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Number name.
	 */
	private Integer number;
	/**
	 * Room types id.
	 */
	private Integer roomTypesId;

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
	 * Get room types id.
	 * 
	 * @return the roomTypesId
	 */
	public final Integer getRoomTypesId() {
		return roomTypesId;
	}

	/**
	 * Set room types id.
	 * 
	 * @param roomTypesId2
	 *            the roomTypesId to set
	 */
	public final void setRoomTypesId(final Integer roomTypesId2) {
		this.roomTypesId = roomTypesId2;
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", getId()=" + getId() + "]";
	}

}
