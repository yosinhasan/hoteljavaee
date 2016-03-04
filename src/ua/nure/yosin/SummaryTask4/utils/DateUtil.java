/**
 * 
 */
package ua.nure.yosin.SummaryTask4.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.exception.DBException;
import ua.nure.yosin.SummaryTask4.exception.Messages;

/**
 * Date Util.
 * 
 * @author Hasan Yosin
 *
 */
public final class DateUtil {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(DateUtil.class);

	/**
	 * Get offset.
	 * 
	 * @param date1
	 *            date1
	 * @param date2
	 *            date2
	 * @return Long offset
	 * @throws DBException
	 */
	public static Long getOffset(String date1, String date2) throws DBException {
		LOG.debug("Get offset method starts");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		Long start = null;
		Long offset = null;
		try {
			date.setTime(dateFormat.parse(date1));
			start = date.getTimeInMillis();
			date.setTime(dateFormat.parse(date2));
			offset = date.getTimeInMillis();
			offset -= start;
			offset /= Integer.parseInt("86400000");
		} catch (ParseException e) {
			LOG.error(Messages.ERR_PARSE_EXCEPTION);
			throw new DBException(Messages.ERR_PARSE_EXCEPTION, e);
		}
		LOG.debug("Get offset method finished");
		return offset;
	}

	/**
	 * Get date.
	 * 
	 * @return String date
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		return dt.format(date).toString();
	}

	/**
	 * Get date time.
	 * 
	 * @return String datetime
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return dateFormat.format(new Date());
	}
}
