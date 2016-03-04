package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.bean.ActivitiesAlbumBean;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Activity details command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ActivityDetailsCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			Logger.getLogger(ActivityDetailsCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		Integer id = Integer.parseInt(request.getParameter("id"));
		LOG.trace("Request parameter: id --> " + id);
		String forward = null;
		if (id > 0) {
			DBManagerMysql manager = DBManagerMysql.getInstance();
			ActivitiesAlbumBean activities = manager.getActivitiesAlbumBean(id);
			LOG.trace("Found in DB: activities album bean --> " + activities);
			if (activities != null) {
				LOG.trace("Set request attribute: activities album bean --> "
			+ activities);
				request.setAttribute("items", activities);
				forward = Path.PAGE_ACTIVITY_DETAILS;
			} else {
				forward = Path.PAGE_REDIRECT;
			}
		} else {
			forward = Path.PAGE_REDIRECT;
		}
		LOG.debug("Command finished");
		return forward;
	}

}