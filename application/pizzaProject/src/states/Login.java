/*
 * Model loginu, wykonuje pracę związaną z logowaniem i rejestracją
 * Ta klasa zawiera implementację czynności jakie użyszkodnik może wykonać
 * poprzez uporczywe klikanie przed zalogowaniem się, przy czym
 * akcje dotyczące bazy są wykonywane przez LoginRole.
 */

package states;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import java.sql.ResultSet;

import utils.StringUtils;
import database.Constants;
import database.LoginRole;
import objects.Account;

public class Login {
	Login() {
		try {
			//pozyskujemy uprawnienia
			role = new LoginRole();
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	public boolean login(String login, String password) {
		try {
			String hashedPwd = StringUtils.hash(password);
			
			//zwraca krotki, które mają taki login i hasło (0 albo 1)
			ResultSet rs = role.Account_authenticate(login, hashedPwd);
			Vector<Account> queryResult = Account.converter.convert(rs);
			
			StateManager.State newState = null;
			String accountType = null;
			if( queryResult.isEmpty() )
				return StateManager.transition(StateManager.State.invalid);
			else
				accountType = queryResult.get(0).typ_konta;

			//jeżeli nie znaleziono krotki to logowanie nie powiodło się,
			//wpp aplikacja przechodzi w odpowiedni stan
			//(stan invalid to sztuczny stan, który nie powoduje żadnej akcji)
			if( accountType.equals(Constants.ROLE.user.toString()) ) {
				newState = StateManager.State.user;
			} else if( accountType.equals(Constants.ROLE.owner.toString()) ) {
				newState = StateManager.State.owner;
			} else if( accountType.equals(Constants.ROLE.admin.toString()) ) {
				newState = StateManager.State.admin;
			} else if( accountType.equals(Constants.ROLE.login.toString()) ) {
				newState = StateManager.State.login;
			} else {
				newState = StateManager.State.invalid;
			}
			
			boolean result = StateManager.transition(newState);
			
			if(result == true)
				StateManager.setUserId(login);
			
			return result;
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
		}
	}
	public boolean register(String login, String password, String accountType) {
		try {
			ResultSet rs = role.Account_exists(login);
			Vector<Account> queryResult = Account.converter.convert(rs);
			
			if( queryResult.isEmpty() ) {
				String hashedPwd = StringUtils.hash(password);
				Account account = new Account(login, hashedPwd, accountType);

				return role.Account_insert(account) != 0;
			} else
				return false;
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return false;
		}
	}
	
	private LoginRole role;
}