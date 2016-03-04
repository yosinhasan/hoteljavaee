package ua.nure.yosin.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;

/**
 * No command.
 * 
 * @author Hasan Yosin
 * 
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response) {
		LOG.debug("Command starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		LOG.error("Set the request attribute: errorMessage --> "
		+ errorMessage);

		LOG.debug("Command finished");
		return Path.PAGE_ERROR_PAGE;
	}

}