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
 * Client command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ClientCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * 
	 */
	private static final Logger LOG = 
			Logger.getLogger(ClientCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command client");
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		LOG.trace("Client: " + user.getName());
		request.setAttribute("command", "client");
		String forward = Path.PAGE_CLIENT;
		LOG.debug("Command finished");
		return forward;
	}

}