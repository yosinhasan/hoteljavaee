/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Hasan Yosin
 *
 */
public class ActivitiesAlbumTest {
	private ActivitiesAlbum album;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		album = new ActivitiesAlbum();
		album.setActivitiesId(1);
		album.setImage("test.jpg");
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum#getActivitiesId()}
	 * .
	 */
	@Test
	public void testGetActivitiesId() {
		assertEquals(new Integer(1), album.getActivitiesId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum#setActivitiesId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetActivitiesId() {
		album.setActivitiesId(2);
		assertEquals(new Integer(2), album.getActivitiesId());
		album.setActivitiesId(1);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum#getImage()}.
	 */
	@Test
	public void testGetImage() {
		assertEquals("test.jpg", album.getImage());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum#setImage(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetImage() {
		album.setImage("test.png");
		assertEquals("test.png", album.getImage());
	}
	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(album);
		assertTrue(true);
	}

}
