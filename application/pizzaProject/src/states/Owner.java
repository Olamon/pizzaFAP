/*
 * Prototyp modelu właściciela
 */

package states;

import java.util.logging.Level;
import java.util.logging.Logger;

import database.OwnerRole;

public class Owner {
	Owner() {
		try {
			//pozyskujemy uprawnienia
			role = new OwnerRole();
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	private OwnerRole role;
}