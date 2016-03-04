package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.entity.Activities;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Activities command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ActivitiesCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ActivitiesCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command activities");
		request.setAttribute("command", "activities");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		List<Activities> activities = manager.getAllActivities();
		LOG.trace("Found in DB: activities  " + activities);
		LOG.trace("Set the request attribute: activities --> " + activities);
		request.setAttribute("activities", activities);
		String forward = Path.PAGE_ACTIVITIES;
		LOG.debug("Command finished");
		return forward;
	}

}