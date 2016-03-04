package ua.nure.yosin.SummaryTask4.db.bean;

import java.util.List;

import ua.nure.yosin.SummaryTask4.db.entity.Entity;
import ua.nure.yosin.SummaryTask4.db.entity.Rooms;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class RoomBean extends Entity {
	/**
	 * Version.
	 */
	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Room price.
	 */
	private Integer price;
	/**
	 * Type name.
	 */
	private String name;
	/**
	 * Max adults per room.
	 */
	private Integer max;
	/**
	 * Rooms.
	 */
	private List<Rooms> rooms;
	/**
	 * Total price.
	 */
	private Long totalPrice;

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

	/**
	 * Get rooms.
	 * 
	 * @return the rooms
	 */
	public final List<Rooms> getRooms() {
		return rooms;
	}

	/**
	 * Set rooms.
	 * 
	 * @param rooms2
	 *            the rooms to set
	 */
	public final void setRooms(final List<Rooms> rooms2) {
		this.rooms = rooms2;
	}

	/**
	 * Get total price.
	 * 
	 * @return the totalPrice
	 */
	public final Long getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Set total price.
	 * 
	 * @param totalPrice2
	 *            the totalPrice to set
	 */
	public final void setTotalPrice(final Long totalPrice2) {
		this.totalPrice = totalPrice2;
	}

	@Override
	public final String toString() {
		return "RoomBean [name=" + name + ", getId()=" + getId() 
		+ " price=" + price + "rooms" + rooms + "]";
	}
}
