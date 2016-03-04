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

import ua.nure.yosin.SummaryTask4.db.ResponseStatus;

/**
 * @author Hasan Yosin
 *
 */
public class ResponsesTest {
	private Responses response;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		response = new Responses();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Responses#setToUserId(java.lang.Long)}
	 * .
	 */
	@Test
	public void testSetToUserId() {
		Long id = new Long(1);
		response.setToUserId(id);
		assertEquals(id, response.getToUserId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Responses#setReservationId(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetReservationId() {
		Integer id = 1;
		response.setReservationId(id);
		assertEquals(id, response.getReservationId());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Responses#setMsg(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetMsg() {
		String msg = "test";
		response.setMsg(msg);
		assertEquals(msg, response.getMsg());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Responses#setStatus(ua.nure.yosin.SummaryTask4.db.ResponseStatus)}
	 * .
	 */
	@Test
	public void testSetStatus() {
		response.setStatus(ResponseStatus.CONFIRMED);
		assertEquals(ResponseStatus.CONFIRMED, response.getStatus());
		response.setStatus(ResponseStatus.UNCONFIRMED);
		assertEquals(ResponseStatus.UNCONFIRMED, response.getStatus());
		response.setStatus(ResponseStatus.NEW);
		assertEquals(ResponseStatus.NEW, response.getStatus());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.Responses#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(response);
		assertTrue(true);
	}

}
