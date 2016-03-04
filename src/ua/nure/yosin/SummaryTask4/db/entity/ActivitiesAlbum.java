package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Activities album entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class ActivitiesAlbum extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Activity id.
	 */
	private Integer activitiesId;
	/**
	 * Activity image.
	 */
	private String image;

	/**
	 * Get activities id.
	 * 
	 * @return Integer
	 */
	public final Integer getActivitiesId() {
		return activitiesId;
	}

	/**
	 * Set activities id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public final void setActivitiesId(final Integer id) {
		this.activitiesId = id;
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
		return "Activities album [image=" + image + ", getId()=" 
				+ getId() + "]";
	}

}
