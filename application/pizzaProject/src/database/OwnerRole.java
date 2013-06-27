/*
 * Klasa będąca implementacją czynności wykonywanych przez rolę "wlasciciel" w bazie.
 * Tutaj powinny znajdować się wszystkie zapytania, jakie użytkownik o tym
 * poziomie uprawnień może wykonać za pośrednictwem programu.
 */

package database;

import java.sql.SQLException;

public class OwnerRole {
	public OwnerRole() throws SQLException {
		Session.setRole(Constants.PATH, 
						Constants.LOGINS.get(Constants.ROLE.owner), 
						Constants.PASSWORDS.get(Constants.ROLE.owner));
	}
}