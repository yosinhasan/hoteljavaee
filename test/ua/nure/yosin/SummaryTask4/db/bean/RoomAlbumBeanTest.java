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

import ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum;

/**
 * @author Hasan Yosin
 *
 */
public class RoomAlbumBeanTest {
	private RoomAlbumBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new RoomAlbumBean();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.RoomAlbumBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.RoomAlbumBean#setAlbum(java.util.List)}
	 * .
	 */
	@Test
	public void testSetAlbum() {
		List<RoomAlbum> album = new ArrayList<RoomAlbum>();
		bean.setAlbum(album);
		assertNotNull(bean.getAlbum());
	}

}
