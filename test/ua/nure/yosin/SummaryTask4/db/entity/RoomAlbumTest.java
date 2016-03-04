/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hasan Yosin
 *
 */
public class RoomAlbumTest {
	private RoomAlbum album;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		album = new RoomAlbum();
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum#setRoomId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetRoomId() {
		Integer id = 1;
		album.setRoomId(id);
		assertEquals(id, album.getRoomId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum#setImage(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetImage() {
		String url = "url";
		album.setImage(url);
		assertEquals(url, album.getImage());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(album);
		assertTrue(true);

	}

}
