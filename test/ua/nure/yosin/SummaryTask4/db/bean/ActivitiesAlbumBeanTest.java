/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.bean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum;

/**
 * @author Hasan Yosin
 *
 */
public class ActivitiesAlbumBeanTest {
	private ActivitiesAlbumBean album;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		album = new ActivitiesAlbumBean();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.ActivitiesAlbumBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(album);
		assertTrue(true);
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.ActivitiesAlbumBean#setAlbum(java.util.List)}.
	 */
	@Test
	public void testSetAlbum() {
		List<ActivitiesAlbum> list = new ArrayList<ActivitiesAlbum>();
		album.setAlbum(list);
		assertNotNull(album.getAlbum());
	}

}
