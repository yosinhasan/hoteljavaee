package ua.nure.yosin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import ua.nure.yosin.SummaryTask4.utils.DateUtil;
import ua.nure.yosin.SummaryTask4.utils.Method;
import ua.nure.yosin.SummaryTask4.web.validator.Validator;

/**
 * Signup command.
 * 
 * @author Hasan Yosin
 * 
 */
public class SignupCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(SignupCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		DBManagerMysql manager = DBManagerMysql.getInstance();
		String forward = Path.PAGE_SIGNUP;
		User user = (User) session.getAttribute("user");
		if (user == null && Method.isPost(request)) {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			String regDate = DateUtil.getCurrentDate();
			forward = Path.COMMAND_CLIENT;
			List<String> errors = new ArrayList<String>();
			int count = 0;
			session.removeAttribute("errorSignUp");
			LOG.trace("Set request attribute: command signup");
			request.setAttribute("command", "signup");
			LOG.trace("Request parameter: name --> " + name);
			LOG.trace("Request parameter: surname --> " + surname);
			LOG.trace("Request parameter: phone --> " + phone);
			LOG.trace("Request parameter: email --> " + email);
			LOG.trace("Request parameter: password --> " + password);
			LOG.trace("Request parameter: repeatPassword --> " + repeatPassword);
			LOG.trace("Validate parameters: password -->" + password + " & repeatPassword --> " + repeatPassword);

			if (!Validator.isValidPassword(password, repeatPassword)) {
				errors.add("Password length must be more " + "than 5 and be equal to repeatPassword field");
				count++;
			}
			LOG.trace("Validate parameter: email --> " + email);
			if (!Validator.isValidEmail(email)) {
				errors.add("Illegal email format");
				count++;
			}
			LOG.trace("Validate parameter: phone --> " + phone);

			if (!Validator.isValidPhone(phone)) {
				errors.add("Illegal phone format");
				count++;
			}
			LOG.trace("Validate parameters: name -->" + name + " surname -->" + surname);
			if (!Validator.isValidString(name) || !Validator.isValidString(surname)) {
				errors.add("Illegal string format / " + "min length must be more than 2");
				count++;
			}
			if (count > 0) {
				forward = Path.PAGE_SIGNUP;
				session.setAttribute("errorSignUp", errors);
			} else {
				user = new User();
				user.setName(name);
				user.setSurname(surname);
				user.setPhone(phone);
				user.setEmail(email);
				user.setRegDate(regDate);
				user.setPassword(password);
				user.setIdrole(Role.CLIENT.ordinal() + 1);
				LOG.trace("Saving new user: " + user);
				Integer id = manager.addUser(user);
				LOG.trace("Id of user: id " + id);
				user.setId((long) id);
				session.setAttribute("user", user);
				LOG.trace("Set the session attribute: user --> " + user);
				session.setAttribute("userRole", Role.CLIENT);
				LOG.trace("Set the session attribute: userRole --> " + Role.CLIENT);
				LOG.info("User " + user + " logged as " + Role.CLIENT.toString().toLowerCase());
				forward = Path.COMMAND_CLIENT;
			}
		} else if (user != null) {
			forward = Path.COMMAND_INDEX;
		}
		LOG.debug("Command finished");
		return forward;
	}

}