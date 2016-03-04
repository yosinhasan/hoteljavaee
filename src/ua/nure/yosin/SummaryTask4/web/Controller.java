package ua.nure.yosin.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.web.command.Command;
import ua.nure.yosin.SummaryTask4.web.command.CommandContainer;

/**
 * Main servlet controller.
 * 
 * @author Hasan Yosin
 * 
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 2423353715955164816L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(Controller.class);

	@Override
	protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main controller method.
	 * 
	 * @param request
	 *            request
	 * @param response
	 *            response
	 * @throws IOException
	 *             exception
	 * @throws ServletException
	 *             exception
	 */
	private void process(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {

		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		if (forward.contains("controller?command")) {
			response.sendRedirect(forward);
		} else {
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

}