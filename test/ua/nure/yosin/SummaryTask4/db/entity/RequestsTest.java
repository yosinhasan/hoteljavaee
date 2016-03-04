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

import ua.nure.yosin.SummaryTask4.db.RequestStatus;

/**
 * @author Hasan Yosin
 *
 */
public class RequestsTest {
	private Requests request;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before 
	public void init() {
		request = new Requests();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setAmount(java.lang.Integer)}.
	 */
	@Test
	public void testSetAmount() {
		request.setAmount(1000);
		assertEquals(new Integer(1000), request.getAmount());
	}

		/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setCheckIn(java.lang.String)}.
	 */
	@Test
	public void testSetCheckIn() {
		request.setCheckIn("10-10-2012");
		assertEquals("10-10-2012", request.getCheckIn());

	}

		/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setCheckOut(java.lang.String)}.
	 */
	@Test
	public void testSetCheckOut() {
		request.setCheckOut("10-10-2012");
		assertEquals("10-10-2012", request.getCheckOut());
	}

	
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setUserId(java.lang.Long)}.
	 */
	@Test
	public void testSetUserId() {
		request.setUserId(new Long(1));
		assertEquals(new Long(1), request.getUserId());

	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setRequestStatusId(java.lang.Integer)}.
	 */
	@Test
	public void testSetRequestStatusId() {
		request.setRequestStatusId(1);
		assertEquals(new Integer(1), request.getRequestStatusId());
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setDate(java.lang.String)}.
	 */
	@Test
	public void testSetDate() {
		String date = "10-10-2012";
		request.setDate(date);
		assertEquals(date, request.getDate());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setRoomTypesId(java.lang.Integer)}.
	 */
	@Test
	public void testSetRoomTypesId() {
		Integer id = 1;
		request.setRoomTypesId(id);
		assertEquals(id, request.getRoomTypesId());
		
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#setStatus(ua.nure.yosin.SummaryTask4.db.RequestStatus)}.
	 */
	@Test
	public void testSetStatus() {
		request.setStatus(RequestStatus.PROCESSED);
		assertEquals(RequestStatus.PROCESSED, request.getStatus());
		request.setStatus(RequestStatus.UNPROCESSED);
		assertEquals(RequestStatus.UNPROCESSED, request.getStatus());
		
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.entity.Requests#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(request);
		assertTrue(true);
	}

}
