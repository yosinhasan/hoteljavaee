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

import ua.nure.yosin.SummaryTask4.db.FinanceStatus;

/**
 * @author elpai
 *
 */
public class FinanceTest {
	private Finance finance;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before 
	public void init() {
		finance = new Finance();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Finance#setReservationId(java.lang.Integer)}.
	 */
	@Test
	public void testSetReservationId() {
		finance.setReservationId(1);
		assertEquals(new Integer(1), finance.getReservationId());
	}


	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Finance#setBill(java.lang.Long)}.
	 */
	@Test
	public void testSetBill() {
		finance.setBill(new Long(1000));
		assertEquals(new Long(1000), finance.getBill());
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Finance#setStatus(ua.nure.yosin.SummaryTask4.db.FinanceStatus)}.
	 */
	@Test
	public void testSetStatus() {
		finance.setStatus(FinanceStatus.CONFIRMED);
		assertEquals(FinanceStatus.CONFIRMED, finance.getStatus());
		finance.setStatus(FinanceStatus.PAID);
		assertEquals(FinanceStatus.PAID, finance.getStatus());
		finance.setStatus(FinanceStatus.UNPAID);
		assertEquals(FinanceStatus.UNPAID, finance.getStatus());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Finance#setUserId(java.lang.Long)}.
	 */
	@Test
	public void testSetUserId() {
		finance.setUserId(new Long(1));
		assertEquals(new Long(1), finance.getUserId());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Finance#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(finance);
		assertTrue(true);
	}

}
