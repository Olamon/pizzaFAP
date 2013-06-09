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
    //pytając o oferty chcemy znać ich średnią ocen, ich ilość oraz nazwę pizzy (zapisaną w tabeli menu)
    private static final String dbpath = "oferta join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza)";

    public String nazwa;
    public int pizzeria_id;
    public float cena;
    public int rozmiar;
    public int sklad;

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

    // przekształca dany ResultSet na listę obiektów typu Oferta
    public static List<Oferta> GetAll(ResultSet rs) throws SQLException {
        List<Oferta> list = new Vector<Oferta>();

        while (rs.next())
        {
            Oferta o = new Oferta();
            o.nazwa = rs.getString("nazwa");
            o.id = rs.getInt("of_id");
            o.pizzeria_id = rs.getInt("pizzeria_id");
            o.cena = rs.getFloat("cena");
            o.rozmiar = rs.getInt("rozmiar");
            o.sklad = rs.getInt("sklad");
            o.sredniaOcen = rs.getFloat("srednia");
            o.iloscOcen = rs.getInt("ilosc");
            list.add(o);
        }
        return list;
    }
}
