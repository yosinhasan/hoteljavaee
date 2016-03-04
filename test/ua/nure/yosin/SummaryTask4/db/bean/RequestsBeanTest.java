/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.bean;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * @author Hasan Yosin
 *
 */
public class RequestsBeanTest {
	private RequestsBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new RequestsBean();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RequestsBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RequestsBean#setUser(ua.nure.yosin.SummaryTask4.db.entity.User)}.
	 */
	@Test
	public void testSetUser() {
		bean.setUser(bean.getUser());
		assertNotNull(bean);
		User user = new User();
		bean.setUser(user);
		assertNotNull(bean);
	}

}
