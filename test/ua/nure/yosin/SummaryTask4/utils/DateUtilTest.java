/**
 * 
 */
package ua.nure.yosin.SummaryTask4.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ua.nure.yosin.SummaryTask4.exception.DBException;

/**
 * @author Hasan Yosin
 *
 */
public class DateUtilTest {

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.utils.DateUtil#getOffset(java.lang.String, java.lang.String)}
	 * .
	 * 
	 * @throws DBException
	 */
	@Test
	public void testGetOffset() throws DBException {
		String checkIn = "2012-02-10";
		String checkOut = "2012-02-16";
		Long offset = DateUtil.getOffset(checkIn, checkOut);
		assertEquals(new Long(6), offset);
	}

	/**
	 * Test method for
	 * {@link ua.nure.yosin.SummaryTask4.utils.DateUtil#getCurrentDate()}.
	 */
	@Test
	public void testGetCurrentDate() {
		String date = DateUtil.getCurrentDate();
		assertNotNull(date);
	}

}
