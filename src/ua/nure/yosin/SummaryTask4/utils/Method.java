/**
 * 
 */
package ua.nure.yosin.SummaryTask4.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Method.
 * 
 * @author Hasan Yosin
 *
 */
public final class Method {
	/**
	 * Is get.
	 * 
	 * @param request
	 *            request
	 * @return boolean
	 */
	public static boolean isGet(final HttpServletRequest request) {
		return request.getMethod().equals("GET");
	}

	/**
	 * Is post.
	 * 
	 * @param request
	 *            request
	 * @return boolean
	 */
	public static boolean isPost(final HttpServletRequest request) {
		return request.getMethod().equals("POST");
	}

	/**
	 * Private constructor.
	 */
	private Method() {

	}
}
