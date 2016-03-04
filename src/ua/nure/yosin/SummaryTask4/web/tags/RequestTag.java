package ua.nure.yosin.SummaryTask4.web.tags;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.db.Role;
import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * Tag for gaining amount of unprocessed requests.
 * 
 * @author Hasan Yosin
 * 
 */
public class RequestTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * Logger. 
	 */
	private static final Logger LOG = Logger.getLogger(RequestTag.class);

	@Override
	public final int doStartTag() throws JspException {
		LOG.trace("RequestTag starts");
		JspWriter out = pageContext.getOut();
		HttpSession session = (HttpSession) pageContext.getSession();
		try {
			User user = (User) session.getAttribute("user");
			LOG.trace(user);
			if (user.getIdrole() == Role.MANAGER.ordinal() + 1) {
				DBManagerMysql manager = DBManagerMysql.getInstance();
				Integer count = manager.getAmountOfUnprocReq();
				LOG.trace("Found in db : amount of unprocessed requests "
						+ count);
				if (count > 0) {
					out.write(count.toString());
				}

			}

		} catch (Exception e) {
			LOG.error("Exception in request tag: " + e.getMessage());
		}

		LOG.trace("RequestTag finished");
		return SKIP_BODY;
	}
}
