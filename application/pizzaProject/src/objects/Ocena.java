/*
 *  klasa odzwierciedlająca pojedynczą ocenę
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'ocena'
 */
package objects;

import fapDB.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Ocena {
    public int oc_id;
    public int podmiot;
    public String email;
    public String recenzja;
    public int gwiazdki;

    //zwraca listę wszystkich ocen w bazie danych
    public static List<Ocena> GetReviews() throws SQLException
    {
        return GetReviews(Session.current().getTable("ocena").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Ocena
    public static List<Ocena> GetReviews(ResultSet rs) throws SQLException {
        List<Ocena> list = new Vector<Ocena>();
        while (rs.next())
        {
            Ocena o = new Ocena();
            o.oc_id = rs.getInt("oc_id");
            o.podmiot = rs.getInt("podmiot");
            o.email = rs.getString("email");
            o.recenzja = rs.getString("recenzja");
            o.gwiazdki = rs.getInt("gwiazdki");
            list.add(o);
        }
        return list;
    }
}
