package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author Hasan Yosin
 * 
 */
public abstract class Command implements Serializable {
	private static final long serialVersionUID = 8879403039606311780L;

	/**
	 * Execution method for command.
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @return String (Address to go once the command is executed.)
	 * @throws IOException
	 *             io exception
	 * @throws ServletException
	 *             servlet exception
	 * @throws AppException
	 *             app exception
	 */
	public abstract String execute(HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException, ServletException, AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}