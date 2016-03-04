package ua.nure.yosin.SummaryTask4.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import ua.nure.yosin.SummaryTask4.Path;

/**
 * Index filter.
 * @author Hasan Yosin
 * 
 */
public class IndexPageFilter implements Filter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public final void doFilter(final ServletRequest request, 
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		((HttpServletResponse) response).sendRedirect(
				Path.COMMAND_INDEX);
	}

	@Override
	public void destroy() {
	}

}
