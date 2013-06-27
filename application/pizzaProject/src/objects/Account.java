/*
 *  klasa odzwierciedlająca pojedynczego uzytkownika
 *  zmieniłem jej nazwę, bo był konflikt między użytkownikiem - użytkownikiem
 *  aplikacji, a użytkownikiem - osobie o uprawnieniach użytkownika
 */

/*
 * Zmieniłem tę klasę na klasę będącą pojemnikiem na wartości pojedyńczej krotki
 * z danej tabeli, a metody dotyczące bazy przeniosłem do paczki "database"
 */
package objects;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Account implements DatabaseObject<Account> {
    public String email;
    public String password;
    public String typ_konta;

    public Account(String email, String password, String typ_konta) {
        this.email = new String(email);
        this.password = new String(password);
        this.typ_konta = new String(typ_konta);
    }
    
    public static Account converter = new Account("", "", "");
    public Vector<Account> convert(ResultSet rs) {
    	Vector<Account> result = new Vector<Account>();
    	try {
    		while(rs.next()) {
        		result.add(new Account(rs.getString("email"), 
        							   rs.getString("pass"),
        							   rs.getString("typ_konta")));
        	}
    	}
    	catch (Exception ex) {
			Logger lgr = Logger.getLogger(Account.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
    	return result;
    }
    
    /*
    //zwraca listę wszystkich uzytkownikow w bazie danych
    public static List<Account> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("uzytkownik").select(null));
    }
    
    //zwraca listę wszystkich ocen użytkownika
    public List<Ocena> GetUserReviews() throws SQLException
    {
        ResultSet rs = Session.current().selectQuery(null, "ocena", "email = " + email);
        if (rs != null)
            return Ocena.GetAll(rs);
        else return null;
    }
    */
}
