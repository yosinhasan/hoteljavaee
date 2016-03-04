package ua.nure.yosin.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;

/**
 * Logout command.
 * 
 * @author Hasan Yosin
 * 
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response) {
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}