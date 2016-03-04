package ua.nure.yosin.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Booking history command.
 * 
 * @author Hasan Yosin
 * 
 */
public class BookingHistoryCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	/**
	 * Logger.
	 */
	private static final Logger LOG = 
			Logger.getLogger(BookingHistoryCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command bookingHistory");
		request.setAttribute("command", "bookingHistory");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		HttpSession session = (HttpSession) request.getSession();
		String action = request.getParameter("action");
		String forward = "";
		LOG.trace("Request parameter: action " + action);
		Role userRole = (Role) session.getAttribute("userRole");
		List<BookingHistoryBean> history = null;
		if (userRole == Role.MANAGER) {
			history = manager.getBookingHistory();
			List<Integer> info = manager.getInfo();
			LOG.trace("info: " + info);
			LOG.trace("Set request attribute: info " + info);
			request.setAttribute("info", info);
			forward = Path.PAGE_MANAGER_BOOKINGHISTORY;
		} else if (userRole == Role.CLIENT) {
			List<RoomTypes> roomTypes = manager.getAllRoomTypes();
			LOG.trace("Found in DB: roomTypes  " + roomTypes);
			LOG.trace("Set the request attribute: roomTypes --> " + roomTypes);
			request.setAttribute("roomTypes", roomTypes);
			User user = (User) session.getAttribute("user");
			history = manager.getBookingHistoryByUserId(user.getId());
			LOG.trace("Found in DB: booking history  " + history);
			forward = Path.PAGE_CLIENT_BOOKINGHISTORY;
		}
		LOG.trace("Set the request attribute: history --> " + history);
		request.setAttribute("history", history);
		LOG.debug("Command finished");
		return forward;
	}

}