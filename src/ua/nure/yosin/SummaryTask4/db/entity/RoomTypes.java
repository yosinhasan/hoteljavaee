package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * Room types entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class RoomTypes extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = 2386302708905518585L;

	/**
	 * Type name.
	 */
	private String name;
	/**
	 * Description.
	 */
	private String description;
	/**
	 * Room price.
	 */
	private Integer price;
	/**
	 * Room avatar.
	 */
	private String image;
	/**
	 * Max adults per room.
	 */
	private Integer max;

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
	 * Get price.
	 * 
	 * @return Integer
	 */
	public final Integer getPrice() {
		return price;
	}

	/**
	 * Set price.
	 * 
	 * @param price2
	 *            the price to set
	 */
	public final void setPrice(final Integer price2) {
		this.price = price2;
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

	/**
	 * Get max.
	 * 
	 * @return the max
	 */
	public final Integer getMax() {
		return max;
	}

	/**
	 * Set max.
	 * 
	 * @param max2
	 *            the max to set
	 */
	public final void setMax(final Integer max2) {
		this.max = max2;
	}

	@Override
	public String toString() {
		return "Room types [name=" + name + ", getId()=" 
				+ getId() + " price=" + price + "]";
	}

}
