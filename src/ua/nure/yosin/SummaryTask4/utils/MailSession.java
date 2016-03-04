package ua.nure.yosin.SummaryTask4.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import ua.nure.yosin.SummaryTask4.db.entity.Reservation;
import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * Mail Session.
 * 
 * @author Hasan Yosin
 *
 */
public class MailSession {
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(MailSession.class);
	/**
	 * Session.
	 */
	private static final Session SESSION = init();
	/**
	 * Cabinet.
	 */
	private static final String CABINET = "http://localhost:8080/SummaryTask4/controller?command=login";
	/**
	 * Subject name.
	 */
	private static final String CONFIRMRESERVATIONRU = "Подтвердите бронирование";
	/**
	 * Subject name.
	 */
	private static final String CONFIRMRESERVATIONEN = "Confirm resevation";
	/**
	 * Subject name.
	 */
	private static final String CONFIRMEDEN = "Reservation confirmed";
	/**
	 * Subject name.
	 */
	private static final String CONFIRMEDRU = "Бронирование подтверждено";

	/**
	 * From.
	 */
	private static final String FROM = "yosin.businesshotel@gmail.com";
	/**
	 * Content type.
	 */
	private static final String CONTENT_TYPE = "Content-type";
	/**
	 * Text/html.
	 */
	private static final String TEXT_HTML = "text/html; charset=UTF-8";
	/**
	 * UTF-8.
	 */
	private static final String UTF8 = "UTF-8";
	/**
	 * Confirm link ru.
	 */
	private static final String CONFIRMLINKRU = "Ссылка на ваш кабинет <a href='" + CABINET + "'>кабинет</a>";
	/**
	 * Confirm link en.
	 */
	private static final String CONFIRMLINKEN = "Link to your cabinet <a href='" + CABINET + "'>cabinet</a>";

	/**
	 * Initialization.
	 * 
	 * @return Session
	 */
	private static Session init() {
		LOG.trace("Mail session starts");
		Session session = null;
		try {
			Context initialContext = new InitialContext();
			session = (Session) initialContext.lookup("java:comp/env/mail/Session");
		} catch (Exception ex) {
			LOG.error("mail session lookup error", ex);
		}
		return session;
	}

