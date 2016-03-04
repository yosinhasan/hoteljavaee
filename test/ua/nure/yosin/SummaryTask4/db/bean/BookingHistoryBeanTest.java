/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.yosin.SummaryTask4.db.Status;
import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * @author Hasan Yosin
 *
 */
public class BookingHistoryBeanTest {
	private BookingHistoryBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new BookingHistoryBean();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean#setStatus(ua.nure.yosin.SummaryTask4.db.Status)}
	 * .
	 */
	@Test
	public void testSetStatus() {
		bean.setStatus(Status.AVAILABLE);
		assertEquals(Status.AVAILABLE, bean.getStatus());
		bean.setStatus(Status.BOOKED);
		assertEquals(Status.BOOKED, bean.getStatus());
		bean.setStatus(Status.BUSY);
		assertEquals(Status.BUSY, bean.getStatus());
		bean.setStatus(Status.NOT_AVAILABLE);
		assertEquals(Status.NOT_AVAILABLE, bean.getStatus());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean#setNumber(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetNumber() {
		Integer number = 1234;
		bean.setNumber(number);
		assertEquals(number, bean.getNumber());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean#setType(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetType() {
		String type = "business";
		bean.setType(type);
		assertEquals(type, bean.getType());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean#setUser(ua.nure.yosin.SummaryTask4.db.entity.User)}
	 * .
	 */
	@Test
	public void testSetUser() {
		assertNotNull(bean.getUser());
		User user = new User();
		bean.setUser(user);
		assertNotNull(bean.getUser());
	}

}
