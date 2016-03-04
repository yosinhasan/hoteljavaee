package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Room album entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class RoomAlbum extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Room id.
	 */
	private Integer roomId;
	/**
	 * Room image.
	 */
	private String image;

	/**
	 * Get room id.
	 * 
	 * @return Integer
	 */
	public final Integer getRoomId() {
		return roomId;
	}

	/**
	 * Set room id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public final void setRoomId(final Integer id) {
		this.roomId = id;
	}
	/**
	 * Get image.
	 * 
	 * @return String
	 */
	public final String getImage() {
		return image;
	}

	/**
	 * Set image.
	 * 
	 * @param image2
	 *            the image to set
	 */
	public final void setImage(final String image2) {
		this.image = image2;
	}

	@Override
	public String toString() {
		return "Room album [image=" + image + ", getId()=" + getId() + "]";
	}

}
