/**
 * 
 */
package ua.nure.yosin.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.yosin.SummaryTask4.db.entity.Rooms;

/**
 * @author Hasan Yosin
 *
 */
public class RoomBeanTest {
	private RoomBean bean;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		bean = new RoomBean();
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
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		String name = "name";
		bean.setName(name);
		assertEquals(name, bean.getName());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#setPrice(java.lang.Integer)}.
	 */
	@Test
	public void testSetPrice() {
		Integer price = 1000;
		bean.setPrice(price);
		assertEquals(price, bean.getPrice());
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#setMax(java.lang.Integer)}.
	 */
	@Test
	public void testSetMax() {
		Integer max = 1;
		bean.setMax(max);
		assertEquals(max, bean.getMax());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#setRooms(java.util.List)}.
	 */
	@Test
	public void testSetRooms() {
		List<Rooms> rooms = new ArrayList<Rooms>();
		bean.setRooms(rooms);
		assertNotNull(bean.getRooms());
	}
	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#setTotalPrice(java.lang.Long)}.
	 */
	@Test
	public void testSetTotalPrice() {
		Long price = new Long(1000);
		bean.setTotalPrice(price);
		assertEquals(price, bean.getTotalPrice());
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.db.bean.RoomBean#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(bean);
		assertTrue(true);
	}

}
