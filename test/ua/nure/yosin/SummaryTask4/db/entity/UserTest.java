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
public class UserTest {
	private User user;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void init() {
		user = new User();
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
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		String name = "name";
		user.setName(name);
		assertEquals(name, user.getName());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setSurname(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetSurname() {
		String name = "name";
		user.setSurname(name);
		assertEquals(name, user.getSurname());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setRegDate(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetRegDate() {
		String date = "10-10-2012";
		user.setRegDate(date);
		assertEquals(date, user.getRegDate());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setEmail(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetEmail() {
		String email = "email@email.com";
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setPassword(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetPassword() {
		String password = "qwerty";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setIdrole(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testSetIdrole() {
		Integer id = 1;
		user.setIdrole(id);
		assertEquals(id, user.getIdrole());
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#setPhone(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetPhone() {
		String phone = "123456789";
		user.setPhone(phone);
		assertEquals(phone, user.getPhone());
	
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.db.entity.User#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(user);
		assertTrue(true);
	}

}
