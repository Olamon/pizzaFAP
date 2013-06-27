/*
 *  klasa odzwierciedlająca pojedynczy wpis w menu
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'menu'
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

public class Menu implements DatabaseObject<Menu> {
    public int pizzeria_id;
    public int pizza;
    public String nazwa;
    
    public Menu(int pizzeria_id, int pizza, String nazwa) {
    	this.pizzeria_id = pizzeria_id;
    	this.pizza = pizza;
    	this.nazwa = new String(nazwa);
    }

    public static Menu converter = new Menu(0, 0, "");
    public Vector<Menu> convert(ResultSet rs) {
    	Vector<Menu> result = new Vector<Menu>();
    	try {
    		while(rs.next()) {
        		result.add(new Menu(rs.getInt("pizzeria_id"), 
        							rs.getInt("pizza"),
        							rs.getString("nazwa")));
        	}
    	}
    	catch (Exception ex) {
			Logger lgr = Logger.getLogger(Menu.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
    	return result;
    }
    /*
    //zwraca listę wszystkich wpisów w menu w bazie danych
    public static List<Menu> GetAll() throws SQLException
    {
        return GetAll(Session.current().getTable("menu").select(null));
    }
    */
}
