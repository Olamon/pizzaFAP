/*
 * Klasa reeprezentująca obecną sesję użytkownika
 * Wstępnie pomyślana jako Singleton, czyli zmiana użytkownika (sesji)
 * wymaga wylogowania (zakończenia) użytkownika (sesji) TODO - logout
 */

package fapDB;

import utils.StringUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
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

    //czasem przyda się bardziej ogólne zapytanie jak trzeba coś selekcić po wielu tabelach naraz z wyszukiwaniem
    public ResultSet selectQuery(String[] columnNames, String from, String conditions) throws SQLException
    {
        Statement st = connection.createStatement();
        String columns;
        if(columnNames == null || columnNames.length == 0)
            columns = "*";
        else
            columns = StringUtils.join(columnNames, ",");
        ResultSet rs = st.executeQuery("SELECT " + columns + " FROM " + from + ((conditions!=null&&conditions.length()>0)?" WHERE " + conditions:""));
        return rs;
    }
	
	//Singleton
	private Session() {	}
	private static final Session instance = new Session();
	
	private boolean logged = false;
	private Connection connection = null;
}
