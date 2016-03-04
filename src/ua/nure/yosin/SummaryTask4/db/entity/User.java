package ua.nure.yosin.SummaryTask4.db.entity;

/**
 * User entity.
 * 
 * @author Hasan Yosin
 * 
 */
public class User extends Entity {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -6889036256149495388L;
	/**
	 * First name.
	 */
	private String name;
	/**
	 * Second name.
	 */
	private String surname;
	/**
	 * Registration date.
	 */
	private String regDate;
	/**
	 * Email.
	 */
	private String email;
	/**
	 * Password.
	 */
	private String password;
	/**
	 * Id role.
	 */
	private Integer idrole;
	/**
	 * Phone.
	 */
	private String phone;

	/**
	 * Get name.
	 * 
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Set name.
	 * 
	 * @param name2
	 *            the name to set
	 */
	public final void setName(final String name2) {
		this.name = name2;
	}

	/**
	 * Get surname.
	 * 
	 * @return the surname
	 */
	public final String getSurname() {
		return surname;
	}

	/**
	 * Set surname.
	 * 
	 * @param surname2
	 *            the surname to set
	 */
	public final void setSurname(final String surname2) {
		this.surname = surname2;
	}

	/**
	 * Get registration date.
	 * 
	 * @return the regDate
	 */
	public final String getRegDate() {
		return regDate;
	}

	/**
	 * Set registration date.
	 * 
	 * @param regDate2
	 *            the regDate to set
	 */
	public final void setRegDate(final String regDate2) {
		this.regDate = regDate2;
	}

	/**
	 * Get user email.
	 * 
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * Set user email.
	 * 
	 * @param email2
	 *            the email to set
	 */
	public final void setEmail(final String email2) {
		this.email = email2;
	}

	/**
	 * Get password.
	 * 
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Set password.
	 * 
	 * @param password2
	 *            password to set
	 */
	public final void setPassword(final String password2) {
		this.password = password2;
	}

	/**
	 * Get id role.
	 * 
	 * @return the idrole
	 */
	public final Integer getIdrole() {
		return idrole;
	}

	/**
	 * Set id role.
	 * 
	 * @param idrole2
	 *            the idrole to set
	 */
	public final void setIdrole(final Integer idrole2) {
		this.idrole = idrole2;
	}

	/**
	 * Get phone.
	 * 
	 * @return the phone
	 */
	public final String getPhone() {
		return phone;
	}

	/**
	 * Set phone.
	 * 
	 * @param phone2
	 *            the phone to set
	 */
	public final void setPhone(final String phone2) {
		this.phone = phone2;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + name 
				+ ", lastName=" + surname + "," + " roleId=" + idrole
				+ " phone = " + phone + "]";
	}

}
