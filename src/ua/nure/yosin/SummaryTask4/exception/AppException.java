package ua.nure.yosin.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author Hasan Yosin
 * 
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 8288779062647218916L;

	/**
	 * App exception.
	 */
	public AppException() {
		super();
	}

	/**
	 * App exception.
	 * 
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public AppException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * App exception.
	 * 
	 * @param message
	 *            message
	 */
	public AppException(final String message) {
		super(message);
	}

}
