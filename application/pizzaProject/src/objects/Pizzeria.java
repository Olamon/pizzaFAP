/*
 *  klasa odzwierciedlająca pojedynczą pizzerię
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'pizzeria'
 */

package objects;

import fapDB.Session;
import fapDB.Table;

import java.sql.*;
import java.util.List;
import java.util.Vector;


public class Pizzeria {

    public int pizzeria_id;
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
        ResultSet rs = Session.current().selectQuery(null, "oferta", "pizzeria_id = " + pizzeria_id);
        if (rs != null)
            return Oferta.GetOffers(rs);
        else return null;
    }

    // zwraca pizzerię na podstawie podanego id (lub null jeśli nie ma takiej w bazie)
    public static Pizzeria GetById(int id) throws SQLException {
        ResultSet rs = Session.current().selectQuery(null, "pizzeria", "pizzeria_id = " + id);
        if (rs != null)
            return GetPizzerias(rs).get(0);
        else return null;
    }

    // zwraca listę wszystkich pizzerii w bazie danych
    public static List<Pizzeria> GetPizzerias() throws SQLException {
        Table pizzerie = Session.current().getTable("pizzeria");
        ResultSet rs = pizzerie.select(null);
        return GetPizzerias(rs);
    }

    // przekształca dany ResultSet na listę obiektów klasy Pizzeria
    private static List<Pizzeria> GetPizzerias(ResultSet rs) throws SQLException {
        List<Pizzeria> list = new Vector<Pizzeria>();

        while (rs.next())
        {
            Pizzeria p = new Pizzeria();
            p.pizzeria_id = rs.getInt("pizzeria_id");
            p.nazwa = rs.getString("nazwa");
            p.strona = rs.getString("strona");
            if (rs.getArray("telefon") != null)
                p.telefony = (String[]) rs.getArray("telefon").getArray();

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
