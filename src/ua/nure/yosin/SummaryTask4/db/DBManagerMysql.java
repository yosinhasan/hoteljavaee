package ua.nure.yosin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.db.bean.ActivitiesAlbumBean;
import ua.nure.yosin.SummaryTask4.db.bean.BookingHistoryBean;
import ua.nure.yosin.SummaryTask4.db.bean.FinanceBean;
import ua.nure.yosin.SummaryTask4.db.bean.RequestsBean;
import ua.nure.yosin.SummaryTask4.db.bean.ResponseBean;
import ua.nure.yosin.SummaryTask4.db.bean.RoomAlbumBean;
import ua.nure.yosin.SummaryTask4.db.bean.RoomBean;
import ua.nure.yosin.SummaryTask4.db.entity.Activities;
import ua.nure.yosin.SummaryTask4.db.entity.ActivitiesAlbum;
import ua.nure.yosin.SummaryTask4.db.entity.Finance;
import ua.nure.yosin.SummaryTask4.db.entity.Requests;
import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.Responses;
import ua.nure.yosin.SummaryTask4.db.entity.RoomAlbum;
import ua.nure.yosin.SummaryTask4.db.entity.RoomTypes;
import ua.nure.yosin.SummaryTask4.db.entity.Rooms;
import ua.nure.yosin.SummaryTask4.db.entity.User;
import ua.nure.yosin.SummaryTask4.exception.DBException;
import ua.nure.yosin.SummaryTask4.exception.Messages;
import ua.nure.yosin.SummaryTask4.utils.DateUtil;

/**
 * DB manager. Works with MySql DB.
 * 
 * @author Hasan Yosin
 * 
 */
public final class DBManagerMysql {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(DBManagerMysql.class);
	/**
	 * singleton.
	 */
	private static DBManagerMysql instance;

