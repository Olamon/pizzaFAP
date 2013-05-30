/*
 *  klasa odzwierciedlająca pojedynczą ofertę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'oferta'
 */

package objects;

import fapDB.Session;
import fapDB.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Oferta {
    public int of_id;
    public int pizzeria_id;
    public float cena;
    public int rozmiar;
    public int sklad;

    //zwraca listę wszystkich ofert w bazie danych
    public static List<Oferta> GetOffers() throws SQLException
    {
        return GetOffers(Session.current().getTable("oferta").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Oferta
    public static List<Oferta> GetOffers(ResultSet rs) throws SQLException {
        List<Oferta> list = new Vector<Oferta>();

        while (rs.next())
        {
            Oferta o = new Oferta();
            o.of_id = rs.getInt("of_id");
            o.pizzeria_id = rs.getInt("pizzeria_id");
            o.cena = rs.getFloat("cena");
            o.rozmiar = rs.getInt("rozmiar");
            o.sklad = rs.getInt("sklad");
            list.add(o);
        }
        return list;
    }
}
