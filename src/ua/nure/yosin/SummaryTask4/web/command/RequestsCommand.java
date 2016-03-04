package ua.nure.yosin.SummaryTask4.web.command;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.RequestStatus;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.bean.RequestsBean;
import ua.nure.yosin.SummaryTask4.db.entity.Requests;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.DateUtil;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Requests command.
 * 
 * @author Hasan Yosin
 * 
 */
public class RequestsCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Request info.
	 */
	private static final String REQUESTINFO = "requestInfo";
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(RequestsCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command requests");
		request.setAttribute("command", "requests");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		String action = request.getParameter("action");
		HttpSession session = (HttpSession) request.getSession();
		LOG.trace("Request parameter: action " + action);
		List<RoomTypes> roomTypes = manager.getAllRoomTypes();
		LOG.trace("Found in DB: roomTypes  " + roomTypes);
		LOG.trace("Set the request attribute: roomTypes --> " + roomTypes);
		request.setAttribute("roomTypes", roomTypes);
		User user = (User) session.getAttribute("user");
		Long userId = user.getId();
		String forward = "";
		Role userRole = (Role) session.getAttribute("userRole");
		if (userRole == Role.CLIENT) {
			List<Requests> requests = null;
			forward = Path.PAGE_CLIENT_REQUESTS;
			if (Method.isPost(request)) {
				session.removeAttribute(REQUESTINFO);
				if (action != null && action.equals("requestTrue")) {
					String checkIn = request.getParameter("checkIn");
					LOG.trace("Request parameter: checkIn --> " + checkIn);
					String checkOut = request.getParameter("checkOut");
					LOG.trace("Request parameter: checkOut --> " + checkOut);
					Integer amount = 0;
					Integer roomTypesId = 0;
					if (Validator.isValidNumber(request.getParameter("roomTypesId"))) {
						roomTypesId = Integer.parseInt(request.getParameter("roomTypesId"));
					}
					if (Validator.isValidNumber(request.getParameter("amount"))) {
						amount = Integer.parseInt(request.getParameter("amount"));
					}
					LOG.trace("Request parameter: roomTypesId --> " + roomTypesId);
					LOG.trace("Request parameter: amount --> " + amount);
					if (Validator.isValidDate(checkIn) && Validator.isValidDate(checkOut)) {
						Integer requestStatusId = RequestStatus.UNPROCESSED.ordinal() + 1;
						LOG.trace("Request parameter: requestStatusId --> " + requestStatusId);
						String date = DateUtil.getCurrentDateTime();
						Requests newRequest = new Requests();
						newRequest.setAmount(amount);
						newRequest.setRoomTypesId(roomTypesId);
						newRequest.setCheckIn(checkIn);
						newRequest.setCheckOut(checkOut);
						newRequest.setUserId(userId);
						newRequest.setRequestStatusId(requestStatusId);
						newRequest.setDate(date);
						Integer cond = manager.addRequest(newRequest);
						if (cond > 0) {
							session.setAttribute(REQUESTINFO, "Success. Request added.");
						} else {
							session.setAttribute(REQUESTINFO, "Failure. Request not added.");

						}
					} else {
						session.setAttribute(REQUESTINFO, "failure");
					}
				}
				forward = Path.COMMAND_REQUESTS;
			} else {
				requests = manager.getRequestsByUserId(userId);
				LOG.trace("Found in db user requests: " + requests);
				LOG.trace("Set request attribute");
				request.setAttribute("requests", requests);
				request.setAttribute(REQUESTINFO, session.getAttribute(REQUESTINFO));
				session.removeAttribute(REQUESTINFO);
			}
		} else if (userRole == Role.MANAGER) {
			if (Method.isPost(request)) {
				String act = request.getParameter("action");
				if (Validator.isValidString(act)) {
					LOG.trace("action: " + act);
					String rid = request.getParameter("requestId");
					String uid = request.getParameter("userId");
					if (act.equals("decline") && Validator.isValidNumber(rid) && Validator.isValidNumber(uid)) {
						Long requestId = Long.parseLong(rid);
						Long customerId = Long.parseLong(uid);
						LOG.trace("Request id to delete: " + requestId);
						LOG.trace("User id : " + customerId);
						manager.deleteRequestById(requestId, customerId);
					}
				}
				forward = Path.COMMAND_REQUESTS;
			} else {
				List<RequestsBean> requests = null;
				forward = Path.PAGE_MANAGER_REQUESTS;
				requests = manager.getAllRequests();
				LOG.trace("Found in db user requests: " + requests);
				LOG.trace("Set request attribute");
				request.setAttribute("requests", requests);
			}
		}

		LOG.debug("Command finished");
		return forward;
	}

}