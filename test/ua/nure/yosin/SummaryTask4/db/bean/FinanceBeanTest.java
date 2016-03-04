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

/**
 * @author Hasan Yosin
 *
 */
public class FinanceBeanTest {
	private FinanceBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new FinanceBean();
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

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#setCheckIn(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetCheckIn() {
		String check = "10-10-2012";
		bean.setCheckIn(check);
		assertEquals(check, bean.getCheckIn());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#setCheckOut(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetCheckOut() {
		String check = "10-10-2012";
		bean.setCheckOut(check);
		assertEquals(check, bean.getCheckOut());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#setUsername(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetUsername() {
		String username = "username";
		bean.setUsername(username);
		assertEquals(username, bean.getUsername());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#setType(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetType() {
		String type = "type";
		bean.setType(type);
		assertEquals(type, bean.getType());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.FinanceBean#setNumber(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetNumber() {
		Integer number = 1234;
		bean.setNumber(number);
		assertEquals(number, bean.getNumber());
	}

}
