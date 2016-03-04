package ua.nure.yosin.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * 
 * @author Hasan Yosin
 * 
 */
public abstract class Entity implements Serializable {
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 8466257860808346236L;
	/**
	 * Id.
	 */
	private Long id;

	/**
	 * Get id.
	 * 
	 * @return Long
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * Set id.
	 * 
	 * @param id2
	 *            id
	 */
	public final void setId(final Long id2) {
		this.id = id2;
	}

}
