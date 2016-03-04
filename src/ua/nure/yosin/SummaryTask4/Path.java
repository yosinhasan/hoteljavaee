package ua.nure.yosin.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author Hasan Yosin
 * 
 */
public final class Path {
	/**
	 * Hotel main page.
	 */
	public static final String PAGE_HOTEL = "/WEB-INF/jsp/index.jsp";
	/**
	 * Redirect page.
	 */
	public static final String PAGE_REDIRECT = "/WEB-INF/jsp/redirect.jsp";
	/**
	 * Activities page.
	 */
	public static final String PAGE_ACTIVITIES = "/WEB-INF/jsp/activities.jsp";
	/**
	 * Details page.
	 */
	public static final String PAGE_DETAILS = "/WEB-INF/jsp/details.jsp";
	/**
	 * Reservation page.
	 */
	public static final String PAGE_RESERVATION = "/WEB-INF/jsp/reservation.jsp";
	/**
	 * Rooms page.
	 */
	public static final String PAGE_ROOMS = "/WEB-INF/jsp/rooms.jsp";
	/**
	 * Activity details page.
	 */
	public static final String PAGE_ACTIVITY_DETAILS = "/WEB-INF/jsp/activityDetails.jsp";
	/**
	 * Success page.
	 */
	public static final String PAGE_SUCCESS = "/WEB-INF/jsp/success.jsp";
	/**
	 * Admin page.
	 */

	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/index.jsp";
	/**
	 * Manager page.
	 */
	public static final String PAGE_MANAGER = "/WEB-INF/jsp/manager/index.jsp";
	/**
	 * Client page.
	 */
	public static final String PAGE_CLIENT = "/WEB-INF/jsp/client/index.jsp";
	/**
	 * Client finance page.
	 */
	public static final String PAGE_CLIENT_FINANCE = "WEB-INF/jsp/client/finance.jsp";
	/**
	 * Manager finance page.
	 */
	public static final String PAGE_MANAGER_FINANCE = "WEB-INF/jsp/manager/finance.jsp";
	
	/**
	 * Login page.
	 */
	public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
	/**
	 * Sign up page.
	 */
	public static final String PAGE_SIGNUP = "/WEB-INF/jsp/signup.jsp";
	/**
	 * Error page.
	 */
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	/**
	 * Client settings page.
	 */
	public static final String PAGE_CLIENT_SETTINGS = "/WEB-INF/jsp/client/settings.jsp";
	/**
	 * Manager settings page.
	 */
	public static final String PAGE_MANAGER_SETTINGS = "/WEB-INF/jsp/manager/settings.jsp";
	/**
	 * Booking history for client page.
	 */
	public static final String PAGE_CLIENT_BOOKINGHISTORY = "/WEB-INF/jsp/client/reservation.jsp";
	/**
	 * Booking history for manager page.
	 */
	public static final String PAGE_MANAGER_BOOKINGHISTORY = "/WEB-INF/jsp/manager/reservation.jsp";
	/**
	 * Client responses page.
	 */
	public static final String PAGE_CLIENT_RESPONSES = "WEB-INF/jsp/client/responses.jsp";
	/**
	 * Manager responses page.
	 */
	public static final String PAGE_MANAGER_RESPONSES = "WEB-INF/jsp/manager/responses.jsp";
	/**
	 * Client requests.
	 */
	public static final String PAGE_CLIENT_REQUESTS = "WEB-INF/jsp/client/requests.jsp";
	/**
	 * Manager requests.
	 */
	public static final String PAGE_MANAGER_REQUESTS = "WEB-INF/jsp/manager/requests.jsp";

	/**
	 * Admin command.
	 */
	public static final String COMMAND_ADMIN = "controller?command=admin";
	/**
	 * Manager command.
	 */
	public static final String COMMAND_MANAGER = "controller?command=manager";
	/**
	 * Client command.
	 */
	public static final String COMMAND_CLIENT = "controller?command=client";
	/**
	 * Reservation command.
	 */
	public static final String COMMAND_RESERVATION = "controller?command=reservation";
	/**
	 * Index command.
	 */
	public static final String COMMAND_INDEX = "controller?command=index";
	/**
	 * Requests command.
	 */
	public static final String COMMAND_REQUESTS = "controller?command=requests";
	/**
	 * Responses command.
	 */
	public static final String COMMAND_RESPONSES = "controller?command=responses";
	/**
	 * Finance command.
	 */
	public static final String COMMAND_FINANCE = "controller?command=finance";
	/**
	 * Settings command.
	 */
	public static final String COMMAND_SETTINGS = "controller?command=settings";
	
	/**
	 * Private constructor.
	 */
	private Path() {

	}
}