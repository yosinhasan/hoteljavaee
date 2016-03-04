package ua.nure.yosin.SummaryTask4.db.bean;

import ua.nure.yosin.SummaryTask4.db.entity.Responses;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class ResponseBean extends Responses {

	/**
	 * Version.
	 */
	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Price.
	 */
	private Integer price;
	/**
	 * Number.
	 */
	private Integer number;
	/**
	 * Check in.
	 */
	private String checkIn;
	/**
	 * Check out.
	 */
	private String checkOut;
	/**
	 * Max.
	 */
	private Integer max;
	/**
	 * Name.
	 */
	private String name;

	/**
	 * Get price.
	 * 
	 * @return the price
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
	 * Get name.
	 * 
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set name.
	 * 
	 * @param name2
	 *            the name to set
	 */
	public final void setName(final String name2) {
		this.name = name2;
	}

	@Override
	public final String toString() {
		return "ResponseBean [price = " + price + ", getId() = " 
				+ getId() + ", amount = " + max + ", checkIn = "
				+ checkIn + ", checkOut = " + checkOut + "]";
	}
}
