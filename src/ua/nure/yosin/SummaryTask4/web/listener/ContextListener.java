package ua.nure.yosin.SummaryTask4.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Context listener.
 * 
 * @author Hasan Yosin
 * 
 */
public class ContextListener implements ServletContextListener {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	@Override
	public final void contextDestroyed(final ServletContextEvent event) {
		log("Servlet context destruction starts");
		// no op
		log("Servlet context destruction finished");
	}

	@Override
	public final void contextInitialized(final ServletContextEvent event) {
		log("Servlet context initialization starts");

		ServletContext servletContext = event.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();

		log("Servlet context initialization finished");
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 *            servlet context.
	 */
	private void initLog4J(final ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			String homeDir = servletContext.getRealPath("/");
			File propertiesFile = new File(homeDir, "WEB-INF/log4j.properties");
			PropertyConfigurator.configure(propertiesFile.toString());
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j" + ex);
		}
		log("Log4J initialization finished");
	}

	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initCommandContainer() {

		// initialize commands container
		// just load class to JVM
		try {
			Class.forName(
				"ua.nure.yosin.SummaryTask4.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException(
					"Cannot initialize Command Container");
		}
	}
	/**
	 * Log.
	 * @param msg messge
	 */
	private void log(final String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}