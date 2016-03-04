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
 * Login command.
 * 
 * @author Hasan Yosin
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		String forward = Path.PAGE_LOGIN;
		if (Method.isPost(request)) {
			HttpSession session = request.getSession();
			DBManagerMysql manager = DBManagerMysql.getInstance();
			String email = request.getParameter("email");
			LOG.trace("Request parameter: email --> " + email);
			String password = request.getParameter("password");
			forward = Path.PAGE_ERROR_PAGE;
			String error = null;
			session.removeAttribute("errorSignIn");
			LOG.trace("Set request attribute: command login");
			request.setAttribute("command", "login");
			User user = (User) session.getAttribute("user");
			if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
				if (user == null) {
					forward = Path.PAGE_LOGIN;
				} else {
					forward = Path.COMMAND_INDEX;
				}
			} else {
				if (Validator.isValidEmail(email)) {
					user = manager.findUserByEmail(email);
					LOG.trace("Found in DB: user --> " + user);
					if (user != null && Validator.isValidPassword(user.getPassword(), password)) {
						Role userRole = Role.getRole(user);
						LOG.trace("userRole --> " + userRole);

						if (userRole == Role.ADMIN) {
							forward = Path.COMMAND_ADMIN;
						}
						if (userRole == Role.MANAGER) {
							forward = Path.COMMAND_MANAGER;
						}
						if (userRole == Role.CLIENT) {
							forward = Path.COMMAND_CLIENT;
						}
						session.setAttribute("cabinet", forward);
						session.setAttribute("user", user);
						LOG.trace("Set the session attribute: user --> " + user);

						session.setAttribute("userRole", userRole);
						LOG.trace("Set the session attribute: userRole --> " + userRole);

						LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

					} else {
						forward = Path.PAGE_LOGIN;
						LOG.error("Cannot find user with such email/password");
						error = "Cannot find user with such email/password";
						session.setAttribute("errorSignIn", error);
					}
				} else {
					forward = Path.PAGE_LOGIN;
					LOG.error("Illegal email format");
					error = "Illegal email format";
					session.setAttribute("errorSignIn", error);
				}
			}
		}
		LOG.debug("Command finished");
		return forward;
	}
}