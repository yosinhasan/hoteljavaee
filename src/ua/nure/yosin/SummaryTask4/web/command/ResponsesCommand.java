package ua.nure.yosin.SummaryTask4.web.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.FinanceStatus;
import ua.nure.yosin.SummaryTask4.db.RequestStatus;
import ua.nure.yosin.SummaryTask4.db.ResponseStatus;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.Status;
import ua.nure.yosin.SummaryTask4.db.bean.ResponseBean;
import ua.nure.yosin.SummaryTask4.db.bean.RoomBean;
import ua.nure.yosin.SummaryTask4.db.entity.Finance;
import ua.nure.yosin.SummaryTask4.db.entity.Requests;
import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.Responses;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.DateUtil;
import ua.nure.yosin.SummaryTask4.utils.MailSession;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Responses command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ResponsesCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Info.
	 */
	private static final String RESPONSESINFO = "responseInfo";
	/**
	 * Error.
	 */
	private static final String RESPONSESERROR = "responseError";
	/**
	 * User id.
	 */
	private static final String USERID = "userId";
	/**
	 * Request id.
	 */
	private static final String REQUESTID = "requestId";
	/**
	 * Check in.
	 */
	private static final String CHECKIN = "checkIn";
	/**
	 * Check out.
	 */
	private static final String CHECKOUT = "checkOut";
	/**
	 * Illegal date format.
	 */
	private static final String ERR_DATE_FORMAT = "Illegal date format";
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ResponsesCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response)
			throws AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command responses");
		request.setAttribute("command", "responses");
		HttpSession session = (HttpSession) request.getSession();
		String forward = "";
		Role userRole = (Role) session.getAttribute("userRole");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		session.removeAttribute(RESPONSESERROR);
		LOG.trace("Role: " + userRole);
		Long userId = null;
		if (userRole == Role.CLIENT) {
			User user = (User) session.getAttribute("user");
			if (Method.isPost(request)) {
				String action = request.getParameter("action");
				LOG.trace("The request parameter: action " + action);
				if (Validator.isValidString(action)) {
					Long id = null;
					Long reservationId = null;
					if (Validator.isValidNumber(request.getParameter("id"))) {
						id = Long.parseLong(request.getParameter("id"));
					}
					if (Validator.isValidNumber(request.getParameter("reservationId"))) {
						reservationId = Long.parseLong(request.getParameter("reservationId"));
					}
					LOG.trace("Response id: " + id);
					LOG.trace("Reservation id " + reservationId);
					if (id != null && reservationId != null && id > 0 && reservationId > 0) {
						Responses responses = new Responses();
						Reservation reservation = new Reservation();
						reservation = manager.getReservationById(reservationId);
						responses.setId(id);
						if (action.equals("confirm")) {
							reservation.setStatusId(Status.BOOKED.ordinal() + 1);
							responses.setStatus(ResponseStatus.CONFIRMED);
						} else {
							reservation.setStatusId(Status.AVAILABLE.ordinal() + 1);
							responses.setStatus(ResponseStatus.UNCONFIRMED);
						}

						Long offset = DateUtil.getOffset(reservation.getCheckIn(), reservation.getCheckOut());
						RoomTypes rType = manager.getRoomTypeByRoomID(reservation.getRoomId());
						if (offset > 0) {
							Finance fin = new Finance();
							fin.setReservationId(reservationId.intValue());
							fin.setStatus(FinanceStatus.UNPAID);
							fin.setUserId(user.getId());
							fin.setBill(rType.getPrice() * offset);
							manager.addFinance(fin);
							LOG.trace("Update response status " + responses.getStatus());
							manager.updateResponse(responses);
							LOG.trace("Update reservation status " + Status.values()[reservation.getStatusId() - 1]);
							manager.updateReservationStatus(reservation);
							session.setAttribute(RESPONSESINFO,
									"We congratulate you with booking a room, a short instruction has been sent to your email.");
							if (action.equals("confirm")) {
								MailSession.sendReservationInfo(user, (String) session.getAttribute("locale"),
										reservation);
							}
						} else {
							manager.deleteReservationById(reservation.getId().intValue());
							manager.deleteResponseById(responses.getId().intValue());
						}

					}
					forward = Path.COMMAND_RESPONSES;
				}
			} else {
				List<ResponseBean> responses = manager.getAllResponseBean(user.getId());
				LOG.trace("Found in db: responses " + responses);
				LOG.trace("Set resquest attribute: clientResponses " + responses);
				request.setAttribute("clientResponses", responses);
				forward = Path.PAGE_CLIENT_RESPONSES;
			}
		} else if (userRole == Role.MANAGER) {
			forward = Path.PAGE_MANAGER_RESPONSES;
			if (Method.isPost(request)) {
				String action = request.getParameter("action");
				LOG.trace("Request parameter: action " + action);
				if (Validator.isValidString(action) && action.equals("responesTrue")) {
					String number = request.getParameter("number");
					if (!Validator.isValidNumber(number)) {
						session.setAttribute(RESPONSESERROR, "Request parameter number cannot be null/empty");
						forward = Path.PAGE_MANAGER_RESPONSES;
						LOG.error("Request parameter number cannot be null/empty");
					} else {
						Integer roomId = Integer.parseInt(number);
						LOG.trace("Request parameter: number" + roomId);
						String checkIn = (String) session.getAttribute(CHECKIN);
						LOG.trace("Session parameter: checkIn " + checkIn);
						String checkOut = (String) session.getAttribute(CHECKOUT);
						LOG.trace("Session parameter: checkOut " + checkOut);
						if (!Validator.isValidDate(checkIn) || !Validator.isValidDate(checkOut)) {
							session.setAttribute(RESPONSESERROR, ERR_DATE_FORMAT);
							forward = Path.PAGE_MANAGER_RESPONSES;
							LOG.error(ERR_DATE_FORMAT);
						} else {
							userId = (Long) session.getAttribute(USERID);
							Long requestId = (Long) session.getAttribute(REQUESTID);
							if (userId > 0 && requestId > 0) {
								session.removeAttribute(CHECKIN);
								session.removeAttribute(CHECKOUT);
								session.removeAttribute(USERID);
								session.removeAttribute(REQUESTID);
								Reservation reservation = new Reservation();
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								String date = dateFormat.format(new Date());
								reservation.setCheckIn(checkIn);
								reservation.setCheckOut(checkOut);
								reservation.setRoomId(roomId);
								reservation.setUserId(userId);
								reservation.setStatusId(Status.NOT_AVAILABLE.ordinal() + 1);
								reservation.setReservationDate(date);
								LOG.trace("Reservation entity for saving in DB " + reservation);
								Integer id = manager.addReservation(reservation);
								if (id > 0) {
									LOG.trace("Reservation data has been saved." + " Id of added data: " + id);
									String msg = request.getParameter("msg");
									if (msg.length() <= 0) {
										msg = "";
									}
									LOG.trace("The request parameter: msg " + msg);
									Responses responses = new Responses();
									responses.setReservationId(id);
									responses.setToUserId(userId);
									responses.setStatus(ResponseStatus.NEW);
									responses.setMsg(msg);
									LOG.trace("Responses entity for saving in DB " + responses);
									Integer respId = manager.addResponse(responses);
									if (respId > 0) {
										Requests requests = new Requests();
										requests.setId(requestId);
										requests.setStatus(RequestStatus.PROCESSED);
										manager.updateRequests(requests);
										session.setAttribute("info", "Request successfuly has been processed.");
										forward = Path.COMMAND_REQUESTS;
									} else {
										manager.deleteReservationById(id);
										session.setAttribute(RESPONSESERROR,
												"Cannot add " + "new reservation for response");
										forward = Path.PAGE_MANAGER_RESPONSES;
										LOG.error("Cannot add " + "new reservation for response");
									}

								} else {
									LOG.trace("Something went wrong." + " Deservation data has not been saved.");
									forward = Path.PAGE_MANAGER_RESPONSES;
								}

							} else {
								session.setAttribute(RESPONSESERROR, "UserId not existed");
								forward = Path.PAGE_MANAGER_RESPONSES;
								LOG.error("UserId not existed");
							}
						}
						session.removeAttribute(CHECKIN);
						session.removeAttribute(CHECKOUT);
						session.removeAttribute(USERID);

					}

				} else {
					String type = request.getParameter("roomTypesId");
					Integer roomTypesId = 0;
					if (Validator.isValidNumber(type)) {
						roomTypesId = Integer.parseInt(type);
					}
					String checkIn = request.getParameter(CHECKIN);
					String checkOut = request.getParameter(CHECKOUT);

					String amt = request.getParameter("amount");
					String uId = request.getParameter(USERID);
					String rId = request.getParameter(REQUESTID);
					Integer amount = 0;
					userId = null;
					Long requestId = null;
					if (Validator.isValidNumber(amt)) {
						amount = Integer.parseInt(amt);
					}
					if (Validator.isValidNumber(uId)) {
						userId = Long.parseLong(uId);
					}
					if (Validator.isValidNumber(rId)) {
						requestId = Long.parseLong(rId);
					}
					LOG.trace("Set the request attribute: amount " + amount);
					request.setAttribute("amount", amount);
					List<RoomBean> rbean = null;
					if (!Validator.isValidDate(checkIn) || !Validator.isValidDate(checkOut)) {
						session.setAttribute(RESPONSESERROR, ERR_DATE_FORMAT);
						LOG.error(ERR_DATE_FORMAT);
					} else {
						request.setAttribute("action", "found");
						if (roomTypesId.equals(0)) {
							rbean = manager.getAvailableRooms(checkIn, checkOut);
						} else {
							rbean = manager.getAvailableRoomsById(checkIn, checkOut, roomTypesId);
						}

						session.setAttribute(CHECKIN, checkIn);
						session.setAttribute(CHECKOUT, checkOut);
						session.setAttribute(USERID, userId);
						session.setAttribute(REQUESTID, requestId);
						LOG.trace("Found in DB: room bean " + rbean);
						LOG.trace("Set the request attribute: rbean -->" + rbean);
						request.getServletContext().setAttribute("data", rbean);
					}
					forward = Path.COMMAND_RESPONSES;
				}
			} else {
				if (session.getAttribute(CHECKIN) == null || session.getAttribute(CHECKOUT) == null) {
					forward = Path.COMMAND_REQUESTS;
				} else {
					request.setAttribute("rbean", request.getServletContext().getAttribute("data"));
					request.getServletContext().removeAttribute("data");
				}
			}

		}
		LOG.debug("Command finished");
		return forward;
	}

}