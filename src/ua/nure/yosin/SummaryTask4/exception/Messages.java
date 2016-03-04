package ua.nure.yosin.SummaryTask4.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Hasan Yosin
 *
 */
public final class Messages {
	/**
	 * Private constructor.
	 */
	private Messages() {

	}

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMTYPES = "Cannot obtain room types";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ID = "Cannot obtain room types by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ROOMID = "Cannot obtain room types by room id";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_ROOMTYPES = "Cannot update a room type";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ID = "Cannot obtain room album by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ROOMID = "Cannot obtain room album by room id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMALBUM = "Cannot obtain room album";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_ROOMALBUM = "Cannot update a room album";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMALBUMBEAN_BY_ID = "Cannot obtain room album bean by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMBEAN_BY_ROOMTYPESID = "Cannot obtain room bean by room types id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ROOMBEAN = "Cannot obtain room bean by room";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ACTIVITIES = "Cannot obtain activities";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ACTIVITIES_BY_ID = "Cannot obtain activities by id";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_ACTIVITIES = "Cannot update activities";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ACTIVITIESALBUM = "Cannot obtain activities album";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_ACTIVITIESALBUM = "Cannot update activities album";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_ACTIVITIESALBUMBEAN_BY_ID = "Cannot obtain activities album bean by id";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_FINANCE_BY_ID = "Cannot obtain a finance by its id";
	
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert new user";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_INSERT_RESERVATION = "Cannot insert new reservation";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_INSERT_FINANCE = "Cannot insert new finance";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESERVATION_BY_USERID = "Cannot obtain reservations by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESERVATION_BY_ID = "Cannot obtain reservations by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_RESERVATION_STATUS_OUT_OF_DATE = "Cannot update status of out of date reservations";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESERVATIONS = "Cannot obtain reservations";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_RESERVATIONSTATUS_BY_ID = "Cannot update reservation status by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_FINANCESTATUS_BY_ID = "Cannot update finance status by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_INSERT_REQUESTS = "Cannot insert new request";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_REQUESTS_BY_USERID = "Cannot obtain requests by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_REQUESTS = "Cannot obtain requests";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_COUNT_UNPROCESSED_REQUESTS = "Cannot count amount of unprocessed requests";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_COUNT_UNPAID_FINANCE = "Cannot count amount of unpaid finance";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_COUNT_PAID_FINANCE = "Cannot count amount of paid finance";
	
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_REQUESTS = "Cannot update requests by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_DELETE_REQUESTS_BY_ID = "Cannot delete request by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_DELETE_RESERVATION_BY_ID = "Cannot delete reservation by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_DELETE_FINANCE_BY_ID = "Cannot delete finace by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_DELETE_REQUEST_BY_ID = "Cannot delete request by id";
	/**
	 * Message.
	 */
	public static final String ERR_PARSE_EXCEPTION = "Cannot parse date";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_INSERT_RESPONSES = "Cannot insert new response";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_DELETE_RESERVATION_RESPONSE_BY_RESPONSEID = "Cannot delete reservation and response raw by id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_COUNT_NEW_RESPONSES = "Cannot count amount of new responses";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_COUNT_NEW_RESPONSES_BY_USERID = "Cannot count amount of new responses by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_UPDATE_RESPONSE_BY_ID = "Cannot update response by id";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESPONSES = "Cannot obtain responses";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_UNPAID_FINANCES_BY_USERID = "Cannot obtain unpaid finances by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_PAID_FINANCES= "Cannot obtain paid finances";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_PAID_FINANCES_BY_USERID = "Cannot obtain paid finances by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_FINANCES_BY_USERID = "Cannot obtain finances by user id";

	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESPONSES_BY_USERID = "Cannot obtain responses by user id";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESPONSEBEAN = "Cannot obtain response bean";
	/**
	 * Message.
	 */
	public static final String ERR_CANNOT_OBTAIN_RESPONSEBEAN_BY_ID = "Cannot obtain response bean by id";
}