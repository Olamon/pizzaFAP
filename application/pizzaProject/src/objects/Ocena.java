/*
 *  klasa odzwierciedlająca pojedynczą ocenę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'ocena'
 */
package objects;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Zmieniłem tę klasę na klasę będącą pojemnikiem na wartości pojedyńczej krotki
 * z danej tabeli, a metody dotyczące bazy przeniosłem do paczki "database"
 */

public class Ocena implements DatabaseObject<Ocena> {
    public int oc_id;
    public int podmiot;
    public String email;
    public String recenzja;
    public int gwiazdki;
    
    public Ocena(int oc_id, int podmiot, String email, String recenzja, int gwiazdki) {
    	this.oc_id = oc_id;
    	this.podmiot = podmiot;
    	this.email = new String(email);
    	this.recenzja = new String(recenzja);
    	this.gwiazdki = gwiazdki;
    }
    
    public static Ocena converter = new Ocena(0, 0, "", "", 0);
    public Vector<Ocena> convert(ResultSet rs) {
    	Vector<Ocena> result = new Vector<Ocena>();
    	try {
    		while(rs.next()) {
        		result.add(new Ocena(rs.getInt("oc_id"), 
        							 rs.getInt("podmiot"),
        							 rs.getString("email"),
        							 rs.getString("recenzja"),
        							 rs.getInt("gwiazdki")));
        	}
    	}
    	catch (Exception ex) {
			Logger lgr = Logger.getLogger(Ocena.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
    	return result;
    }

    /*
    //zwraca listę wszystkich ocen w bazie danych
    public static List<Ocena> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("ocena").select(null));
    }
    */
}
