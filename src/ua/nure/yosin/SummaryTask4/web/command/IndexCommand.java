package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Index command.
 * 
 * @author Hasan Yosin
 * 
 */
public class IndexCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(IndexCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response) 
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command index");
		request.setAttribute("command", "index");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		List<RoomTypes> roomTypes = manager.getAllRoomTypes();
		LOG.trace("Found in DB: roomTypes  " + roomTypes);
		LOG.trace("Set the request attribute: roomTypes --> " + roomTypes);
		request.setAttribute("roomTypes", roomTypes);
		String forward = Path.PAGE_HOTEL;
		LOG.debug("Command finished");
		return forward;
	}

}