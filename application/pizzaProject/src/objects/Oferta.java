/*
 *  klasa odzwierciedlająca pojedynczą ofertę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'oferta'
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

public class Oferta extends Ocenialne implements DatabaseObject<Oferta> {
    //pytając o oferty chcemy znać ich średnią ocen, ich ilość oraz nazwę pizzy (zapisaną w tabeli menu)
    //private static final String dbpath = "oferta join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza)";

	//ze względu na dużą liczbę atrybutów darowałem sobie tutaj konstrukcję
	//(poza koniecznym wywołaniem konstruktora nadrzędnego)
    public String nazwa;
    public int pizzeria_id;
    public String nazwa_pizzerii;
    public float cena;
    public int rozmiar;
    public int sklad;
    
    public Oferta(int id, int ilosc, float srednia) {
    	super(id, ilosc, srednia);
    }
    
    public static Oferta converter = new Oferta(0, 0, 0f);
    public Vector<Oferta> convert(ResultSet rs) {
    	Vector<Oferta> result = new Vector<Oferta>();
    	try {
            while (rs.next()) {
            	int id = rs.getInt("of_id");
            	float sredniaOcen = rs.getFloat("srednia");
                int iloscOcen = rs.getInt("ilosc");
                Oferta o = new Oferta(id, iloscOcen, sredniaOcen);
                o.nazwa = rs.getString("nazwa");
                o.nazwa_pizzerii = rs.getString("pizzeria_nazwa");
                o.cena = rs.getFloat("cena");
                o.rozmiar = rs.getInt("rozmiar");
                o.sklad = rs.getInt("sklad");
                
                result.add(o);
            }
    	}
    	catch (Exception ex) {
    		Logger lgr = Logger.getLogger(Oferta.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
    	}
    	return result;
    }
    /*
    //zwraca pizzerię, której dotyczy oferta
    public Pizzeria GetPizzeria() throws SQLException {
        return Pizzeria.GetById(pizzeria_id);
    }

    //TODO wyszukiwanie sparametryzowane analogicznie do pizzerii

    //zwraca listę wszystkich ofert w bazie danych
    public static List<Oferta> GetAll() throws SQLException
    {
        //return GetAll(Session.current().getTable("oferta").select(null));
        return GetAll(Session.current().selectQuery(null, dbpath, null));
    }

    */
	@Override
	public String getNazwa() {
		return this.nazwa;
	}
}
