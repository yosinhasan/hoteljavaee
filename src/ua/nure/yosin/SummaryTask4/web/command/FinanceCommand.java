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
import ua.nure.yosin.SummaryTask4.db.FinanceStatus;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.Status;
import ua.nure.yosin.SummaryTask4.db.bean.FinanceBean;
import ua.nure.yosin.SummaryTask4.db.entity.Finance;
import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.MailSession;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Finance command.
 * 
 * @author Hasan Yosin
 * 
 */
public class FinanceCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(FinanceCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		DBManagerMysql manager = DBManagerMysql.getInstance();
		String forward = Path.PAGE_ERROR_PAGE;
		LOG.trace("Set request attribute: command login");
		request.setAttribute("command", "finance");
		User user = (User) session.getAttribute("user");
		if (Role.getRole(user) == Role.CLIENT) {
			String action = request.getParameter("act");
			String id = request.getParameter("id");
			if (Validator.isValidNumber(id) && Validator.isValidNumber(action)) {
				Long finId = Long.parseLong(id);
				Integer act = Integer.parseInt(action);
				Finance fin = manager.findFinance(finId, user.getId());
				LOG.trace("Found in db: finance " + fin);
				if (fin != null) {
					if (act == 0) {
						LOG.trace("Action: delete ");
						LOG.trace("Reservation id to delete: " + fin.getReservationId());
						manager.deleteReservationById(fin.getReservationId());
						LOG.trace("Response id to delete: " + fin.getId());
						manager.deleteFinanceById(fin.getId());
					} else if (act == 1) {
						fin.setStatus(FinanceStatus.PAID);
						manager.updateFinanceStatus(fin);
						Reservation reservation = new Reservation();
						reservation.setId(fin.getReservationId().longValue());
						reservation.setStatusId(Status.BUSY.ordinal() + 1);
						manager.updateReservationStatus(reservation);

					}
				}
			}
			List<FinanceBean> finances = manager.getUnpaidFinancesByUserId(user.getId());
			LOG.trace("Found in db: finances " + finances);
			LOG.trace("Set the request attribute: finances " + finances);
			request.setAttribute("finances", finances);
			forward = Path.PAGE_CLIENT_FINANCE;
		} else if (Role.getRole(user) == Role.MANAGER) {
			if (Method.isPost(request)) {
				String action = request.getParameter("act");
				String id = request.getParameter("id");
				String uid = request.getParameter("userId");
				if (Validator.isValidNumber(id) && Validator.isValidNumber(action) && Validator.isValidNumber(uid)) {
					Long finId = Long.parseLong(id);
					Integer act = Integer.parseInt(action);
					Long userId = Long.parseLong(uid);
					Finance fin = manager.findFinance(finId, userId);
					LOG.trace("Found in db: finance " + fin);
					if (fin != null) {
						if (act == 0) {
							LOG.trace("Action: delete ");
							LOG.trace("Reservation id to delete: " + fin.getReservationId());
							manager.deleteReservationById(fin.getReservationId());
							LOG.trace("Response id to delete: " + fin.getId());
							manager.deleteFinanceById(fin.getId());
						} else if (act == 1) {
							fin.setStatus(FinanceStatus.CONFIRMED);
							manager.updateFinanceStatus(fin);
							User customer = manager.findUser(userId);
							LOG.trace("FOUND IN DB: customer " + customer);
							Reservation reserv = manager.getReservationById(fin.getReservationId().longValue());
							LOG.trace("FOUND IN DB: reservation " + reserv);
							MailSession.sendConfirmedReservationInfo(customer, (String) session.getAttribute("locale"),
									reserv);
						}
					}
				}
				forward = Path.COMMAND_FINANCE;
			} else {
				List<FinanceBean> finances = manager.getFinances();
				LOG.trace("Found in db: finances " + finances);
				LOG.trace("Set the request attribute: finances " + finances);
				request.setAttribute("finances", finances);
				forward = Path.PAGE_MANAGER_FINANCE;
			}
		}
		LOG.debug("Command finished");
		return forward;
	}

}