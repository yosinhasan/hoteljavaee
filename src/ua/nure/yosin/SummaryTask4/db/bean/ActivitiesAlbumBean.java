package ua.nure.yosin.SummaryTask4.db.bean;

import java.util.List;

import ua.nure.yosin.SummaryTask4.db.entity.Activities;
import ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class ActivitiesAlbumBean extends Activities {
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Album list.
	 */
	private List<ActivitiesAlbum> album;

	/**
	 * Get album.
	 * 
	 * @return the album
	 */
	public final List<ActivitiesAlbum> getAlbum() {
		return album;
	}

	/**
	 * Set album.
	 * 
	 * @param album2
	 *            the album to set
	 */
	public final void setAlbum(final List<ActivitiesAlbum> album2) {
		this.album = album2;
	}

	@Override
	public String toString() {
		return "ActivitiesAlbumBean [name=" 
				+ getName() + ", album=" + album + "]";
	}
}
