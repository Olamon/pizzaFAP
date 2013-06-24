/*
 *  klasa odzwierciedlająca pojedynczą pizzerię
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'pizzeria'
 */

package objects;

import database.Session;

import java.sql.*;
import java.util.List;
import java.util.Vector;


public class Pizzeria extends Ocenialne{
    //pytając o pizzerie chcemy znać ich średnią ocen i ich ilość, toteż sama tabela pizzeria nie wystarczy
    private static final String dbpath = "pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id)";

    public String nazwa;

    public String ulica;
    public int nr_budynku;
    public int nr_lokalu;

    public String strona;
    public String[] telefony;
    public String[] godzOtwarcia;
    public String[] godzZamkniecia;


    public Pizzeria()
    {
        godzOtwarcia = new String[6];
        godzZamkniecia = new String[6];
    }

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

    // przekształca dany ResultSet na listę obiektów klasy Pizzeria
    public static List<Pizzeria> GetAll(ResultSet rs) throws SQLException {
        List<Pizzeria> list = new Vector<Pizzeria>();

        while (rs.next())
        {
            Pizzeria p = new Pizzeria();
            p.id = rs.getInt("pizzeria_id");
            p.nazwa = rs.getString("nazwa");
            p.strona = rs.getString("strona");
            if (rs.getArray("telefon") != null)
                p.telefony = (String[]) rs.getArray("telefon").getArray();
            p.sredniaOcen = rs.getFloat("srednia");
            p.iloscOcen = rs.getInt("ilosc");

            // Wczytywanie typów złożonych z SQL-a na pewno można jakoś ładniej zrobić niż poniżej, ale mi się nie udało
            String[] addr = rs.getString("adres").replaceAll("[()\"]", "").split(",");
            p.ulica = addr[0];
            p.nr_budynku = Integer.parseInt(addr[1]);
            p.nr_lokalu = Integer.parseInt(addr[2]);

            if (rs.getString("godziny")!=null) {
                String[] dni = rs.getString("godziny").split("\",\"");
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
            list.add(p);
        }
        return list;
    }
}
