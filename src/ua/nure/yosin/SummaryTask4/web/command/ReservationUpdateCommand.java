package ua.nure.yosin.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.Path;
import ua.nure.yosin.SummaryTask4.db.DBManagerMysql;
import ua.nure.yosin.SummaryTask4.exception.AppException;

/**
 * Reservation update command.
 * 
 * @author Hasan Yosin
 * 
 */
public class ReservationUpdateCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = 
			Logger.getLogger(ReservationUpdateCommand.class);

	@Override
	public final String execute(final HttpServletRequest request, 
			final HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");
		DBManagerMysql manager = DBManagerMysql.getInstance();
		LOG.trace("Command: update out of date reservation's status");
		manager.updateStatusOfOutResevations();
		LOG.debug("Command finished");
		return Path.COMMAND_INDEX;
	}

}