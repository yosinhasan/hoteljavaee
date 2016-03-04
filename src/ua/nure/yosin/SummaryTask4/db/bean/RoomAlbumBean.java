package ua.nure.yosin.SummaryTask4.db.bean;

import java.util.List;

import ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;

/**
 * 
 * @author Hasan Yosin
 * 
 */
public class RoomAlbumBean extends RoomTypes {

	private static final long serialVersionUID = -5654982557199337483L;
	/**
	 * Album.
	 */
	private List<RoomAlbum> album;

	/**
	 * Get album.
	 * 
	 * @return the album
	 */
	public final List<RoomAlbum> getAlbum() {
		return album;
	}

	/**
	 * Set album.
	 * 
	 * @param album2
	 *            the album to set
	 */
	public final void setAlbum(final List<RoomAlbum> album2) {
		this.album = album2;
	}

	@Override
	public final String toString() {
		return "RoomAlbumBean [name=" + getName() + ", album=" + album + "]";
	}
}
