/*
 *  klasa odzwierciedlająca pojedynczą ofertę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'oferta'
 */

package objects;

import database.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Oferta extends Ocenialne{
    public int pizzeria_id;
    public float cena;
    public int rozmiar;
    public int sklad;

    //zwraca pizzerię, której dotyczy oferta
    public Pizzeria GetPizzeria() throws SQLException {
        return Pizzeria.GetById(pizzeria_id);
    }

    //zwraca listę wszystkich ofert w bazie danych
    public static List<Oferta> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("oferta").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Oferta
    public static List<Oferta> GetAll(ResultSet rs) throws SQLException {
        List<Oferta> list = new Vector<Oferta>();

        while (rs.next())
        {
            Oferta o = new Oferta();
            o.id = rs.getInt("of_id");
            o.pizzeria_id = rs.getInt("pizzeria_id");
            o.cena = rs.getFloat("cena");
            o.rozmiar = rs.getInt("rozmiar");
            o.sklad = rs.getInt("sklad");
            list.add(o);
        }
        return list;
    }
}
