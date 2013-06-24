/*
 * Model loginu, wykonuje pracę związaną z logowaniem i rejestracją
 */

package roles;

import java.util.logging.Level;
import java.util.logging.Logger;

import utils.StringUtils;
import database.Session;
import objects.Account;

public class Login {
	Login() {
		Session.setRole("jdbc:postgresql://localhost/pizzadb", "loginrole", "loginrole");
	}
	public boolean login(String login, String password) {
		String authResult = null;
		try {
			String hashedPwd = StringUtils.hash(password);
			Account account = new Account(login, hashedPwd, "");
			//authResult zawiera typ_konta użytkownika, który można zamienić
			//na odpowiednią Rolę
			authResult = account.Authenticate();
			Role rt = Role.StringToRole(authResult);
			return State.transition(rt);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
		}
	}
	public boolean register(String login, String password, String accountType) {
		try {
			String hashedPwd = StringUtils.hash(password);
			Account account = new Account(login, hashedPwd, accountType);
			if( !account.Exists() ) {
				account.Save();
				return true;
			} else
				return false;
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
		}
	}
}