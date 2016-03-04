package ua.nure.yosin.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * 
 * @author Hasan Yosin
 * 
 */
public final class CommandContainer {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	/**
	 * Commands.
	 */
	private static Map<String, Command> commands = 
			new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("index", new IndexCommand());
		commands.put("reservation", new ReservationCommand());
		commands.put("details", new DetailsCommand());
		commands.put("activityDetails", new ActivityDetailsCommand());
		commands.put("rooms", new RoomsCommand());
		commands.put("activities", new ActivitiesCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("signup", new SignupCommand());
		commands.put("process", new ProcessCommand());
		commands.put("admin", new AdminCommand());
		commands.put("client", new ClientCommand());
		commands.put("manager", new ManagerCommand());
		commands.put("settings", new SettingsCommand());
		commands.put("bookingHistory", new BookingHistoryCommand());
		commands.put("requests", new RequestsCommand());
		commands.put("responses", new ResponsesCommand());
		commands.put("updatestatus", new ReservationUpdateCommand());
		commands.put("finance", new FinanceCommand());
		commands.put("noCommand", new NoCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(final String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

	/**
	 * Constructor.
	 */
	private CommandContainer() {

	}

}