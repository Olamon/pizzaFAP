/*
 *  klasa odzwierciedlająca pojedynczy wpis w menu
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'menu'
 */
package objects;

import database.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Menu {
    public int pizzeria_id;
    public int pizza;
    public String nazwa;



    //zwraca listę wszystkich wpisów w menu w bazie danych
    public static List<Menu> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("menu").select(null));
    }

    // przekształca dany ResultSet na listę obiektów typu Menu
    public static List<Menu> GetAll(ResultSet rs) throws SQLException {
        List<Menu> list = new Vector<Menu>();
        while (rs.next())
        {
            Menu m = new Menu();
            m.pizzeria_id = rs.getInt("pizzeria_id");
            m.pizza = rs.getInt("pizza");
            m.nazwa = rs.getString("nazwa");
            list.add(m);
        }
        return list;
    }
}
