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
 * Tag for gaining new responses of respective user.
 * 
 * @author Hasan Yosin
 * 
 */
public class ResponseTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = 
			Logger.getLogger(ResponseTag.class);

	@Override
	public final int doStartTag() throws JspException {
		LOG.trace("ResponseTag starts");
		JspWriter out = pageContext.getOut();
		HttpSession session = (HttpSession) pageContext.getSession();
		try {
			User user = (User) session.getAttribute("user");
			LOG.trace(user);
			if (user.getIdrole() == Role.CLIENT.ordinal() + 1) {
				DBManagerMysql manager = DBManagerMysql.getInstance();
				Integer count =
						manager.getAmountOfNewRespForUserId(user.getId());
				LOG.trace("Found in db : amount of new responses " + count);
				if (count > 0) {
					out.write(count.toString());
				} else {
					out.write("0");
				}

			}

		} catch (Exception e) {
			LOG.error("Exception in response tag: " + e.getMessage());
		}

		LOG.trace("ResponseTag finished");
		return SKIP_BODY;
	}
}
