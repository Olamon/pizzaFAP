/*
 * Prototyp modelu właściciela
 */

package roles;

import database.Session;

public class Owner {
	Owner() {
		Session.setRole("jdbc:postgresql://localhost/pizzadb", "wlascicielrole", "wlascicielrole");
	}
}