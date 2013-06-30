/*
 *  klasa odzwierciedlająca pojedynczą pizzerię
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'pizzeria'
 */

package objects;

/*
import java.util.List;
*/
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Zmieniłem tę klasę na klasę będącą pojemnikiem na wartości pojedyńczej krotki
 * z danej tabeli, a metody dotyczące bazy przeniosłem do paczki "database"
 */

public class Pizzeria extends Ocenialne implements DatabaseObject<Pizzeria> {
    //pytając o pizzerie chcemy znać ich średnią ocen i ich ilość, toteż sama tabela pizzeria nie wystarczy
    //private static final String dbpath = "pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id)";

    public String nazwa;
    public String adres;
    public String strona;
    public String[] telefony;
    public String[] godzOtwarcia;
    public String[] godzZamkniecia;

    //ze względu na dużą liczbę atrybutów darowałem sobie tutaj konstrukcję
  	//(poza koniecznym wywołaniem konstruktora nadrzędnego)
    public Pizzeria(int id, int ilosc, float srednia) {
    	super(id, ilosc, srednia);
    }
    
    public static Pizzeria converter = new Pizzeria(0, 0, 0f);
    public Vector<Pizzeria> convert(ResultSet rs) {
    	Vector<Pizzeria> result = new Vector<Pizzeria>();

    	try {
    		while (rs.next()) {
    			int id = rs.getInt("pizzeria_id");
    			float srednia = rs.getFloat("srednia");
                int ilosc = rs.getInt("ilosc");
    			Pizzeria p = new Pizzeria(id, ilosc, srednia);
            	p.nazwa = rs.getString("nazwa");
            	p.strona = rs.getString("strona");
            	p.telefony = null;
            	if (rs.getArray("telefon") != null)
            		p.telefony = (String[]) rs.getArray("telefon").getArray();
                // Wczytywanie typów złożonych z SQL-a na pewno można jakoś ładniej zrobić niż poniżej, ale mi się nie udało
                p.adres = rs.getString("adres");
                p.godzOtwarcia = null;
                p.godzZamkniecia = null;
                if (rs.getString("godziny")!=null) {
                    String[] dni = rs.getString("godziny").split("\",\"");
                    p.godzOtwarcia = new String[dni.length];
                	p.godzZamkniecia = new String[dni.length];
                    for(int i=0; i<dni.length;i++)
                    {
                        String[] dzien = dni[i].replaceAll("[{}\"()]","").split(",");
                        if (dzien.length>1) {
                            int d = Integer.parseInt(dzien[0]);
                            p.godzOtwarcia[d] = dzien[1];
                            p.godzZamkniecia[d] = dzien[2];
                        }
                    }
                }

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
    // zwraca listę wszystkich ofert pizzerii
    public List<Oferta> GetAllOffers() throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, "oferta", "pizzeria_id = " + id);
        if (rs != null)
            return Oferta.GetAll(rs);
        return null;
    }

    // zwraca pizzerię na podstawie podanego id (lub null jeśli nie ma takiej w bazie)
    public static Pizzeria GetById(int id) throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, dbpath, "pizzeria_id = " + id);
        if (rs != null)
            return GetAll(rs).get(0);
        else return null;
    }

    // zwraca listę wszystkich pizzerii w bazie danych
    public static List<Pizzeria> GetAll() throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, dbpath, null);
        return GetAll(rs);
    }

    //zwraca wynik wyszukiwania według podanych parametrów
    public static List<Pizzeria> GetAll(String nazwa, String ulica, String telefon, float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) throws SQLException {

        String prepString = "SELECT * from " + dbpath + " WHERE nazwa LIKE ? AND adres::varchar LIKE (?,'%','%')::varchar AND (telefon::varchar LIKE ?";
        if (telefon == null || telefon.length()==0)
            prepString += " OR telefon IS NULL";
        prepString += ")";
        if (iloscMin>0)
            prepString += " AND srednia BETWEEN ? and ? ";
        if (iloscMax>=0)
            prepString += " AND ilosc BETWEEN ? and ?";
        PreparedStatement psmt = Session.current().connection.prepareStatement(prepString);

        psmt.setString(1, nazwa!=null?"%"+nazwa+"%":"%");
        psmt.setString(2, ulica!=null?"%"+ulica+"%":"%");
        psmt.setString(3, telefon!=null?"%"+telefon+"%":"%");
        if (iloscMin>0) {
            psmt.setObject(4, ocenaMin);
            psmt.setObject(5, ocenaMax);
        }
        if (iloscMax >= 0)    {
            psmt.setInt(iloscMin>0?6:4, iloscMin);
            psmt.setInt(iloscMin>0?7:5, iloscMax);
        }

        ResultSet rs = psmt.executeQuery();
        if (rs != null)
            return GetAll(rs);
        return null;
    }

    */
}
