package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Activities entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class Activities extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Activity name.
	 */
	private String name;
	/**
	 * Description.
	 */
	private String description;
	/**
	 * Activity avatar.
	 */
	private String image;

	/**
	 * Get name.
	 * 
	 * @return String
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set name.
	 * 
	 * @param name2
	 *            type name
	 */
	public final void setName(final String name2) {
		this.name = name2;
	}

	/**
	 * Get description.
	 * 
	 * @return String
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Set description.
	 * 
	 * @param description2
	 *            the description to set
	 */
	public final void setDescription(final String description2) {
		this.description = description2;
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
		return "Activity [name=" + name + ", getId()=" + getId() + "]";
	}

}
