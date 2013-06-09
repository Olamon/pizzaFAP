/*
 *  klasa odzwierciedlająca pojedynczą pizzerię
 *  każdy obiekt tej klasy odpowiada pojedynczemu wierszowi w tabeli 'pizzeria'
 */

package objects;

import database.Session;
import database.Table;

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
        int i = 0;
        StringBuilder sb = new StringBuilder();
        if (nazwa != null)
        {
            sb.append("nazwa LIKE '%").append(nazwa).append("%'");
            i++;
        }
        if (ulica != null)
        {
            if (i>0) sb.append(" AND ");
            sb.append("adres::varchar LIKE ('%").append(ulica).append("%','%','%')::varchar");
            i++;
        }
        if (telefon != null && telefon.length()>0)
        {
            if (i>0) sb.append(" AND ");
            sb.append("telefon::varchar LIKE '%").append(telefon).append("%'");
            i++;
        }
        if (iloscMin > 0) {
            if (i>0) sb.append(" AND ");
            sb.append("srednia between ").append(ocenaMin).append(" and ").append(ocenaMax);
            i++;
        }
        if (iloscMax >= 0) {
            if (i>0) sb.append(" AND ");
            sb.append("ilosc between ").append(iloscMin).append(" and " ).append(iloscMax);
        }
        ResultSet rs = Session.current().selectQuery(null, dbpath, sb.toString());
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
