/*
 * Klasa reprezentująca obecną sesję użytkownika
 * UWAGA - sesja określa tryb dostępu do bazy - docelowo (użytkownik/właściciel/
 * admin/?login?), a nie samo konto użytkownika!
 */

package database;

import utils.StringUtils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Session {
	// zwraca obecną sesję
	public static Session current() {
		return instance;
	}
	
	//ustawia tryb dostępu do bazy (login/user/owner/(TODO admin))
	public static void setRole(String url, String role, String password) {
		try {
			if(instance.connection != null)
				instance.connection.close();
			
			instance.connection = DriverManager.getConnection(url, role, password);
			instance.connection.setAutoCommit(false);
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
    //tylko do wewnętrzenego użytku, wszystko związane z inputem użytkownika trzeba robić przez Session.current().connection.prepareStatement()
	//to trzeba wydzielić gdzieś indziej
    public ResultSet selectQuery(String[] columnNames, String from, String conditions) throws SQLException
    {
        Statement st = connection.createStatement();
        String columns;
        if(columnNames == null || columnNames.length == 0)
            columns = "*";
        else
            columns = StringUtils.join(columnNames, ",");
        //TODO to trzeba poprawić! W tym momencie trzeba pamiętać, żeby każdą
        //wartość w conditions umieszczać w cudzysłowiach, co jest bardzo
        //niewygodne i prowadzi do błedów.
        ResultSet rs = st.executeQuery("SELECT " + columns + " FROM " + from + ((conditions!=null&&conditions.length()>0)?" WHERE " + conditions:""));
        return rs;
    }
    
	//Singleton
	private Session() {	}
	private static final Session instance = new Session();
	
    // Connection musi być publiczne żeby można było używać gdzie indziej connection.PrepareStatement itp
	public Connection connection = null;
}