	/**
	 * Get instance.
	 * 
	 * @return DBManagerMysql
	 * @throws DBException
	 *             db exception
	 */
	public static synchronized DBManagerMysql getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManagerMysql();
		}
		return instance;
	}

	/**
	 * Private contructor.
	 * 
	 * @throws DBException
	 *             db exception
	 */
	private DBManagerMysql() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// Hotel - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/hotel");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	/**
	 * Data source.
	 */
	private DataSource ds;
	/**
	 * Query.
	 */
	private static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM `user` WHERE `email`=?";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM `user` WHERE `id`=?";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_USER = "UPDATE `user` SET `password`=?, `name`=?, `surname`=?, `phone`=?, `email`=? WHERE `id`=?";
	/**
	 * Query.
	 */
	private static final String SQL_INSERT_USER = "INSERT INTO `user` (`name`, `surname`, "
			+ "`phone`, `regDate`, `email`, `password`, `idrole`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_FIND_ALL_ROOMTYPES = "SELECT * FROM `roomTypes`;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ROOMTYPES_BY_ID = "SELECT * FROM `roomTypes` WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ROOMTYPES_BY_ROOMID = "SELECT rt.* FROM rooms rs"
			+ " INNER JOIN roomTypes rt ON rs.roomTypesId = rt.id WHERE rs.id = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_ROOMTYPES = "UPDATE `roomTypes`"
			+ " SET `name` = ?, `description` = ?, `price` = ?, `image` = ?, `max` = ? WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ROOMALBUM = "SELECT * FROM `roomAlbum`;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ROOMALBUM_BY_ID = "SELECT * FROM `roomAlbum` WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ROOMALBUM_BY_ROOMID = "SELECT * FROM `roomAlbum` WHERE `roomId` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_ROOMALBUM = "UPDATE `roomAlbum` SET `roomId` = ?, `image` = ? WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ACTIVITIES = "SELECT * FROM `activities`;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ACTIVITIES_BY_ID = "SELECT * FROM `activities` WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_ACTIVITIES = "UPDATE `activities` "
			+ " SET `name` = ?, `description` = ?, `image` = ? WHERE `id` = ?";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_ACTIVITIESALBUMBEAN_BY_ID = "SELECT `a`.*, `b`.`id`  as idalbum,"
			+ " `b`.`image` as photos " + " FROM `activities` a INNER JOIN activitiesAlbum `b`"
			+ " ON `a`.`id` = `b`.`activitiesId` WHERE `a`.`id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ROOMALBUMBEAN_BY_ID = "SELECT t.*, r.id as idalbum, r.image as photos, r.roomId  FROM roomTypes t "
			+ " INNER JOIN roomAlbum r  ON t.id = r.roomId WHERE t.id = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_AVAILABLE_ROOM_BY_ROOMTYPESID = " SELECT `r`.*, `rt`.`price`, `rt`.`name`, `rt`.`max` "
			+ " FROM `rooms` `r` INNER JOIN `roomTypes` `rt` ON `rt`.`id` = `r`.`roomTypesId` WHERE  ? > ? AND `rt`.`id` = ? AND `r`.`id` "
			+ " NOT IN (SELECT `roomId` FROM `reservation` `rs` WHERE `rs`.`statusId` IN (2,3,4) AND (`rs`.`checkIn` >= ? AND `rs`.`checkOut` <= ? OR "
			+ " ? >= `rs`.`checkIn`  AND ? <= `rs`.`checkOut`  OR ? > `rs`.`checkIn`  AND ? < `rs`.`checkOut`));";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_AVAILABLE_ROOM = " SELECT `r`.*, `rt`.`price`, `rt`.`name`, `rt`.`max` "
			+ " FROM `rooms` `r` INNER JOIN `roomTypes` `rt` ON `rt`.`id` = `r`.`roomTypesId` WHERE "
			+ " ? > ? AND `r`.`id`  NOT IN (SELECT `roomId` FROM `reservation` `rs` WHERE `rs`.`statusId` IN (2,3,4) AND "
			+ "	(`rs`.`checkIn` >= ? AND `rs`.`checkOut` <= ? OR " + " ? >= `rs`.`checkIn`  AND ? <= `rs`.`checkOut` "
			+ " OR ? > `rs`.`checkIn`  AND ? < `rs`.`checkOut`)) ORDER BY `r`.`roomTypesId`, `r`.`number`;";
	/**
	 * Query.
	 */
	public static final String SQL_INSERT_RESERVATION = "INSERT INTO `reservation`"
			+ " (`roomId`, `checkIn`, `checkOut`, `userId`, `statusId`, `reservationDate`)"
			+ " VALUES (?, ?, ?, ?, ?, ?);";
	/**
	 * Query.
	 */
	public static final String SQL_INSERT_REQUESTS = "INSERT INTO `requests`"
			+ "(`amount`, `roomTypesId`, `checkIn`, `checkOut`, `userId`, `requestStatusId`,"
			+ " `date`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_REQUESTS_BY_USERID = "SELECT * FROM `requests` WHERE `userId` = ? ORDER BY `requestStatusId`;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_REQUESTS = "SELECT `u`.`name` as firstName, `u`.`surname` as secondName, `r`.* FROM `requests` `r` "
			+ "INNER JOIN `user` `u` ON `r`.`userId` = `u`.`id` ORDER BY `r`.`requestStatusId`, `r`.`date` desc;";
	/**
	 * Query.
	 */
	private static final String SQL_COUNT_UNPROCESSED_REQUESTS = "SELECT COUNT(`id`) as amount FROM `requests` WHERE `requestStatusId` = 1";
	/**
	 * Query.
	 */
	private static final String SQL_COUNT_UNPAID_FINANCE = "SELECT COUNT(`id`) as amount FROM `finance` WHERE `financeStatusId` = 2 AND `userId` = ?";
	/**
	 * Query.
	 */
	private static final String SQL_COUNT_PAID_FINANCE = "SELECT COUNT(`id`) as amount FROM `finance` WHERE `financeStatusId` = 1";

	/**
	 * Query.
	 */
	private static final String SQL_COUNT_NEW_RESPONSES = "SELECT COUNT(`id`) FROM responses WHERE statusId = 1;";
	/**
	 * Query.
	 */
	private static final String SQL_COUNT_NEW_RESPONSES_BY_USERID = "SELECT COUNT(`id`) FROM `responses` WHERE `statusId` = 1 AND `toUserId` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_RESERVATIONS_BY_USERID = "SELECT `r`.`id`, `r`.`checkIn`, `r`.`checkOut`, `r`.`reservationDate`, `r`.`userId`,"
			+ " `u`.`name` as firstName, `u`.`surname` as secondName, `rt`.`name`, `ro`.`number`, `r`.`statusId` FROM `reservation` `r` "
			+ "INNER JOIN `rooms` `ro` ON `r`.`roomId` = `ro`.`id` INNER JOIN `roomTypes` `rt` ON `ro`.`roomTypesId` = `rt`.`id` "
			+ "INNER JOIN `user` `u` ON `u`.`id` = `r`.`userId` WHERE `r`.`userId`=? AND `r`.`statusId` NOT IN(1,2)  ORDER BY `r`.`reservationDate` DESC;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_RESERVATIONS = "SELECT `r`.`id`, `r`.`checkIn`, `r`.`checkOut`, `r`.`reservationDate`, `r`.`userId`,"
			+ " `u`.`name` as firstName, `u`.`surname` as secondName, `rt`.`name`, `ro`.`number`, `r`.`statusId` FROM `reservation` `r` "
			+ "INNER JOIN `rooms` `ro` ON `r`.`roomId` = `ro`.`id` INNER JOIN `roomTypes` `rt` ON `ro`.`roomTypesId` = `rt`.`id` "
			+ "INNER JOIN `user` `u` ON `u`.`id` = `r`.`userId` AND `r`.`statusId` NOT IN(1,2)  ORDER BY `r`.`reservationDate` DESC;";
	/**
	 * Query.
	 */
	private static final String SQL_DELETE_RESERVATION_BY_ID = "DELETE FROM `reservation` WHERE id = ?;";

	/**
	 * Query.
	 */
	private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM `requests` WHERE `id` = ? AND `userId` = ?;";

	/**
	 * Query.
	 */
	private static final String SQL_DELETE_FINANCE_BY_ID = "DELETE FROM `finance` WHERE id = ?;";

	/**
	 * Query.
	 */
	private static final String SQL_INSERT_RESPONSES = "INSERT INTO `responses` (`toUserId`, `reservationId`, `msg`, `statusId`) VALUES (?, ?, ?, ?);";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_REQUESTS_STATUS_BY_ID = "UPDATE `requests` SET `requestStatusId` = ? WHERE `id` = ?;";
	/**
	 * Query.
	 */
	private static final String SQL_DELETE_RESERVATION_RESPONSE_BY_RESPONSEID = "DELETE FROM r, rr USING responses r "
			+ " INNER JOIN reservation rr WHERE  r.reservationId = rr.id AND r.id=?;";
	/**
	 * Query.
	 */
	private static final String SQL_UPDATE_RESPONSE_BY_ID = "UPDATE `responses` SET `statusId` = ? WHERE `id` = ?";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_RESPONSES = "SELECT * FROM `responses`";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_RESPONSES_BY_USERID = "SELECT * FROM `responses` WHERE `toUserId` = ?";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_RESPONSEBEAN = "SELECT ((rr.checkOut - rr.checkIn) * rt.price) as price, rt.name, rt.max, rm.number, r.*, rr.checkIn, rr.checkOut FROM responses r"
			+ " INNER JOIN reservation rr ON r.reservationId = rr.id INNER JOIN rooms rm ON rr.roomId = rm.id INNER JOIN roomTypes rt ON rt.id = rm.roomTypesId"
			+ " WHERE `statusId` = 1;";
	/**
	 * Query.
	 */
	private static final String SQL_FIND_ALL_RESPONSEBEAN_BY_ID = "SELECT ((`rr`.`checkOut` - `rr`.`checkIn`) * `rt`.`price`) as price, `rt`.`name`, `rt`.`max`, "
			+ " `rm`.`number`, `r`.*, `rr`.`checkIn`, `rr`.`checkOut` FROM `responses` `r` INNER JOIN `reservation` `rr` ON `r`.`reservationId` = `rr`.`id` "
			+ " INNER JOIN `rooms` `rm` ON `rr`.`roomId` = `rm`.`id` INNER JOIN `roomTypes` `rt` ON `rt`.`id` = `rm`.`roomTypesId`"
			+ " WHERE `r`.`toUserId` = ? AND `r`.`statusId` = 1";
	/**
	 * Query.
	 */
	public static final String SQL_UPDATE_RESERVATION_STATUS_BY_ID = "UPDATE `reservation` SET `statusId` = ? WHERE `id` = ? ;";

	/**
	 * Query.
	 */
	public static final String SQL_UPDATE_FINANCE_STATUS_BY_ID = "UPDATE `finance` SET `financeStatusId` = ? WHERE `id` = ? AND `userId` = ?;";

	/**
	 * Query.
	 */
	public static final String SQL_FIND_RESERVATION_BY_ID = "SELECT * FROM `reservation` WHERE `id` = ?;";
	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_OUT_OF_DATE_RESERVATION = "SELECT  `id` FROM `reservation` "
			+ "WHERE TIMESTAMPDIFF(HOUR,`reservationDate`, NOW()) >= 48";
	/**
	 * Query.
	 */
	public static final String SQL_UPDATE_STATUS_OF_OUT_OF_DATE_RESERVATION = "UPDATE `hotel`.`reservation` "
			+ " SET `statusId` = 1 	WHERE `id` 	IN (SELECT `id` FROM (SELECT  `id` 	FROM `reservation` "
			+ " WHERE TIMESTAMPDIFF(HOUR,`reservationDate`, NOW()) >= 48 AND `statusId` = 3)" + " as result);";
	/**
	 * Query.
	 */
	public static final String SQL_INSERT_FINANCE = "INSERT INTO `finance` ("
			+ " `reservationId`, `bill`, `financeStatusId`, `userId`)" + "  VALUES(?, ?, ?, ?);";
	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_FINANCES = "SELECT f.*, r.checkIn, r.checkOut, rt.name, rs.number, CONCAT(u.name, ' ', u.surname)"
			+ " as username FROM finance f"
			+ " INNER JOIN  reservation r ON f.reservationId = r.id  INNER JOIN rooms rs ON rs.id = r.roomId "
			+ " INNER JOIN roomTypes rt ON rs.roomTypesId = rt.id INNER JOIN user u ON u.id = f.userId ";

	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_FINANCE_BY_USERID_AND_UNPAID = SQL_FIND_ALL_FINANCES
			+ " WHERE f.userId = ? AND financeStatusId = 2;";
	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_FINANCE_BY_USERID_AND_PAID = SQL_FIND_ALL_FINANCES
			+ " WHERE f.userId = ? AND financeStatusId = 1;";
	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_FINANCE_BY_USERID = SQL_FIND_ALL_FINANCES + " WHERE f.userId = ?;";

	/**
	 * Query.
	 */
	public static final String SQL_FIND_ALL_PAID_FINANCE = SQL_FIND_ALL_FINANCES + " WHERE financeStatusId = 1;";

	/**
	 * Query.
	 */
	public static final String SQL_FIND_FINANCE_BY_ID = "SELECT * FROM `finance` WHERE `id` = ? AND `userId` = ?";
	/**
	 * Query.
	 */
	public static final String SQL_FIND_INFO = "SELECT COUNT(r.statusId) as amount, SUM((r.checkOut - r.checkIn) * rt.price) as totalSum "
			+ " FROM reservation r INNER JOIN rooms rm 	ON rm.id=r.roomId INNER	JOIN roomTypes"
			+ "	rt ON rt.id=rm.roomTypesId WHERE r.statusId=3;";

	/**
	 * Returns a DB connection from the Pool Connections. *
	 * 
	 * @return DB connection.
	 * @throws DBException
	 *             db exception
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	/**
	 * Add new user.
	 * 
	 * @param user
	 *            user to add
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer addUser(final User user) throws DBException {
		Integer cond = -1;
		if (user.getId() != null && user.getId() > 0) {
			return 0;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getSurname());
			pstmt.setString(k++, user.getPhone());
			pstmt.setString(k++, user.getRegDate());
			pstmt.setString(k++, user.getEmail());
			pstmt.setString(k++, user.getPassword());
			pstmt.setInt(k, user.getIdrole());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cond = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return cond;
	}

	/**
	 * Add new reservation.
	 * 
	 * @param reservation
	 *            reservation to add
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer addReservation(final Reservation reservation) throws DBException {
		Integer cond = -1;
		if (reservation.getId() != null && reservation.getId() > 0) {
			return 0;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_RESERVATION, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setLong(k++, reservation.getRoomId());
			pstmt.setString(k++, reservation.getCheckIn());
			pstmt.setString(k++, reservation.getCheckOut());
			pstmt.setLong(k++, reservation.getUserId());
			pstmt.setInt(k++, reservation.getStatusId());
			pstmt.setString(k, reservation.getReservationDate());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cond = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_RESERVATION, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_RESERVATION, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return cond;
	}

	/**
	 * Add new response.
	 * 
	 * @param response
	 *            response to add
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer addResponse(final Responses response) throws DBException {
		Integer cond = -1;
		if (response.getId() != null && response.getId() > 0) {
			return 0;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_RESPONSES, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setLong(k++, response.getToUserId());
			pstmt.setInt(k++, response.getReservationId());
			pstmt.setString(k++, response.getMsg());
			pstmt.setLong(k++, response.getStatus().ordinal() + 1);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cond = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_RESPONSES, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_RESPONSES, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return cond;
	}

	/**
	 * Add new request.
	 * 
	 * @param request
	 *            request to add
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer addRequest(final Requests request) throws DBException {
		Integer cond = -1;
		if (request.getId() != null && request.getId() > 0) {
			return 0;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_REQUESTS, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setInt(k++, request.getAmount());
			pstmt.setInt(k++, request.getRoomTypesId());
			pstmt.setString(k++, request.getCheckIn());
			pstmt.setString(k++, request.getCheckOut());
			pstmt.setLong(k++, request.getUserId());
			pstmt.setInt(k++, request.getRequestStatusId());
			pstmt.setString(k, request.getDate());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cond = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_REQUESTS, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_REQUESTS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return cond;
	}

	/**
	 * Add new finance.
	 * 
	 * @param finance
	 *            finance to add
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer addFinance(final Finance finance) throws DBException {
		Integer cond = -1;
		if (finance.getId() != null && finance.getId() > 0) {
			return 0;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_FINANCE, PreparedStatement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setInt(k++, finance.getReservationId());
			pstmt.setLong(k++, finance.getBill());
			pstmt.setInt(k++, finance.getStatus().ordinal() + 1);
			pstmt.setLong(k, finance.getUserId());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cond = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_INSERT_FINANCE, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_FINANCE, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return cond;
	}

	/**
	 * Returns a user with the given identifier.
	 * 
	 * @param id
	 *            User identifier
	 * @return User
	 * @throws DBException
	 *             db exception
	 */
	public User findUser(final long id) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Returns a finance with the given identifier.
	 * 
	 * @param id
	 *            Finance identifier
	 * @param userId
	 *            user identifier
	 * @return Finance
	 * @throws DBException
	 *             db exception
	 */
	public Finance findFinance(final long id, final long userId) throws DBException {
		Finance finance = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_FINANCE_BY_ID);
			pstmt.setLong(1, id);
			pstmt.setLong(2, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				finance = extractFinance(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_FINANCE_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FINANCE_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return finance;
	}

	/**
	 * Returns a user with the given email.
	 * 
	 * @param email
	 *            User email.
	 * @return User
	 * @throws DBException
	 *             db exception
	 */
	public User findUserByEmail(final String email) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}

	/**
	 * Returns all room types.
	 * 
	 * @return List<RoomTypes>
	 * @throws DBException
	 *             db exception
	 */
	public List<RoomTypes> getAllRoomTypes() throws DBException {
		List<RoomTypes> roomTypes = new ArrayList<RoomTypes>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ROOMTYPES);
			while (rs.next()) {
				roomTypes.add(extractRoomTypes(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return roomTypes;
	}

	/**
	 * Returns all response bean.
	 * 
	 * @return List<ResponseBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<ResponseBean> getAllResponseBean() throws DBException {
		List<ResponseBean> response = new ArrayList<ResponseBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_RESPONSEBEAN);
			while (rs.next()) {
				response.add(extractResponseBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESPONSEBEAN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESPONSEBEAN, ex);
		} finally {
			close(con, stmt, rs);
		}
		return response;
	}

	/**
	 * Returns all response bean by user id.
	 * 
	 * @param userId
	 *            user id
	 * @return List<ResponseBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<ResponseBean> getAllResponseBean(final Long userId) throws DBException {
		if (userId == null || userId <= 0) {
			return null;
		}
		List<ResponseBean> response = new ArrayList<ResponseBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_RESPONSEBEAN_BY_ID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				response.add(extractResponseBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESPONSEBEAN_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESPONSEBEAN_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return response;
	}

	/**
	 * Returns all room album.
	 * 
	 * @return List<RoomAlbum>
	 * @throws DBException
	 *             db exception
	 */
	public List<RoomAlbum> getAllRoomAlbum() throws DBException {
		List<RoomAlbum> roomAlbum = new ArrayList<RoomAlbum>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ROOMALBUM);
			while (rs.next()) {
				roomAlbum.add(extractRoomAlbum(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM, ex);
		} finally {
			close(con, stmt, rs);
		}

		return roomAlbum;

	}

	/**
	 * Returns all activities.
	 * 
	 * @return List<Activities>
	 * @throws DBException
	 *             db exception
	 */
	public List<Activities> getAllActivities() throws DBException {
		List<Activities> activities = new ArrayList<Activities>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_ACTIVITIES);
			while (rs.next()) {
				activities.add(extractActivities(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ACTIVITIES, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACTIVITIES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return activities;
	}

	/**
	 * Returns all responses.
	 * 
	 * @return List<Responses>
	 * @throws DBException
	 *             db exception
	 */
	public List<Responses> getAllResponses() throws DBException {
		List<Responses> responses = new ArrayList<Responses>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_RESPONSES);
			while (rs.next()) {
				responses.add(extractResponses(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESPONSES);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESPONSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return responses;
	}
	
	/**
	 * Returns info.
	 * 
	 * @return List<Integer>
	 * @throws DBException
	 *             db exception
	 */
	public List<Integer> getInfo() throws DBException {
		List<Integer> info = new ArrayList<Integer>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_INFO);
			if (rs.next()) {
				info.add(rs.getInt("amount"));
				info.add(rs.getInt("totalSum"));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESPONSES);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESPONSES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return info;
	}
	
	

	/**
	 * Returns all responses by user id.
	 * 
	 * @param id
	 *            respones id
	 * @return List<Responses>
	 * @throws DBException
	 *             db exception
	 */
	public List<Responses> getAllResponsesById(final Long id) throws DBException {
		if (id == null || id <= 0) {
			return null;
		}
		List<Responses> responses = new ArrayList<Responses>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_RESPONSES_BY_USERID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				responses.add(extractResponses(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESPONSES_BY_USERID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESPONSES_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return responses;
	}

	/**
	 * Get room album by id.
	 * 
	 * @param id
	 *            room album id
	 * @return RoomAlbum
	 * @throws DBException
	 *             db exception
	 */
	public RoomAlbum getRoomAlbumById(final Integer id) throws DBException {
		if (id < 0) {
			return null;
		}
		RoomAlbum album = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_ROOMALBUM_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				album = extractRoomAlbum(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.trace(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return album;

	}

	/**
	 * Get reservation by id.
	 * 
	 * @param id
	 *            reservation id
	 * @return Reservation
	 * @throws DBException
	 *             db exception
	 */
	public Reservation getReservationById(final Long id) throws DBException {
		if (id < 0) {
			return null;
		}
		Reservation reservation = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_RESERVATION_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reservation = extractReservation(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESERVATION_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESERVATION_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return reservation;
	}

	/**
	 * Get booking history bean by user id.
	 * 
	 * @param id
	 *            reservation id
	 * @return BookingHistoryBean
	 * @throws DBException
	 *             db exception
	 */
	public List<BookingHistoryBean> getBookingHistoryByUserId(final Long id) throws DBException {
		if (id < 0) {
			return null;
		}
		List<BookingHistoryBean> bean = new ArrayList<BookingHistoryBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_RESERVATIONS_BY_USERID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.add(extractBookingHistory(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESERVATION_BY_USERID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESERVATION_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return bean;

	}

	/**
	 * Get booking history bean.
	 * 
	 * @param id
	 *            reservation id
	 * @return BookingHistoryBean
	 * @throws DBException
	 *             db exception
	 */
	public List<BookingHistoryBean> getBookingHistory() throws DBException {
		List<BookingHistoryBean> bean = new ArrayList<BookingHistoryBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_RESERVATIONS);
			while (rs.next()) {
				bean.add(extractBookingHistory(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(ex.getMessage());
			LOG.error(Messages.ERR_CANNOT_OBTAIN_RESERVATIONS);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_RESERVATIONS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return bean;

	}

	/**
	 * Returns all room album.
	 * 
	 * @param roomId
	 *            room id
	 * @return List of roomAlbum entities.
	 * @throws DBException
	 *             db exception
	 */
	public List<RoomAlbum> getAllRoomAlbumByRoomId(final Integer roomId) throws DBException {
		List<RoomAlbum> roomAlbum = new ArrayList<RoomAlbum>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_ROOMALBUM_BY_ROOMID);
			pstmt.setLong(1, roomId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomAlbum.add(extractRoomAlbumBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ROOMID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMALBUM_BY_ROOMID, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return roomAlbum;

	}

	/**
	 * Returns all user requests.
	 * 
	 * @param userId
	 *            user id
	 * @return List<Requests>.
	 * @throws DBException
	 *             db exception
	 */
	public List<Requests> getRequestsByUserId(final Long userId) throws DBException {
		List<Requests> requests = new ArrayList<Requests>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_REQUESTS_BY_USERID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				requests.add(extractRequests(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQUESTS_BY_USERID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUESTS_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return requests;

	}

	/**
	 * Returns all user requests.
	 * 
	 * @param userId
	 *            user id
	 * @return List<Requests>.
	 * @throws DBException
	 *             db exception
	 */
	public List<RequestsBean> getAllRequests() throws DBException {
		List<RequestsBean> requests = new ArrayList<RequestsBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_REQUESTS);

			while (rs.next()) {
				requests.add(extractRequestsBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REQUESTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REQUESTS, ex);
		} finally {
			close(con, stmt, rs);
		}
		return requests;
	}

	/**
	 * Returns amount of unprocessed requests.
	 * 
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer getAmountOfUnprocReq() throws DBException {
		Integer count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_COUNT_UNPROCESSED_REQUESTS);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_COUNT_UNPROCESSED_REQUESTS, ex);
			throw new DBException(Messages.ERR_CANNOT_COUNT_UNPROCESSED_REQUESTS, ex);
		} finally {
			close(con, stmt, rs);
		}

		return count;

	}

	/**
	 * Returns amount of unpaid finance.
	 * 
	 * @param userId
	 *            user id
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer getAmountOfUnpaidFin(final Long userId) throws DBException {
		if (userId == null || userId <= 0) {
			return null;
		}
		Integer count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_COUNT_UNPAID_FINANCE);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_COUNT_UNPAID_FINANCE, ex);
			throw new DBException(Messages.ERR_CANNOT_COUNT_UNPAID_FINANCE, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}

	/**
	 * Returns amount of paid finance.
	 * 
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer getAmountOfPaidFin() throws DBException {
		Integer count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_COUNT_PAID_FINANCE);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_COUNT_PAID_FINANCE, ex);
			throw new DBException(Messages.ERR_CANNOT_COUNT_PAID_FINANCE, ex);
		} finally {
			close(con, stmt, rs);
		}
		return count;
	}

	/**
	 * Returns amount of new responses.
	 * 
	 * @param userId
	 *            user id
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer getAmountOfNewResp() throws DBException {
		Integer count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_COUNT_NEW_RESPONSES);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_COUNT_NEW_RESPONSES, ex);
			throw new DBException(Messages.ERR_CANNOT_COUNT_NEW_RESPONSES, ex);
		} finally {
			close(con, stmt, rs);
		}

		return count;

	}

	/**
	 * Returns amount of new responses for user id.
	 * 
	 * @param userId
	 *            user id
	 * @return Integer
	 * @throws DBException
	 *             db exception
	 */
	public Integer getAmountOfNewRespForUserId(final Long userId) throws DBException {
		Integer count = 0;
		if (userId == null || userId <= 0) {
			return count;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_COUNT_NEW_RESPONSES_BY_USERID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_COUNT_UNPROCESSED_REQUESTS, ex);
			throw new DBException(Messages.ERR_CANNOT_COUNT_UNPROCESSED_REQUESTS, ex);
		} finally {
			close(con, pstmt, rs);
		}

		return count;

	}

	/**
	 * Get room type by id.
	 * 
	 * @param id
	 *            room id
	 * @return RoomType
	 * @throws DBException
	 *             db exception
	 */
	public RoomTypes getRoomType(final Integer id) throws DBException {
		if (id < 0) {
			return null;
		}
		RoomTypes type = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ROOMTYPES_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				type = extractRoomTypes(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return type;
	}

	/**
	 * Get room type by room id.
	 * 
	 * @param room
	 *            id room id
	 * @return RoomType
	 * @throws DBException
	 *             db exception
	 */
	public RoomTypes getRoomTypeByRoomID(final Integer id) throws DBException {
		if (id == null || id < 0) {
			return null;
		}
		RoomTypes type = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ROOMTYPES_BY_ROOMID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				type = extractRoomTypes(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ROOMID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMTYPES_BY_ROOMID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return type;
	}

	/**
	 * Get activities by id.
	 * 
	 * @param id
	 *            activity id
	 * @return Activities
	 * @throws DBException
	 *             db exception
	 */
	public Activities getActivity(final Integer id) throws DBException {
		if (id < 0) {
			return null;
		}
		Activities activity = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_ACTIVITIES_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				activity = extractActivities(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ACTIVITIES_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACTIVITIES_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return activity;
	}

	/**
	 * Get room album bean by id.
	 * 
	 * @param id
	 *            room id
	 * @return RoomAlbumBean
	 * @throws DBException
	 *             db exception
	 */
	public RoomAlbumBean getRoomAlbumBean(final Integer id) throws DBException {
		if (id < 0) {
			return null;
		}
		RoomAlbumBean raBean = new RoomAlbumBean();
		List<RoomAlbum> album = new ArrayList<RoomAlbum>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ROOMALBUMBEAN_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				raBean.setId(rs.getLong(Fields.ENTITY_ID));
				raBean.setName(rs.getString(Fields.ROOMTYPES_NAME));
				raBean.setPrice(rs.getInt(Fields.ROOMTYPES_PRICE));
				raBean.setDescription(rs.getString(Fields.ROOMTYPES_DESCRIPTION));
				raBean.setImage(rs.getString(Fields.IMAGE));
				album.add(extractRoomAlbumBean(rs));
			}
			while (rs.next()) {
				album.add(extractRoomAlbumBean(rs));
			}
			raBean.setAlbum(album);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMALBUMBEAN_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMALBUMBEAN_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return raBean;
	}

	/**
	 * Get activities album bean by id.
	 * 
	 * @param id
	 *            room id
	 * @return RoomAlbumBean
	 * @throws DBException
	 *             db exception
	 */
	public ActivitiesAlbumBean getActivitiesAlbumBean(final Integer id) throws DBException {
		if (id < 0) {
			return null;
		}
		ActivitiesAlbumBean aaBean = new ActivitiesAlbumBean();
		List<ActivitiesAlbum> album = new ArrayList<ActivitiesAlbum>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_ACTIVITIESALBUMBEAN_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				aaBean.setId(rs.getLong(Fields.ENTITY_ID));
				aaBean.setName(rs.getString(Fields.ACTIVITIES_NAME));
				aaBean.setDescription(rs.getString(Fields.ACTIVITIES_DESCRIPTION));
				aaBean.setImage(rs.getString(Fields.IMAGE));
				album.add(extractActivitiesAlbumBean(rs));
			}
			while (rs.next()) {
				album.add(extractActivitiesAlbumBean(rs));
			}
			aaBean.setAlbum(album);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ACTIVITIESALBUMBEAN_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACTIVITIESALBUMBEAN_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return aaBean;
	}

	/**
	 * Get room bean.
	 * 
	 * @param checkIn
	 *            check in
	 * @param checkOut
	 *            check out
	 * @param roomTypeID
	 *            type id
	 * @return List<RoomBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<RoomBean> getAvailableRoomsById(final String checkIn, final String checkOut, final Integer roomTypeID)
			throws DBException {
		if (checkIn == null || checkOut == null || roomTypeID == null) {
			return null;
		}
		if (checkIn.isEmpty() || checkOut.isEmpty() || roomTypeID <= 0) {
			return null;
		}
		List<RoomBean> rbean = new ArrayList<RoomBean>();
		Rooms room = null;
		List<Rooms> rooms = new ArrayList<Rooms>();
		RoomBean roombean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Long offset = DateUtil.getOffset(checkIn, checkOut);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_AVAILABLE_ROOM_BY_ROOMTYPESID);
			int k = 1;
			pstmt.setString(k++, checkOut);
			pstmt.setString(k++, checkIn);
			pstmt.setInt(k++, roomTypeID);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkOut);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkOut);
			pstmt.setString(k, checkOut);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				roombean = extractRoomBean(rs);
				room = extractRooms(rs);
				roombean.setTotalPrice(offset * roombean.getPrice());
				rooms.add(room);
				while (rs.next()) {
					room = extractRooms(rs);
					rooms.add(room);
				}
				roombean.setRooms(rooms);
				rbean.add(roombean);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMBEAN_BY_ROOMTYPESID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMBEAN_BY_ROOMTYPESID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return rbean;
	}

	/**
	 * Get all available rooms.
	 * 
	 * @param checkIn
	 *            check in
	 * @param checkOut
	 *            check out
	 * @return List<RoomBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<RoomBean> getAvailableRooms(final String checkIn, final String checkOut) throws DBException {
		if (checkIn == null || checkOut == null || checkIn.isEmpty() || checkOut.isEmpty()) {
			return null;
		}
		List<RoomBean> rbean = new ArrayList<RoomBean>();
		Rooms room = null;
		List<Rooms> rooms = new ArrayList<Rooms>();
		RoomBean roombean = null;
		RoomBean tmp = null;
		Long id = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Long offset = DateUtil.getOffset(checkIn, checkOut);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_AVAILABLE_ROOM);
			int k = 1;
			pstmt.setString(k++, checkOut);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkOut);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkIn);
			pstmt.setString(k++, checkOut);
			pstmt.setString(k, checkOut);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				roombean = extractRoomBean(rs);
				room = extractRooms(rs);
				rooms.add(room);
				id = roombean.getId();
				roombean.setTotalPrice(offset * roombean.getPrice());
				while (rs.next()) {
					tmp = extractRoomBean(rs);
					if (!id.equals(tmp.getId())) {
						id = tmp.getId();
						roombean.setRooms(rooms);
						rbean.add(roombean);
						roombean = tmp;
						roombean.setTotalPrice(offset * roombean.getPrice());
						rooms = new ArrayList<Rooms>();
					}
					room = extractRooms(rs);
					rooms.add(room);
				}
				roombean.setRooms(rooms);
				rbean.add(roombean);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ROOMBEAN);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROOMBEAN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return rbean;
	}

	/**
	 * Returns all unpaid finances by userid.
	 * 
	 * @param userId
	 *            user id
	 * @return List<FinanceBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<FinanceBean> getUnpaidFinancesByUserId(final Long userId) throws DBException {
		if (userId == null || userId < 0) {
			return null;
		}
		List<FinanceBean> finances = new ArrayList<FinanceBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_FINANCE_BY_USERID_AND_UNPAID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				finances.add(extractFinanceBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_UNPAID_FINANCES_BY_USERID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_UNPAID_FINANCES_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return finances;
	}

	/**
	 * Returns all paid finances.
	 * 
	 * @return List<FinanceBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<FinanceBean> getFinances() throws DBException {
		List<FinanceBean> finances = new ArrayList<FinanceBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_PAID_FINANCE);
			while (rs.next()) {
				finances.add(extractFinanceBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_PAID_FINANCES);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PAID_FINANCES, ex);
		} finally {
			close(con, stmt, rs);
		}
		return finances;
	}

	/**
	 * Returns all paid finances by userid.
	 * 
	 * @param userId
	 *            user id
	 * @return List<FinanceBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<FinanceBean> getPaidFinancesByUserId(final Long userId) throws DBException {
		if (userId == null || userId < 0) {
			return null;
		}
		List<FinanceBean> finances = new ArrayList<FinanceBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_FINANCE_BY_USERID_AND_PAID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				finances.add(extractFinanceBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_PAID_FINANCES_BY_USERID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_PAID_FINANCES_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return finances;
	}

	/**
	 * Returns all finances by userid.
	 * 
	 * @param userId
	 *            user id
	 * @return List<FinanceBean>
	 * @throws DBException
	 *             db exception
	 */
	public List<FinanceBean> getFinancesByUserId(final Long userId) throws DBException {
		if (userId == null || userId < 0) {
			return null;
		}
		List<FinanceBean> finances = new ArrayList<FinanceBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_FINANCE_BY_USERID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				finances.add(extractFinanceBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_FINANCES_BY_USERID);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FINANCES_BY_USERID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return finances;
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateUser(final User user) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_USER);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update response status.
	 * 
	 * @param response
	 *            response to update
	 * @throws DBException
	 *             db exception
	 */
	public void updateResponse(final Responses response) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateResponse(con, response);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_RESPONSE_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_RESPONSE_BY_ID, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update status of out of date reservations.
	 * 
	 * 
	 * @throws DBException
	 *             db exception
	 */
	public void updateStatusOfOutResevations() throws DBException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(SQL_UPDATE_STATUS_OF_OUT_OF_DATE_RESERVATION);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_RESERVATION_STATUS_OUT_OF_DATE);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_RESERVATION_STATUS_OUT_OF_DATE, ex);
		} finally {
			close(con);
			close(stmt);
		}
	}

	/**
	 * Update reservation status id.
	 * 
	 * @param reservation
	 *            reservation to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateReservationStatus(final Reservation reservation) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateReservation(con, reservation);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_RESERVATIONSTATUS_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_RESERVATIONSTATUS_BY_ID, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update finance status id.
	 * 
	 * @param finance
	 *            finance to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateFinanceStatus(final Finance finance) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateFinance(con, finance);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_FINANCESTATUS_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FINANCESTATUS_BY_ID, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update requests.
	 * 
	 * @param request
	 *            request to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateRequests(final Requests request) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateRequests(con, request);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_REQUESTS);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REQUESTS, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update requests.
	 * 
	 * @param con
	 *            connection
	 * @param request
	 *            request to update.
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateRequests(final Connection con, final Requests request) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_REQUESTS_STATUS_BY_ID);
			int k = 1;
			pstmt.setInt(k++, request.getStatus().ordinal() + 1);
			pstmt.setLong(k, request.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update reservation status.
	 * 
	 * @param reservation
	 *            reservation to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateReservation(final Connection con, final Reservation reservation) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_RESERVATION_STATUS_BY_ID);
			int k = 1;
			pstmt.setInt(k++, reservation.getStatusId());
			pstmt.setLong(k, reservation.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update finance status.
	 * 
	 * @param finance
	 *            finance to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateFinance(final Connection con, final Finance finance) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_FINANCE_STATUS_BY_ID);
			int k = 1;
			pstmt.setInt(k++, finance.getStatus().ordinal() + 1);
			pstmt.setLong(k++, finance.getId());
			pstmt.setLong(k, finance.getUserId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            user to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateUser(final Connection con, final User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getName());
			pstmt.setString(k++, user.getSurname());
			pstmt.setString(k++, user.getPhone());
			pstmt.setString(k++, user.getEmail());
			pstmt.setLong(k, user.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update response.
	 * 
	 * @param response
	 *            response to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateResponse(final Connection con, final Responses response) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_RESPONSE_BY_ID);
			int k = 1;
			pstmt.setInt(k++, response.getStatus().ordinal() + 1);
			pstmt.setLong(k, response.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update room type.
	 * 
	 * @param type
	 *            type to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateRoomTypes(final RoomTypes type) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateRoomTypes(con, type);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ROOMTYPES, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ROOMTYPES, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update activities.
	 * 
	 * @param activity
	 *            activity to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateActivities(final Activities activity) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateActivities(con, activity);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ACTIVITIES, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ACTIVITIES, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update room album.
	 * 
	 * @param album
	 *            album to update.
	 * @throws DBException
	 *             db exception
	 */
	public void updateRoomAlbum(final RoomAlbum album) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateRoomAlbum(con, album);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_UPDATE_ROOMALBUM, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ROOMALBUM, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Update room types.
	 * 
	 * @param type
	 *            type to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateRoomTypes(final Connection con, final RoomTypes type) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_ROOMTYPES);
			int k = 1;
			pstmt.setString(k++, type.getName());
			pstmt.setString(k++, type.getDescription());
			pstmt.setInt(k++, type.getPrice());
			pstmt.setString(k++, type.getImage());
			pstmt.setInt(k++, type.getMax());
			pstmt.setLong(k, type.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update activities.
	 * 
	 * @param activity
	 *            activity to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateActivities(final Connection con, final Activities activity) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_ACTIVITIES);
			int k = 1;
			pstmt.setString(k++, activity.getName());
			pstmt.setString(k++, activity.getDescription());
			pstmt.setString(k++, activity.getImage());
			pstmt.setLong(k, activity.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Update room album.
	 * 
	 * @param album
	 *            album to update.
	 * @param con
	 *            connection
	 * @throws SQLException
	 *             sql exception
	 */
	private void updateRoomAlbum(final Connection con, final RoomAlbum album) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_ROOMALBUM);
			int k = 1;
			pstmt.setInt(k++, album.getRoomId());
			pstmt.setString(k++, album.getImage());
			pstmt.setLong(k++, album.getId());
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Closes a connection.
	 * 
	 * @param con
	 *            Connection to be closed.
	 */
	private void close(final Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Closes a statement object.
	 * 
	 * @param stmt
	 *            statement
	 */
	private void close(final Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	/**
	 * Closes a result set object.
	 * 
	 * @param rs
	 *            result set
	 */
	private void close(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Closes resources.
	 * 
	 * @param con
	 *            connection
	 * @param stmt
	 *            statement
	 * @param rs
	 *            result set
	 */
	private void close(final Connection con, final Statement stmt, final ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked.
	 */
	private void rollback(final Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return User
	 * @throws SQLException
	 *             sql exception
	 */
	private User extractUser(final ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setRegDate(rs.getString(Fields.USER_REG_DATE));
		user.setName(rs.getString(Fields.USER_FIRST_NAME));
		user.setSurname(rs.getString(Fields.USER_LAST_NAME));
		user.setIdrole(rs.getInt(Fields.USER_ROLE_ID));
		user.setPhone(rs.getString(Fields.USER_PHONE));
		return user;
	}

	/**
	 * Extracts a room bean entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return RoomBean
	 * @throws SQLException
	 *             sql exception
	 */
	private RoomBean extractRoomBean(final ResultSet rs) throws SQLException {
		RoomBean bean = new RoomBean();
		bean.setId(rs.getLong(Fields.ROOMS_ROOMTYPESID));
		bean.setName(rs.getString(Fields.ROOMTYPES_NAME));
		bean.setPrice(rs.getInt(Fields.ROOMTYPES_PRICE));
		bean.setMax(rs.getInt(Fields.ROOMTYPES_MAX));
		return bean;
	}

	/**
	 * Extracts a room bean entity from the result set.
	 * 
	 * @param rs
	 *            Result set from which a user entity will be extracted.
	 * @return RoomBean entity
	 * @throws SQLException
	 *             sql exception
	 */
	private Rooms extractRooms(final ResultSet rs) throws SQLException {
		Rooms room = new Rooms();
		room.setId(rs.getLong(Fields.ENTITY_ID));
		room.setNumber(rs.getInt(Fields.ROOMS_NUMBER));
		room.setRoomTypesId(rs.getInt(Fields.ROOMS_ROOMTYPESID));
		return room;
	}

	/**
	 * Extracts a room type object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a room type object will be extracted.
	 * @return room type object
	 * @throws SQLException
	 *             sql exception
	 */
	private RoomTypes extractRoomTypes(final ResultSet rs) throws SQLException {
		RoomTypes type = new RoomTypes();
		type.setId(rs.getLong(Fields.ENTITY_ID));
		type.setName(rs.getString(Fields.ROOMTYPES_NAME));
		type.setPrice(rs.getInt(Fields.ROOMTYPES_PRICE));
		type.setDescription(rs.getString(Fields.ROOMTYPES_DESCRIPTION));
		type.setImage(rs.getString(Fields.IMAGE));
		type.setMax(rs.getInt(Fields.ROOMTYPES_MAX));
		return type;
	}

	/**
	 * Extracts a response bean object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a response bean object will be
	 *            extracted.
	 * @return ResponseBean object
	 * @throws SQLException
	 *             sql exception
	 */
	private ResponseBean extractResponseBean(final ResultSet rs) throws SQLException {
		ResponseBean response = new ResponseBean();
		response.setId(rs.getLong(Fields.ENTITY_ID));
		response.setToUserId(rs.getLong(Fields.RESPONSES_TOUSERID));
		response.setReservationId(rs.getInt(Fields.RESPONSES_RESERVATION_ID));
		response.setMsg(rs.getString(Fields.RESPONSES_MSG));
		response.setStatus(ResponseStatus.values()[rs.getInt(Fields.RESPONSES_STATUS_ID) - 1]);
		response.setCheckIn(rs.getString(Fields.RESERVATION_CHECKIN));
		response.setCheckOut(rs.getString(Fields.RESERVATION_CHECKOUT));
		response.setMax(rs.getInt(Fields.ROOMTYPES_MAX));
		response.setPrice(rs.getInt(Fields.ROOMTYPES_PRICE));
		response.setNumber(rs.getInt(Fields.ROOMS_NUMBER));
		response.setName(rs.getString(Fields.ROOMTYPES_NAME));
		return response;
	}

	/**
	 * Extracts a responses object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a responses object will be extracted.
	 * @return responses object
	 * @throws SQLException
	 *             sql exception
	 */
	private Responses extractResponses(final ResultSet rs) throws SQLException {
		Responses response = new Responses();
		response.setId(rs.getLong(Fields.ENTITY_ID));
		response.setToUserId(rs.getLong(Fields.RESPONSES_TOUSERID));
		response.setReservationId(rs.getInt(Fields.RESPONSES_RESERVATION_ID));
		response.setMsg(rs.getString(Fields.RESPONSES_MSG));
		response.setStatus(ResponseStatus.values()[rs.getInt(Fields.RESPONSES_STATUS_ID) - 1]);
		return response;
	}

	/**
	 * Extracts a activity object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a activity object will be extracted.
	 * @return activities object
	 * @throws SQLException
	 *             sql exception
	 */
	private Activities extractActivities(final ResultSet rs) throws SQLException {
		Activities activity = new Activities();
		activity.setId(rs.getLong(Fields.ENTITY_ID));
		activity.setName(rs.getString(Fields.ACTIVITIES_NAME));
		activity.setDescription(rs.getString(Fields.ACTIVITIES_DESCRIPTION));
		activity.setImage(rs.getString(Fields.IMAGE));
		return activity;
	}

	/**
	 * Extracts a room album object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a room album object will be extracted.
	 * @return room type object
	 * @throws SQLException
	 *             sql exception
	 */
	private RoomAlbum extractRoomAlbum(final ResultSet rs) throws SQLException {
		RoomAlbum album = new RoomAlbum();
		album.setId(rs.getLong(Fields.ENTITY_ID));
		album.setRoomId(rs.getInt(Fields.ROOMALBUM_ROOMID));
		album.setImage(rs.getString(Fields.IMAGE));
		return album;
	}

	/**
	 * Extracts a booking history object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a booking history object will be
	 *            extracted.
	 * @return BookingHistoryBean
	 * @throws SQLException
	 *             sql exception
	 */
	private BookingHistoryBean extractBookingHistory(final ResultSet rs) throws SQLException {
		BookingHistoryBean bean = new BookingHistoryBean();
		bean.setId(rs.getLong(Fields.ENTITY_ID));
		bean.setCheckIn(rs.getString(Fields.RESERVATION_CHECKIN));
		bean.setCheckOut(rs.getString(Fields.RESERVATION_CHECKOUT));
		bean.setNumber(rs.getInt(Fields.ROOMS_NUMBER));
		bean.setType(rs.getString(Fields.ROOMTYPES_NAME));
		bean.setReservationDate(rs.getString(Fields.RESERVATION_RESERVATIONDATE));
		bean.setStatus(Status.values()[rs.getInt(Fields.RESERVATION_STATUSID) - 1]);
		bean.setUserId(rs.getLong(Fields.RESERVATION_USERID));
		bean.getUser().setName(rs.getString(Fields.BOOKINGHISTORY_USERNAME));
		bean.getUser().setSurname(rs.getString(Fields.BOOKINGHISTORY_USERSURNAME));
		return bean;
	}

	/**
	 * Extracts a reservation object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a reservation object will be extracted.
	 * @return Reservation
	 * @throws SQLException
	 *             sql exception
	 */
	private Reservation extractReservation(final ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setId(rs.getLong(Fields.ENTITY_ID));
		reservation.setCheckIn(rs.getString(Fields.RESERVATION_CHECKIN));
		reservation.setCheckOut(rs.getString(Fields.RESERVATION_CHECKOUT));
		reservation.setReservationDate(rs.getString(Fields.RESERVATION_RESERVATIONDATE));
		reservation.setUserId(rs.getLong(Fields.RESERVATION_USERID));
		reservation.setRoomId(rs.getInt(Fields.RESERVATION_ROOMID));
		return reservation;
	}

	/**
	 * Extracts a room album bean object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a room album object will be extracted.
	 * @return RoomAlbum
	 * @throws SQLException
	 *             sql exception
	 */
	private RoomAlbum extractRoomAlbumBean(final ResultSet rs) throws SQLException {
		RoomAlbum album = new RoomAlbum();
		album.setId(rs.getLong(Fields.ROOMALBUMBEAN_ID));
		album.setRoomId(rs.getInt(Fields.ROOMALBUMBEAN_ROOMID));
		album.setImage(rs.getString(Fields.ROOMALBUMBEAN_IMAGE));
		return album;
	}

	/**
	 * Extracts a request object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a request object will be extracted.
	 * @return Requests
	 * @throws SQLException
	 *             sql exception
	 */
	private Requests extractRequests(final ResultSet rs) throws SQLException {
		Requests request = new Requests();
		request.setId(rs.getLong(Fields.ENTITY_ID));
		request.setAmount(rs.getInt(Fields.REQUESTS_AMOUNT));
		request.setRoomTypesId(rs.getInt(Fields.REQUESTS_ROOMTYPESID));
		request.setCheckIn(rs.getString(Fields.REQUESTS_CHECKIN));
		request.setCheckOut(rs.getString(Fields.REQUESTS_CHECKOUT));
		request.setUserId(rs.getLong(Fields.REQUESTS_USERID));
		request.setRequestStatusId(rs.getInt(Fields.REQUESTS_REQUESTSTATUSID));
		request.setStatus(RequestStatus.values()[request.getRequestStatusId() - 1]);
		request.setDate(rs.getString(Fields.REQUESTS_DATE));
		return request;
	}

	/**
	 * Extracts a request object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a request object will be extracted.
	 * @return RequestsBean
	 * @throws SQLException
	 *             sql exception
	 */
	private RequestsBean extractRequestsBean(final ResultSet rs) throws SQLException {
		RequestsBean request = new RequestsBean();
		request.setId(rs.getLong(Fields.ENTITY_ID));
		request.setAmount(rs.getInt(Fields.REQUESTS_AMOUNT));
		request.setRoomTypesId(rs.getInt(Fields.REQUESTS_ROOMTYPESID));
		request.setCheckIn(rs.getString(Fields.REQUESTS_CHECKIN));
		request.setCheckOut(rs.getString(Fields.REQUESTS_CHECKOUT));
		request.setUserId(rs.getLong(Fields.REQUESTS_USERID));
		request.setRequestStatusId(rs.getInt(Fields.REQUESTS_REQUESTSTATUSID));
		request.setStatus(RequestStatus.values()[request.getRequestStatusId() - 1]);
		request.setDate(rs.getString(Fields.REQUESTS_DATE));
		request.getUser().setName(rs.getString(Fields.REQUESTSBEAN_USERNAME));
		request.getUser().setSurname(rs.getString(Fields.REQUESTSBEAN_USERSURNAME));
		return request;
	}

	/**
	 * Extracts a activities album bean object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a activities album object will be
	 *            extracted.
	 * @return room type object
	 * @throws SQLException
	 *             sql exception
	 */
	private ActivitiesAlbum extractActivitiesAlbumBean(final ResultSet rs) throws SQLException {
		ActivitiesAlbum album = new ActivitiesAlbum();
		album.setId(rs.getLong(Fields.ACTIVITIESALBUMBEAN_ID));
		album.setActivitiesId(rs.getInt(Fields.ENTITY_ID));
		album.setImage(rs.getString(Fields.ACTIVITIESALBUMBEAN_IMAGE));
		return album;
	}

	/**
	 * Extracts a finance bean object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a finance bean object will be extracted.
	 * @return FinanceBean object
	 * @throws SQLException
	 *             sql exception
	 */
	private FinanceBean extractFinanceBean(final ResultSet rs) throws SQLException {
		FinanceBean finance = new FinanceBean();
		finance.setId(rs.getLong(Fields.ENTITY_ID));
		finance.setBill(rs.getLong(Fields.FINANCE_BILL));
		finance.setCheckIn(rs.getString(Fields.RESERVATION_CHECKIN));
		finance.setCheckOut(rs.getString(Fields.RESERVATION_CHECKOUT));
		finance.setNumber(rs.getInt(Fields.ROOMS_NUMBER));
		finance.setReservationId(rs.getInt(Fields.FINANCE_RESERVATIONID));
		finance.setStatus(FinanceStatus.values()[rs.getInt(Fields.FINANCE_FINANCESTATUSID) - 1]);
		finance.setType(rs.getString(Fields.ROOMTYPES_NAME));
		finance.setUserId(rs.getLong(Fields.FINANCE_USERID));
		finance.setUsername(rs.getString(Fields.FINANCEBEAN_USERNAME));
		return finance;
	}

	/**
	 * Extracts a finance object from the result set.
	 * 
	 * @param rs
	 *            Result set from which a finance object will be extracted.
	 * @return Finance object
	 * @throws SQLException
	 *             sql exception
	 */
	private Finance extractFinance(final ResultSet rs) throws SQLException {
		Finance finance = new Finance();
		finance.setId(rs.getLong(Fields.ENTITY_ID));
		finance.setBill(rs.getLong(Fields.FINANCE_BILL));
		finance.setReservationId(rs.getInt(Fields.FINANCE_RESERVATIONID));
		finance.setStatus(FinanceStatus.values()[rs.getInt(Fields.FINANCE_FINANCESTATUSID) - 1]);
		finance.setUserId(rs.getLong(Fields.FINANCE_USERID));
		return finance;
	}

	/**
	 * Delete reservation.
	 * 
	 * @param id
	 *            id to delete.
	 * @return boolean
	 * @throws DBException
	 *             db exception
	 */
	public boolean deleteReservationById(final Integer id) throws DBException {
		if (id == null || id <= 0) {
			return false;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_RESERVATION_BY_ID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_RESERVATION_BY_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_DELETE_RESERVATION_BY_ID, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return true;
	}

	/**
	 * Delete finance.
	 * 
	 * @param id
	 *            id to delete.
	 * @return boolean
	 * @throws DBException
	 *             db exception
	 */
	public boolean deleteFinanceById(final Long id) throws DBException {
		if (id == null || id <= 0) {
			return false;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_FINANCE_BY_ID);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_FINANCE_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_DELETE_FINANCE_BY_ID, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return true;
	}

	/**
	 * Delete request.
	 * 
	 * @param userId
	 *            userId to delete
	 * @param id
	 *            id to delete.
	 * @return boolean
	 * @throws DBException
	 *             db exception
	 */
	public boolean deleteRequestById(final Long id, final Long userId) throws DBException {
		if (id == null || id <= 0 || userId == null || userId <= 0) {
			return false;
		}
		LOG.trace("Delete request: id = " + id + " userId = " + userId);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_REQUEST_BY_ID);
			int k = 1;
			pstmt.setLong(k++, id);
			pstmt.setLong(k, userId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_REQUEST_BY_ID);
			throw new DBException(Messages.ERR_CANNOT_DELETE_REQUEST_BY_ID, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return true;
	}

	/**
	 * Delete response and reservation by id.
	 * 
	 * @param id
	 *            id to delete.
	 * @return boolean
	 * @throws DBException
	 *             db exception
	 */
	public boolean deleteResponseById(final Integer id) throws DBException {
		if (id == null || id <= 0) {
			return false;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_RESERVATION_RESPONSE_BY_RESPONSEID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			LOG.error(Messages.ERR_CANNOT_DELETE_RESERVATION_RESPONSE_BY_RESPONSEID);
			throw new DBException(Messages.ERR_CANNOT_DELETE_RESERVATION_RESPONSE_BY_RESPONSEID, ex);
		} finally {
			close(con);
			close(pstmt);
		}
		return true;
	}
}