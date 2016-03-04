package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Login command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ManagerCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ManagerCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command manager");
		request.setAttribute("command", "manager");
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		LOG.trace("Manager: " + user.getName());
		String forward = Path.PAGE_MANAGER;
		LOG.debug("Command manager");
		return forward;
	}

}