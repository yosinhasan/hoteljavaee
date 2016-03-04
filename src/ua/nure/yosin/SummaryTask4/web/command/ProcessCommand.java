package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.FinanceStatus;
import ua.nure.yosin.SummaryTask4.db.Status;
import ua.nure.yosin.SummaryTask4.db.entity.Finance;
import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.DateUtil;
import ua.nure.yosin.SummaryTask4.utils.MailSession;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Process command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ProcessCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ProcessCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		request.setAttribute("command", "process");
		HttpSession session = (HttpSession) request.getSession();
		String forward = Path.COMMAND_RESERVATION;
		if (Method.isPost(request)) {
			String action = request.getParameter("action");
			LOG.trace("request parameter: action" + action);
			if (action != null && action.equals("booking")) {
				String number = request.getParameter("number");
				if (number == null || number.isEmpty()) {
					session.setAttribute("reservationError", "Request parameter number cannot be null/empty");
					LOG.error("Request parameter number cannot be null/empty");
				} else {
					Integer roomId = Integer.parseInt(number);
					LOG.trace("Request parameter: number" + roomId);
					String checkIn = (String) session.getAttribute("checkIn");
					LOG.trace("Session parameter: checkIn " + checkIn);
					String checkOut = (String) session.getAttribute("checkOut");
					LOG.trace("Session parameter: checkOut " + checkOut);
					if (!Validator.isValidDate(checkIn) || !Validator.isValidDate(checkOut)) {
						session.setAttribute("reservationError", "Illegal date format");
						LOG.error("Illegal date format");
					} else {
						session.removeAttribute("checkIn");
						session.removeAttribute("checkOut");
						DBManagerMysql manager = DBManagerMysql.getInstance();
						Reservation reservation = new Reservation();
						User user = (User) session.getAttribute("user");
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String date = dateFormat.format(new Date());
						reservation.setCheckIn(checkIn);
						reservation.setCheckOut(checkOut);
						reservation.setRoomId(roomId);
						reservation.setUserId(user.getId());
						reservation.setStatusId(Status.BOOKED.ordinal() + 1);
						reservation.setReservationDate(date);
						LOG.trace("Reservation entity for saving in DB " + reservation);
						Integer id = manager.addReservation(reservation);
						LOG.trace("Reservation data has been saved. " + "Id of added data: " + id);
						RoomTypes rType = manager.getRoomTypeByRoomID(roomId);
						Finance fin = new Finance();
						fin.setReservationId(id);
						fin.setStatus(FinanceStatus.UNPAID);
						fin.setUserId(user.getId());
						Long offset = DateUtil.getOffset(checkIn, checkOut);
						fin.setBill(rType.getPrice() * offset);
						manager.addFinance(fin);
						MailSession.sendReservationInfo(user, (String) session.getAttribute("locale"), reservation);
						session.setAttribute("info",
								"Room successfuly has been reserved."
										+ " We sent to your email account a short instruction"
										+ " for further action. Please check out notifications" + " in your cabinet.");
					}
				}
			}
		}
		LOG.debug("Command finished");
		return forward;
	}
}