package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.AppException;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Settings command.
 * 
 * @author Hasan Yosin
 * 
 */
public class SettingsCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Settings info.
	 */
	private static final String INFO = "settingsInfo";
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(SettingsCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		LOG.trace("Set request attribute: command settins");
		request.setAttribute("command", "settings");
		HttpSession session = request.getSession();
		String forward = "";
		if (Method.isPost(request)) {
			String action = request.getParameter("action");
			LOG.trace("Action: " + action);
			if (Validator.isValidString(action) && action.equals("saveSettings")) {
				// obtain login and password from a request
				DBManagerMysql manager = DBManagerMysql.getInstance();
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String newPassword = request.getParameter("newPassword");
				User user = (User) session.getAttribute("user");
				LOG.trace("Request parameter: phone --> " + phone);
				LOG.trace("Request parameter: email --> " + email);
				LOG.trace("Request parameter: password --> " + password);
				LOG.trace("Request parameter: newPassword --> " + newPassword);
				if (Validator.isValidPassword(password, user.getPassword())) {
					if (Validator.isValidLength(newPassword, Integer.parseInt("5"))) {
						user.setPassword(newPassword);
					}
					if (Validator.isValidEmail(email)) {
						user.setEmail(email);
					}
					if (Validator.isValidPhone(phone)) {
						user.setPhone(phone);
					}
					LOG.trace("Update user info in DB: " + user);
					manager.updateUser(user);
					session.setAttribute(INFO, "Inforamtion updated.");

				} else {
					LOG.error("Failed to update changes");
					session.setAttribute(INFO, "Failed to update changes.");
				}

			}
			forward = Path.COMMAND_SETTINGS;
		} else {
			request.setAttribute(INFO, session.getAttribute(INFO));
			session.removeAttribute(INFO);
			Role userRole = (Role) session.getAttribute("userRole");
			if (userRole == Role.MANAGER) {
				forward = Path.PAGE_MANAGER_SETTINGS;
			} else if (userRole == Role.CLIENT) {
				forward = Path.PAGE_CLIENT_SETTINGS;
			}
		}
		LOG.debug("Command finished");
		return forward;
	}

}