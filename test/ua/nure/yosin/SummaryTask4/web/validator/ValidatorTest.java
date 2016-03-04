/**
 * 
 */
package ua.nure.yosin.SummaryTask4.web.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Hasan Yosin
 *
 */
public class ValidatorTest {

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidEmail(java.lang.String)}.
	 */
	@Test
	public void testIsValidEmail() {
		assertTrue(Validator.isValidEmail("yosinhasan@gmail.com"));
		assertFalse(Validator.isValidEmail(null));
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidNumber(java.lang.String)}.
	 */
	@Test
	public void testIsValidNumber() {
		assertTrue(Validator.isValidNumber("1"));
		assertFalse(Validator.isValidNumber(null));
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidPassword(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testIsValidPassword() {
		assertTrue(Validator.isValidPassword("1111111", "1111111"));
		assertFalse(Validator.isValidPassword(null, null));
		assertFalse(Validator.isValidPassword("1111", "1111"));
	
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidPhone(java.lang.String)}.
	 */
	@Test
	public void testIsValidPhone() {
		assertTrue(Validator.isValidPhone("+38(093)821-30-09"));
		assertFalse(Validator.isValidPhone(null));
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidString(java.lang.String)}.
	 */
	@Test
	public void testIsValidString() {
		assertTrue(Validator.isValidString("abcdefg"));
		assertFalse(Validator.isValidNumber(null));
	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidDate(java.lang.String)}.
	 */
	@Test
	public void testIsValidDate() {
		assertTrue(Validator.isValidDate("2016-02-10"));
		assertFalse(Validator.isValidDate(null));

	}

	/**
	 * Test method for {@link ua.nure.yosin.SummaryTask4.web.validator.Validator#isValidLength(java.lang.String, int)}.
	 */
	@Test
	public void testIsValidLength() {
		assertTrue(Validator.isValidLength("2016-02-10", 4));
		assertFalse(Validator.isValidLength(null, 3));
	}

}