	/**
	 * Send reservation infomation.
	 * 
	 * @param user
	 *            user
	 * @param loc
	 *            locale
	 * @param reservation
	 *            reservation
	 */
	public static void sendReservationInfo(final User user, final String loc, final Reservation reservation) {
		try {
			LOG.trace("Send reservation info");
			Message msg = new MimeMessage(SESSION);
			LOG.trace("Set from: " + FROM);
			msg.setFrom(new InternetAddress(FROM));
			LOG.trace("Set to: " + user.getEmail());
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			LOG.trace("Set content to reservation info");
			String locale = loc;
			if (locale == null) {
				locale = "en";
			}
			if (locale.equalsIgnoreCase("ru")) {
				setReservationInfoRu(msg, user, reservation);
			} else {
				setReservationInfoEn(msg, user, reservation);
			}
			Date date = new Date();
			LOG.trace("Set send date " + date);
			msg.setSentDate(date);
			LOG.trace("Send message ");
			Transport.send(msg);
		} catch (AddressException e) {
			LOG.error(e);
		} catch (MessagingException e) {
			LOG.error(e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}
	}

	/**
	 * Send confirmed reservation infomation.
	 * 
	 * @param user
	 *            user
	 * @param loc
	 *            locale
	 * @param reservation
	 *            reservation
	 */
	public static void sendConfirmedReservationInfo(final User user, final String loc, final Reservation reservation) {
		try {
			LOG.trace("Send confirmed reservation info");
			Message msg = new MimeMessage(SESSION);
			LOG.trace("Set from: " + FROM);
			msg.setFrom(new InternetAddress(FROM));
			LOG.trace("Set to: " + user.getEmail());
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			LOG.trace("Set content to reservation info");
			String locale = loc;
			if (locale == null) {
				locale = "en";
			}
			if (locale.equalsIgnoreCase("ru")) {
				setConfirmedReservationInfoRu(msg, user, reservation);
			} else {
				setConfirmedReservationInfoEn(msg, user, reservation);
			}
			Date date = new Date();
			LOG.trace("Set send date " + date);
			msg.setSentDate(date);
			LOG.trace("Send message ");
			Transport.send(msg);
		} catch (AddressException e) {
			LOG.error(e);
		} catch (MessagingException e) {
			LOG.error(e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e);
		}
	}

	/**
	 * Set reservation information.
	 * 
	 * @param msg
	 *            message
	 * @param user
	 *            user
	 * @param info
	 *            infomation
	 * @throws MessagingException
	 *             exception
	 * @throws UnsupportedEncodingException
	 *             exception
	 */
	private static void setReservationInfoRu(final Message msg, final User user, final Reservation info)
			throws MessagingException, UnsupportedEncodingException {
		msg.setSubject(CONFIRMRESERVATIONRU);
		Multipart multipart = new MimeMultipart();
		InternetHeaders emailAndPass = new InternetHeaders();
		emailAndPass.addHeader(CONTENT_TYPE, TEXT_HTML);
		String hello = "<h1>Здравствуйте, <strong>" + user.getName() + " " + user.getSurname() + "</strong>!</h1><br />"
				+ "<p>Вы успешно забронировали номер c <strong>" + info.getCheckIn() + "</strong> по <strong>"
				+ info.getCheckOut() + "</strong><br /><br /><br />";
		String data = "<br />Чтобы подтвердить номер, вам необходимо в течении двух дней оплатить за номер.</p>"
				+ " <br /> <p>Подтверждение об оплате вы можете сделать в своем личном кабинете в разделе финансы.</p>"
				+ " <br /><br /><br /><br /><h4>С наилучшими пожеланиями,<br /> <strong>Luxury Hotel</strong></h4>";

		MimeBodyPart greetingAndData = new MimeBodyPart(emailAndPass, (hello + data).getBytes(UTF8));

		InternetHeaders headers = new InternetHeaders();
		headers.addHeader(CONTENT_TYPE, TEXT_HTML);
		MimeBodyPart link = new MimeBodyPart(headers, CONFIRMLINKRU.getBytes(StandardCharsets.UTF_8));

		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(link);

		msg.setContent(multipart);
	}

	/**
	 * Set reservation information.
	 * 
	 * @param msg
	 *            message
	 * @param user
	 *            user
	 * @param info
	 *            infomation
	 * @throws MessagingException
	 *             exception
	 * @throws UnsupportedEncodingException
	 *             exception
	 */
	private static void setReservationInfoEn(final Message msg, final User user, final Reservation info)
			throws MessagingException, UnsupportedEncodingException {
		msg.setSubject(CONFIRMRESERVATIONEN);
		Multipart multipart = new MimeMultipart();
		InternetHeaders emailAndPass = new InternetHeaders();
		emailAndPass.addHeader(CONTENT_TYPE, TEXT_HTML);
		String hello = "<h1>Hello, <strong>" + user.getName() + " " + user.getSurname() + "<strong>!</h1>\n"
				+ "<p>We congratulate you with booking room from <strong>" + info.getCheckIn() + "</strong> to <strong>"
				+ info.getCheckOut() + "</strong></p><br /><br /><br />";
		String data = "<br /> <p>In order to confirm booking, you need to make payment within two days."
				+ " <br /> <p>You can confirm your payment in your private cabinet in finance section.</p>"
				+ " <br /><br /><br /><br /><h4>Bests regards,<br /> <strong>Luxury Hotel</strong></h4>";

		MimeBodyPart greetingAndData = new MimeBodyPart(emailAndPass, (hello + data).getBytes(UTF8));

		InternetHeaders headers = new InternetHeaders();
		headers.addHeader(CONTENT_TYPE, TEXT_HTML);
		MimeBodyPart link = new MimeBodyPart(headers, CONFIRMLINKEN.getBytes(StandardCharsets.UTF_8));

		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(link);

		msg.setContent(multipart);
	}

	/**
	 * Set confirmed reservation information.
	 * 
	 * @param msg
	 *            message
	 * @param user
	 *            user
	 * @param info
	 *            infomation
	 * @throws MessagingException
	 *             exception
	 * @throws UnsupportedEncodingException
	 *             exception
	 */
	private static void setConfirmedReservationInfoEn(final Message msg, final User user, final Reservation info)
			throws MessagingException, UnsupportedEncodingException {
		msg.setSubject(CONFIRMEDEN);
		Multipart multipart = new MimeMultipart();
		InternetHeaders emailAndPass = new InternetHeaders();
		emailAndPass.addHeader(CONTENT_TYPE, TEXT_HTML);
		String hello = "<h1>Dear, <strong>" + user.getName() + " " + user.getSurname() + "</strong>!</h1><br />"
				+ "<p>We hereby confirm your reservation of room from  <strong>" + info.getCheckIn()
				+ "</strong> to <strong>" + info.getCheckOut() + "</strong><br /><br /><br />";
		String data = "<br />Hopefully, you will enjoy having a rest in our Hotel.</p>"
				+ " <br /><br /><br /><br /><br /><h4>Best regards,<br /> <strong>Luxury Hotel</strong></h4>";

		MimeBodyPart greetingAndData = new MimeBodyPart(emailAndPass, (hello + data).getBytes(UTF8));
		InternetHeaders headers = new InternetHeaders();
		headers.addHeader(CONTENT_TYPE, TEXT_HTML);
		MimeBodyPart link = new MimeBodyPart(headers, CONFIRMLINKEN.getBytes(StandardCharsets.UTF_8));
		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(link);
		msg.setContent(multipart);
	}

	/**
	 * Set confirmed reservation information.
	 * 
	 * @param msg
	 *            message
	 * @param user
	 *            user
	 * @param info
	 *            infomation
	 * @throws MessagingException
	 *             exception
	 * @throws UnsupportedEncodingException
	 *             exception
	 */
	private static void setConfirmedReservationInfoRu(final Message msg, final User user, final Reservation info)
			throws MessagingException, UnsupportedEncodingException {
		msg.setSubject(CONFIRMEDRU);
		Multipart multipart = new MimeMultipart();
		InternetHeaders emailAndPass = new InternetHeaders();
		emailAndPass.addHeader(CONTENT_TYPE, TEXT_HTML);
		String hello = "<h1>Здравствуйте, <strong>" + user.getName() + " " + user.getSurname() + "</strong>!</h1><br />"
				+ "<p>Вы успешно подтвердили запрос о бронировании номера c <strong>" + info.getCheckIn()
				+ "</strong> по <strong>" + info.getCheckOut() + "</strong><br /><br /><br />";
		String data = "<br />Желаем успешно провести время.</p>"
				+ " <br /><br /><br /><br /><br /><h4>С наилучшими пожеланиями,<br /> <strong>Luxury Hotel</strong></h4>";

		MimeBodyPart greetingAndData = new MimeBodyPart(emailAndPass, (hello + data).getBytes(UTF8));
		InternetHeaders headers = new InternetHeaders();
		headers.addHeader(CONTENT_TYPE, TEXT_HTML);
		MimeBodyPart link = new MimeBodyPart(headers, CONFIRMLINKRU.getBytes(StandardCharsets.UTF_8));
		multipart.addBodyPart(greetingAndData);
		multipart.addBodyPart(link);
		msg.setContent(multipart);
	}

}
