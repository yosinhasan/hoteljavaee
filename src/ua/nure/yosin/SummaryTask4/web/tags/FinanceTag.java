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
 * Tag for gaining amount of finance.
 * 
 * @author Hasan Yosin
 * 
 */
public class FinanceTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(FinanceTag.class);

	@Override
	public final int doStartTag() throws JspException {
		LOG.trace("FinanceTag starts");
		JspWriter out = pageContext.getOut();
		HttpSession session = (HttpSession) pageContext.getSession();
		try {
			DBManagerMysql manager = DBManagerMysql.getInstance();
			User user = (User) session.getAttribute("user");
			LOG.trace(user);
			if (user.getIdrole() == Role.MANAGER.ordinal() + 1) {
				Integer count = manager.getAmountOfPaidFin();
				LOG.trace("Found in db : amount of paid finance " + count);
				if (count > 0) {
					out.write(count.toString());
				} else {
					out.write("0");
				}

			} else if (user.getIdrole() == Role.CLIENT.ordinal() + 1) {
				Integer count = manager.getAmountOfUnpaidFin(user.getId());
				if (count > 0) {
					out.write(count.toString());
				} else {
					out.write("0");
				}
			}

		} catch (Exception e) {
			LOG.error("Exception in finance tag: " + e.getMessage());
		}

		LOG.trace("FinanceTag finished");
		return SKIP_BODY;
	}
}
