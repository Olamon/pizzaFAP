/*
 *  klasa odzwierciedlająca pojedynczego uzytkownika
 *  zmieniłem jej nazwę, bo był konflikt między użytkownikiem - użytkownikiem
 *  aplikacji, a użytkownikiem - osobie o uprawnieniach użytkownika
 */

package objects;

import database.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Account {
    private String email;
    private String password;
    private String typ_konta;

    public Account(String email, String password, String typ_konta) {
        this.email = new String(email);
        this.password = new String(password);
        this.typ_konta = new String(typ_konta);
    }
    
    //sprawdza czy w bazie jest użytkownik o danym emailu i haśle
    //wynikiem jest kod (string) reprezentujący typ konta, jakie posiada
    //użytkownik o tych danych lub invalid jeżeli logowanie nie powiodło się
    public String Authenticate() throws SQLException {
    	String conditions = "email = '" + email + "' AND " + "pass = '" + password + "'";
        ResultSet rs = Session.current().selectQuery(null, "uzytkownik", conditions);
        if(rs.next())
        	return rs.getString("typ_konta");
        else
        	return new String("invalid");
    }
    
    public boolean Exists() throws SQLException {
    	String conditions = "email = '" + email + "'";
    	ResultSet rs = Session.current().selectQuery(null, "uzytkownik", conditions);
        return rs.next(); //true jeśli istnieje, flase wpp
    }
    
    //zapisuje użytkownika do bazy
    //TODO jeżeli użytkownik już istnieje powinno nadpisać
    public void Save() throws SQLException {
    	String[][] values = new String[1][3];
    	values[0] = new String[] {email, password, typ_konta};
    	if( !Exists() ) {
        	Session.current().getTable("uzytkownik").insert(null, values);
    	}
    	/*else {
    		Session.current().getTable("uzytkownik").update(null, values); - napisać
    	}*/
    }

    //zwraca listę wszystkich uzytkownikow w bazie danych
    public static List<Account> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("uzytkownik").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Account
    public static List<Account> GetAll(ResultSet rs) throws SQLException {
        List<Account> list = new Vector<Account>();
        while (rs.next())
            list.add(new Account(rs.getString("email"), rs.getString("pass"),
            				  	 rs.getString("typ_konta")));
        return list;
    }
    
    //zwraca listę wszystkich ocen użytkownika
    public List<Ocena> GetUserReviews() throws SQLException
    {
        ResultSet rs = Session.current().selectQuery(null, "ocena", "email = " + email);
        if (rs != null)
            return Ocena.GetAll(rs);
        else return null;
    }
}
