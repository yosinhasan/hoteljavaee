package ua.nure.yosin.SummaryTask4.exception;

/**
 * An exception that provides information on a database access error.
 * 
 * @author Hasan Yosin
 * 
 */
public class DBException extends AppException {

	private static final long serialVersionUID = -3550446897536410392L;

	/**
	 * DB exception.
	 */
	public DBException() {
		super();
	}

	/**
	 * DB exception.
	 * 
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public DBException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
