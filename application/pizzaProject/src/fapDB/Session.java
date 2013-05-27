/*
 * Klasa reeprezentująca obecną sesję użytkownika
 * Wstępnie pomyślana jako Singleton, czyli zmiana użytkownika (sesji)
 * wymaga wylogowania (zakończenia) użytkownika (sesji) TODO - logout
 */

package fapDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Session {
	// zwraca obecną sesję
	// ma sens tylko jeśli użytkownik się zalogował!
	public static Session current() throws IllegalStateException {
		if(instance.logged) 
			return instance;
		else
			throw new IllegalStateException();
	}
	
	public static void login(String url, String username, String password) {
		try {
			instance.connection = DriverManager.getConnection(url, username, password);
			instance.connection.setAutoCommit(false);
			instance.logged = true;
		} catch (SQLException ex) {
			//TODO - poprawić obsługę wyjątków
			Logger lgr = Logger.getLogger(Session.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	// rzuca jesli nie ma tabeli o podanej nazwie lub baza ma focha
	public Table getTable(String name) throws IllegalArgumentException, SQLException {
		return new Table(connection, name);
	}
	
	//Singleton
	private Session() {	}
	private static final Session instance = new Session();
	
	private boolean logged = false;
	private Connection connection = null;
	
}
