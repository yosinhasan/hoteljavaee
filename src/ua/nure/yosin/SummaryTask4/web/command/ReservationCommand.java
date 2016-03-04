package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.bean.RoomBean;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Reservation command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ReservationCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Error.
	 */
	private static final String RESERVATIONERROR = "reservationError";
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ReservationCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		request.setAttribute("command", "reservation");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		HttpSession session = (HttpSession) request.getSession();
		String forward = Path.PAGE_RESERVATION;
		if (Method.isPost(request)) {
			session.removeAttribute("checkIn");
			session.removeAttribute("checkOut");
			session.removeAttribute(RESERVATIONERROR);
			String action = request.getParameter("action");
			LOG.trace("Request parameter: action " + action);
			if (action.equals("reservationTrue")) {
				LOG.trace("Set action name: show found in db information");
				String type = request.getParameter("roomTypeId");
				Integer roomTypesId = 0;
				if (Validator.isValidNumber(type)) {
					roomTypesId = Integer.parseInt(type);
				}
				String checkIn = request.getParameter("checkIn");
				String checkOut = request.getParameter("checkOut");
				List<RoomBean> rbean = null;
				if (!Validator.isValidDate(checkIn) || !Validator.isValidDate(checkOut)) {
					session.setAttribute("reservationError", "Illegal date format");
					LOG.error("Illegal date format");
				} else {
					request.getServletContext().setAttribute("act", "found");
					if (roomTypesId.equals(0)) {
						rbean = manager.getAvailableRooms(checkIn, checkOut);
					} else {
						rbean = manager.getAvailableRoomsById(checkIn, checkOut, roomTypesId);
					}
					session.setAttribute("checkIn", checkIn);
					session.setAttribute("checkOut", checkOut);
					LOG.trace("Found in DB: room bean " + rbean);
					LOG.trace("Set the request attribute: rbean -->" + rbean);
					request.getServletContext().setAttribute("data", rbean);
				}
			}
			forward = Path.COMMAND_RESERVATION;
		} else {
			String act = (String) request.getServletContext().getAttribute("act");
			if (act != null && act.equals("found")) {
				LOG.trace("Set action name: found");
				request.setAttribute("action", "found");
				request.setAttribute("rbean", request.getServletContext().getAttribute("data"));
				request.getServletContext().removeAttribute("act");
				request.getServletContext().removeAttribute("data");
			} else {
				LOG.trace("Set action name: initial information");
				List<RoomTypes> roomTypes = manager.getAllRoomTypes();
				LOG.trace("Found in DB: roomTypes  " + roomTypes);
				LOG.trace("Set the request attribute: roomTypes --> " + roomTypes);
				request.setAttribute("roomTypes", roomTypes);
				request.setAttribute("info", session.getAttribute("info"));
				request.setAttribute(RESERVATIONERROR, session.getAttribute(RESERVATIONERROR));
				request.setAttribute("action", "initial");
				session.removeAttribute(RESERVATIONERROR);
				session.removeAttribute("info");
			}
		}
		LOG.debug("Command finished");
		return forward;
	}

}