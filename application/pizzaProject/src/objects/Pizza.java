/*
 *  klasa odzwierciedlająca pojedynczą pizzę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'pizza'
 */

package objects;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
*/

/*
 * Zmieniłem tę klasę na klasę będącą pojemnikiem na wartości pojedyńczej krotki
 * z danej tabeli, a metody dotyczące bazy przeniosłem do paczki "database"
 */

public class Pizza implements DatabaseObject<Pizza>/*extends Ocenialne implements DatabaseObject<Pizza>*/ {
    public static enum Ciasto {
        CHRUPKIE, KLASYCZNE, GRUBE
    }
    public int sklad;
    public Ciasto grubosc;
    
    public Pizza(int sklad, int gr) {
    	this.sklad = sklad;
    	this.grubosc = Pizza.getHumanReadableGrubosc(gr);
    }
    
    public static Ciasto getHumanReadableGrubosc(int gr){
    	switch(gr){
    		case 0: return Ciasto.CHRUPKIE;
    		case 1: return Ciasto.KLASYCZNE;
    		case 2: return Ciasto.GRUBE;
    	}
    	return Ciasto.KLASYCZNE; 
    }

	@Override
	public Vector<Pizza> convert(ResultSet rs) {
		Vector<Pizza> result = new Vector<Pizza>();

    	try {
    		while (rs.next()) {
    			int sklad = rs.getInt("sklad");
    			int grubosc = rs.getInt("ciasto");
            	Pizza p = new Pizza(sklad, grubosc);
                result.add(p);
            }
    	}
        catch (Exception ex) {
        	Logger lgr = Logger.getLogger(Pizzeria.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
	}

    /*
    //zwraca listę ofert dotyczących danej pizzy
    public List<Oferta> GetOffers() throws SQLException
    {
        ResultSet rs = Session.current().selectQuery(null, "oferta", "sklad = " + sklad);
        if (rs != null)
            return Oferta.GetAll(rs);
        return null;
    }

    //zwraca listę wszystkich pizz w bazie danych
    public static List<Pizza> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("pizza").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Pizza
    public static List<Pizza> GetAll(ResultSet rs) throws SQLException {
        List<Pizza> list = new Vector<Pizza>();

        while (rs.next())
        {
            Pizza p = new Pizza();
            p.sklad = rs.getInt("sklad");
            String s = rs.getString("grubosc");
            if (s=="chrupkie")
                p.grubosc = ciasto.CHRUPKIE;
            else if (s=="grube")
                p.grubosc = ciasto.GRUBE;
            else p.grubosc = ciasto.KLASYCZNE;
            list.add(p);
        }
        return list;
    }
	*/
}
