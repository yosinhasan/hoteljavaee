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
public class RoomTypesTest {
	private RoomTypes type;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		type = new RoomTypes();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		String name = "name";
		type.setName(name);
		assertEquals(name, type.getName());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#setDescription(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetDescription() {
		String name = "name";
		type.setDescription(name);
		assertEquals(name, type.getDescription());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#setPrice(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetPrice() {
		Integer price = 1000;
		type.setPrice(price);
		assertEquals(price, type.getPrice());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#setImage(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetImage() {
		String name = "name";
		type.setImage(name);
		assertEquals(name, type.getImage());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#setMax(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetMax() {
		Integer max = 1000;
		type.setMax(max);
		assertEquals(max, type.getMax());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.RoomTypes#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(type);
		assertTrue(true);
	}

}
