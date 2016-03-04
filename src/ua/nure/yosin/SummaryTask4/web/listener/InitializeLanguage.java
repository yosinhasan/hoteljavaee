package ua.nure.yosin.SummaryTask4.web.listener;

import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Language Initializer.
 * 
 * @author Hasan Yosin
 */
public class InitializeLanguage implements ServletContextListener {
	/**
	 * Properties file.
	 */
	public static final String PROPERTIES_FILE = 
			"ua.nure.yosin.SummaryTask4.properties/lang";

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
		sce.getServletContext().setAttribute("lang", rb);
	}

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
	}
}