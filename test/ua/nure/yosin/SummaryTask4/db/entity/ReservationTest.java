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
public class ReservationTest {
	private Reservation reservation;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		reservation = new Reservation();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setRoomId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetRoomId() {
		Integer id = 1;
		reservation.setRoomId(id);
		assertEquals(id, reservation.getRoomId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setCheckIn(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetCheckIn() {
		reservation.setCheckIn("10-10-2012");
		assertEquals("10-10-2012", reservation.getCheckIn());

	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setCheckOut(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetCheckOut() {
		reservation.setCheckOut("10-10-2012");
		assertEquals("10-10-2012", reservation.getCheckOut());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setUserId(java.lang.Long)}
	 * .
	 */
	@Test
	public void testSetUserId() {
		Long id = new Long(1);
		reservation.setUserId(id);
		assertEquals(id, reservation.getUserId());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setStatusId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetStatusId() {
		Integer id = 1;
		reservation.setStatusId(id);
		assertEquals(id, reservation.getStatusId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#setReservationDate(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetReservationDate() {
		reservation.setReservationDate("10-10-2012");
		assertEquals("10-10-2012", reservation.getReservationDate());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Reservation#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(reservation);
		assertTrue(true);
	}

}
