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
public class RoomsTest {
	private Rooms room;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		room = new Rooms();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Rooms#setNumber(java.lang.Integer)}.
	 */
	@Test
	public void testSetNumber() {
		Integer number = 1000;
		room.setNumber(number);
		assertEquals(number, room.getNumber());
	}

		/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Rooms#setRoomTypesId(java.lang.Integer)}.
	 */
	@Test
	public void testSetRoomTypesId() {
		Integer id = 100;
		room.setRoomTypesId(id);
		assertEquals(id, room.getRoomTypesId());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Rooms#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(room);
		assertTrue(true);
	}

}
