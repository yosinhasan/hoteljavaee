package ua.nure.yosin.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author Hasan Yosin
 * 
 */
public final class Fields {

	/**
	 * Entity id.
	 */
	public static final String ENTITY_ID = "id";
	/**
	 * Room type name.
	 */
	public static final String ROOMTYPES_NAME = "name";
	/**
	 * Room type description.
	 */
	public static final String ROOMTYPES_DESCRIPTION = "description";
	/**
	 * Room type price.
	 */
	public static final String ROOMTYPES_PRICE = "price";
	/**
	 * Room type image.
	 */
	public static final String IMAGE = "image";
	/**
	 * Room type max.
	 */
	public static final String ROOMTYPES_MAX = "max";

	/**
	 * Room album id.
	 */
	public static final String ROOMALBUM_ROOMID = "roomId";
	/**
	 * Room album bean id.
	 */
	public static final String ROOMALBUMBEAN_ID = "idalbum";
	/**
	 * Room album bean image.
	 */
	public static final String ROOMALBUMBEAN_IMAGE = "photos";
	/**
	 * Room album bean room id.
	 */
	public static final String ROOMALBUMBEAN_ROOMID = "roomId";

	/**
	 * Activities name.
	 */
	public static final String ACTIVITIES_NAME = "name";
	/**
	 * Activities description.
	 */
	public static final String ACTIVITIES_DESCRIPTION = "description";
	/**
	 * Activities album id.
	 */
	public static final String ACTIVITIESALBUM_ACTIVITIES_ID = "activitiesId";
	/**
	 * Activities album bean id.
	 */
	public static final String ACTIVITIESALBUMBEAN_ID = "idalbum";
	/**
	 * Activities album bean image.
	 */
	public static final String ACTIVITIESALBUMBEAN_IMAGE = "photos";

	/**
	 * User first name.
	 */
	public static final String USER_FIRST_NAME = "name";
	/**
	 * User last name.
	 */
	public static final String USER_LAST_NAME = "surname";
	/**
	 * User date.
	 */
	public static final String USER_REG_DATE = "regDate";
	/**
	 * User email.
	 */
	public static final String USER_EMAIL = "email";
	/**
	 * User password.
	 */
	public static final String USER_PASSWORD = "password";
	/**
	 * User role id.
	 */
	public static final String USER_ROLE_ID = "idrole";
	/**
	 * User phone.
	 */
	public static final String USER_PHONE = "phone";
	/**
	 * Rooms number.
	 */
	public static final String ROOMS_NUMBER = "number";
	/**
	 * Rooms type id.
	 */
	public static final String ROOMS_ROOMTYPESID = "roomTypesId";
	/**
	 * Reservation room id.
	 */
	public static final String RESERVATION_ROOMID = "roomId";
	/**
	 * Reservation check in.
	 */
	public static final String RESERVATION_CHECKIN = "checkIn";
	/**
	 * Reservation check out.
	 */
	public static final String RESERVATION_CHECKOUT = "checkOut";
	/**
	 * Reservation user id.
	 */
	public static final String RESERVATION_USERID = "userId";
	/**
	 * Reservation status id.
	 */
	public static final String RESERVATION_STATUSID = "statusId";
	/**
	 * Reservation reservation date.
	 */
	public static final String RESERVATION_RESERVATIONDATE = "reservationDate";
	/**
	 * Requests amount.
	 */
	public static final String REQUESTS_AMOUNT = "amount";

	/**
	 * Requests room types id.
	 */
	public static final String REQUESTS_ROOMTYPESID = "roomTypesId";
	/**
	 * Requests check in.
	 */
	public static final String REQUESTS_CHECKIN = "checkIn";
	/**
	 * Requests check out.
	 */
	public static final String REQUESTS_CHECKOUT = "checkOut";
	/**
	 * Requests user id.
	 */
	public static final String REQUESTS_USERID = "userId";
	/**
	 * Requests request status id.
	 */
	public static final String REQUESTS_REQUESTSTATUSID = "requestStatusId";
	/**
	 * Requests date.
	 */
	public static final String REQUESTS_DATE = "date";
	/**
	 * Requests bean user name.
	 */
	public static final String REQUESTSBEAN_USERNAME = "firstName";
	/**
	 * Requests bean user surname.
	 */
	public static final String REQUESTSBEAN_USERSURNAME = "secondName";

	/**
	 * Booking history user surname.
	 */
	public static final String BOOKINGHISTORY_USERNAME = "firstName";
	/**
	 * Booking history user surname.
	 */
	public static final String BOOKINGHISTORY_USERSURNAME = "secondName";

	/**
	 * Responses to user id.
	 */
	public static final String RESPONSES_TOUSERID = "toUserId";
	/**
	 * Responses reservation id.
	 */
	public static final String RESPONSES_RESERVATION_ID = "reservationId";
	/**
	 * Responses message.
	 */
	public static final String RESPONSES_MSG = "msg";
	/**
	 * Responses status id.
	 */
	public static final String RESPONSES_STATUS_ID = "statusId";
	/**
	 * Finance reservation id.
	 */
	public static final String FINANCE_RESERVATIONID = "reservationId";
	/**
	 * Bill.
	 */
	public static final String FINANCE_BILL = "bill";
	/**
	 * Finance status id.
	 */
	public static final String FINANCE_FINANCESTATUSID = "financeStatusId";
	/**
	 * User id.
	 */
	public static final String FINANCE_USERID = "userId";

	/**
	 * Username.
	 */
	public static final String FINANCEBEAN_USERNAME = "username";
	/**
	 * Constructor.
	 */
	private Fields() {

	}
}