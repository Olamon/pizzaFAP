/*
 * Prototyp modelu użytkownika
 */

package roles;

import database.Session;

public class User {
	User() {
		Session.setRole("jdbc:postgresql://localhost/pizzadb", "uzytkownikrole", "uzytkownikrole");
	}
}