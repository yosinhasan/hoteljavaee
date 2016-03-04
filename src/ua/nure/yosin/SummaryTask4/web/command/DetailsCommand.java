package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.bean.RoomAlbumBean;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Details command.
 * 
 * @author Hasan Yosin
 * 
 */
public class DetailsCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(DetailsCommand.class);

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
			RoomAlbumBean type = manager.getRoomAlbumBean(id);
			LOG.trace("Found in DB: room album bean --> " + type);
			if (type != null) {
				LOG.trace("Set request attribute: room album bean --> " + type);
				request.setAttribute("roomAlbumBean", type);
				forward = Path.PAGE_DETAILS;
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