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
public class ActivitiesTest {
	private Activities album;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 
	@Before
	public void init() {
		album = new Activities();
		album.setDescription("description");
		album.setName("test");
		album.setImage("test.jpg");
		album.setId(new Long(1));
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Activities#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		album.setName("name");
		assertEquals("name", album.getName());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Activities#setDescription(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetDescription() {
		album.setDescription("descript");
		assertEquals("descript", album.getDescription());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Activities#setImage(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetImage() {
		album.setImage("test");
		assertEquals("test", album.getImage());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Entity#setId(java.lang.Long)}
	 * .
	 */
	@Test
	public void testSetId() {
		album.setId(new Long(1));
		assertEquals(new Long(1), album.getId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Activities#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(album);
		assertTrue(true);
	}

}
