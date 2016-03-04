/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.bean;

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
public class ResponseBeanTest {
	private ResponseBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new ResponseBean();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setPrice(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetPrice() {
		Integer price = 1000;
		bean.setPrice(price);
		assertEquals(price, bean.getPrice());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setNumber(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetNumber() {
		Integer number = 123456;
		bean.setNumber(number);
		assertEquals(number, bean.getNumber());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setCheckIn(java.lang.String)}
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
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setCheckOut(java.lang.String)}
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
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setMax(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetMax() {
		Integer max = 123;
		bean.setMax(max);
		assertEquals(max, bean.getMax());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.bean.ResponseBean#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		String name = "name";
		bean.setName(name);
		assertEquals(name, bean.getName());
	}

}
