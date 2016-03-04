package ua.nure.yosin.SummaryTask4.web.filter;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Locales;

/**
 * Filter for changing languages of the Web-Service.
 * 
 * @author Hasan Yosin
 * 
 */
public class LanguageFilter implements Filter {
	/**
	 * Logger.
	 */
	private static final Logger LOG = 
			Logger.getLogger(EncodingFilter.class);
	/**
	 * Properties file.
	 */
	public static final String PROPERTIES_FILE =
			"ua.nure.yosin.SummaryTask4.properties/lang";

	/**
	 *
	 * @param request
	 *            The servlet request we are processing
	 * @param response
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 *
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	@Override
	public final void doFilter(final ServletRequest request, 
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		LOG.debug("Filter starts");

		HttpServletRequest req = (HttpServletRequest) request;
		LOG.trace("Request uri --> " + req.getRequestURI());
		String locale = request.getParameter("locale");
		if (locale == null) {
			locale = "en";
		}
		LOG.trace("Request locale --> " + locale);

		LOG.trace("Change language to: " + locale);
		ResourceBundle rb = 
		ResourceBundle.getBundle(PROPERTIES_FILE, Locales.getLocale(locale));
		LOG.trace("Set attribute: lang ");
		// Set wanted language to the session
		req.getSession().setAttribute("lang", rb);
		LOG.trace("Set attribute: locale ");
		req.getSession().setAttribute("locale", locale);
		LOG.debug("Filter finished");		

		// redirect to the previous page
		((HttpServletResponse) response).sendRedirect(req.getHeader("referer"));
	}

	@Override
	public final void init(final FilterConfig filterConfig) 
			throws ServletException {
		LOG.debug("Filter initialization starts");
		LOG.debug("Filter initialization finished");
	}

	@Override
	public final void destroy() {
		LOG.debug("Filter destruction starts");
		// no op
		LOG.debug("Filter destruction finished");
	}

}
