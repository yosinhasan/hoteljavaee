package ua.nure.yosin.SummaryTask4.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.Role;

/**
 * Security filter.
 * 
 * @author Hasan Yosin
 * 
 */
public class CommandAccessFilter implements Filter {
	/**
	 * Logger.
	 */
	private static final Logger LOG =
			Logger.getLogger(CommandAccessFilter.class);

	/**
	 * Access map.
	 */
	private Map<Role, List<String>> accessMap = 
			new HashMap<Role, List<String>>();
	/**
	 * Commons.
	 */
	private List<String> commons = new ArrayList<String>();
	/**
	 * Out of control.
	 */
	private List<String> outOfControl = new ArrayList<String>();
	@Override
	public final void destroy() {
		LOG.debug("Filter destruction starts");
		// do nothing
		LOG.debug("Filter destruction finished");
	}
	@Override
	public final void doFilter(final ServletRequest request, 
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Filter starts");

		if (accessAllowed(request)) {
			LOG.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String errorMessasge = "You do not have permission "
					+ "to access the requested resource";

			request.setAttribute("errorMessage", errorMessasge);
			LOG.trace("Set the request attribute: errorMessage --> " 
			+ errorMessasge);

			request.getRequestDispatcher(
					Path.PAGE_ERROR_PAGE).forward(request, response);
		}
	}
	/**
	 * Check whether access allowed.
	 * @param request request
	 * @return boolean
	 */
	private boolean accessAllowed(final ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String commandName = request.getParameter("command");
		if (commandName == null || commandName.isEmpty()) {
			return false;
		}

		if (outOfControl.contains(commandName)) {
			return true;
		}

		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}

		Role userRole = (Role) session.getAttribute("userRole");
		if (userRole == null) {
			return false;
		}

		return accessMap.get(userRole).contains(commandName) 
				|| commons.contains(commandName);
	}
	@Override
	public final void init(final FilterConfig fConfig) 
			throws ServletException {
		LOG.debug("Filter initialization starts");

		// roles
		accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
		accessMap.put(Role.MANAGER, asList(
				fConfig.getInitParameter("manager")));
		accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
		LOG.trace("Access map --> " + accessMap);

		// commons
		commons = asList(fConfig.getInitParameter("common"));
		LOG.trace("Common commands --> " + commons);

		// out of control
		outOfControl = asList(fConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);

		LOG.debug("Filter initialization finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str
	 *            parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(final String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}