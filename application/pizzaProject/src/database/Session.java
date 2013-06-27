/*
 * Klasa reprezentująca obecną sesję użytkownika
 * UWAGA - sesja określa tryb dostępu do bazy - docelowo (użytkownik/właściciel/
 * admin/?login?), a nie samo konto użytkownika!
 */

package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class Session {
	//ustawia tryb dostępu do bazy (login/user/owner/(TODO admin))
	//usunąłem ustawienie autoCommit(false), bo w sumie nie wiem czemu byłoby
	//to pożądane
	static void setRole(String url, String role, String password) {
		try {
			if(instance.connection != null)
				instance.connection.close();
			
			instance.connection = DriverManager.getConnection(url, role, password);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Session.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
    }
    
	//Singleton
	private Session() {	}
	static final Session instance = new Session();
	
	//dostępność w obrębie kodu zajmującego się kontaktem z bazą
	Connection connection = null;
}
