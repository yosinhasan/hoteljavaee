package ua.nure.yosin.SummaryTask4.db;

import ua.nure.yosin.SummaryTask4.db.entity.User;

/**
 * Role entity.
 * 
 * @author Hasan Yosin
 * 
 */

public enum Role {
	/**
	 * Admin.
	 */
	ADMIN,
	/**
	 * Manager.
	 */
	MANAGER,
	/**
	 * Client.
	 */
	CLIENT;
	/**
	 * Get role.
	 * 
	 * @param user
	 *            user
	 * @return Role
	 */
	public static Role getRole(final User user) {
		int roleId = user.getIdrole() - 1;
		return Role.values()[roleId];
	}

	/**
	 * Get name.
	 * 
	 * @return String.
	 */
	public String getName() {
		return name().toLowerCase();
	}

}
