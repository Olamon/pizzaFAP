/*
 * Klasa będąca implementacją czynności wykonywanych przez rolę "wlasciciel" w bazie.
 * Tutaj powinny znajdować się wszystkie zapytania, jakie użytkownik o tym
 * poziomie uprawnień może wykonać za pośrednictwem programu.
 */

package database;

import static utils.StringUtils.asFloat;
import static utils.StringUtils.asInteger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Array;

import states.StateManager;

public class OwnerRole {
	public OwnerRole() throws SQLException {
		Session.setRole(Constants.PATH, 
						Constants.LOGINS.get(Constants.ROLE.owner), 
						Constants.PASSWORDS.get(Constants.ROLE.owner));
	}
	
	//zwraca wartość nowego klucza wstawionego do tabeli Ocenialne
	private int Ocenialne_insert() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
		st.executeUpdate("INSERT INTO ocenialne DEFAULT VALUES", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if ( rs.next() ) {
		    return rs.getInt(1);
		} else {
			throw new SQLException();
		}
	}
	
	public int Oferta_insert(String nazwa, int pizzeria_id, int sklad, 
			String ciasto, String rozmiar, double cena) throws SQLException {
		//!!!!!!!!! błąd w bazie - primary key na skład uniemożliwia dodanie tej samej
		//!!!!!!!!! pizzy w różnych wariantach grubości ciasta!
		ResultSet doesExist = Oferta_GetSome(null, null, 0, 0, 0, 0, 0, -1, sklad);
		if(doesExist.next())
			return 0;
		
		
		PreparedStatement pizzaInsert = Session.instance.connection.prepareStatement(
			"INSERT INTO pizza(sklad, ciasto) VALUES (?,?)"
		);
		pizzaInsert.setInt(1, sklad);
		pizzaInsert.setString(2, ciasto);
		pizzaInsert.executeUpdate();
		
		int id = Ocenialne_insert();
		
		PreparedStatement ofertaInsert = Session.instance.connection.prepareStatement(
			"INSERT INTO oferta(of_id, sklad, pizzeria_id, cena, rozmiar) VALUES (?,?,?,?,?)"
		);
		ofertaInsert.setInt(1, id);
		ofertaInsert.setInt(2, sklad);
		ofertaInsert.setInt(3, pizzeria_id);
		ofertaInsert.setDouble(4, cena);
		
		//tymczasowo...
		int rozmiarHardCode = getSize(rozmiar);
		
		ofertaInsert.setInt(5, rozmiarHardCode);
		ofertaInsert.executeUpdate();
		
		PreparedStatement menuInsert = Session.instance.connection.prepareStatement(
			"INSERT INTO menu(pizzeria_id, pizza, nazwa) VALUES (?,?,?)"
		);
		menuInsert.setInt(1, pizzeria_id);
		menuInsert.setInt(2, sklad);
		menuInsert.setString(3, nazwa);
		menuInsert.executeUpdate();
		
		return 1;
	}
	
	public int Pizzeria_insert(String nazwa, String adres, String strona, String telefon,
			String[] godziny) throws SQLException {
		int id = Ocenialne_insert();
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"INSERT INTO pizzeria(pizzeria_id, nazwa, adres, strona, telefon, godziny, wlasciciel) VALUES (?,?,?,?,?,?,?)"
		);
		st.setInt(1, id);
		st.setString(2, nazwa);
		st.setString(3, adres);
		st.setString(4, strona);
		st.setString(5, telefon);
		Array godzArr = Session.instance.connection.createArrayOf("varchar", godziny);
		st.setArray(6, godzArr);
		st.setString(7, StateManager.user_id);
		return st.executeUpdate();
	}
	
	public int Pizzeria_update(int id, String nazwa, String adres, String strona, String telefon,
			String[] godziny) throws SQLException {
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"UPDATE pizzeria SET nazwa=?, adres=?, strona=?, telefon=?, godziny=? WHERE pizzeria_id=?"
		);
		st.setString(1, nazwa);
		st.setString(2, adres);
		st.setString(3, strona);
		st.setString(4, telefon);
		Array godzArr = Session.instance.connection.createArrayOf("varchar", godziny);
		st.setArray(5, godzArr);
		st.setInt(6, id);
		return st.executeUpdate();
	}
	
	//tymczasowo Copy-Pasta kodu dla użytkownika
	public ResultSet Pizzeria_GetAll() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
        return st.executeQuery("SELECT * FROM" + pizzeriaSelectPath);
    }
	public ResultSet Oferta_GetAll() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
        return st.executeQuery("SELECT * FROM" + ofertaSelectPath);
	}
	public ResultSet Oferta_GetByOwner(String wlasciciel) throws SQLException{
		String prototype = "Select * FROM " + ofertaSelectPath + "WHERE wlasciciel = ?";
		PreparedStatement psmt = Session.instance.connection.prepareStatement(prototype);
		psmt.setString(1, wlasciciel);
		return psmt.executeQuery();
	}
	public ResultSet Pizzeria_GetByOwner(String wlasciciel) throws SQLException{
		String prototype = "Select * FROM " + pizzeriaSelectPath + "WHERE wlasciciel = ?";
		PreparedStatement psmt = Session.instance.connection.prepareStatement(prototype);
		psmt.setString(1, wlasciciel);
		return psmt.executeQuery();
	}
	public ResultSet Pizzeria_GetSome(String nazwa, String adres, String telefon, 
		float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) throws SQLException {
		String prototype = "Select * FROM" + pizzeriaSelectPath + "WHERE nazwa " +
			"LIKE ? AND adres LIKE ? AND telefon LIKE ?";
        if (iloscMin>0)
            prototype += " AND srednia BETWEEN ? and ? ";
        if (iloscMax>=0)
            prototype += " AND ilosc BETWEEN ? and ?";
        PreparedStatement psmt = Session.instance.connection.prepareStatement(prototype);

        psmt.setString(1, nazwa!=null?"%"+nazwa+"%":"%");
        psmt.setString(2, adres!=null?"%"+adres+"%":"%");
        psmt.setString(3, telefon!=null?"%"+telefon+"%":"%");
        if (iloscMin>0) {
            psmt.setObject(4, ocenaMin);
            psmt.setObject(5, ocenaMax);
        }
        if (iloscMax >= 0)    {
            psmt.setInt(iloscMin>0?6:4, iloscMin);
            psmt.setInt(iloscMin>0?7:5, iloscMax);
        }

        return psmt.executeQuery();
	}
	
	//tymczasowa Copy-Pasta
	public ResultSet Oferta_GetSome(String nazwa, String nazwa_pizzerii, float cena_od, float cena_do, float ocena_od, float ocena_do, int ilosc_od, int ilosc_do, int sklad) throws SQLException {
		String prototype = "Select * FROM" + ofertaSelectPath + "WHERE 0 = 0 ";
		
		if(nazwa!=null && nazwa.length() != 0){
			prototype += "AND nazwa LIKE ? ";
		}
		
		if(nazwa_pizzerii!=null && nazwa_pizzerii.length() != 0){
			prototype += "AND pizzeria_nazwa LIKE ? ";
		}
		
		if(cena_od < 0) cena_od = 0;
		if(cena_do > 0.0){
			prototype += "AND cena BETWEEN ? AND ? ";
		} else {
			prototype += "AND cena >= ? ";
		}
		
		if(ocena_od < 0) ocena_od = 0;
		if(ocena_do > 0.0){
			if(ocena_od == 0){
				prototype += "AND (srednia IS NULL OR srednia BETWEEN ? AND ?) ";
			} else {
				prototype += "AND srednia IS NOT NULL AND srednia BETWEEN ? AND ? ";
			}
		} else {
			if(ocena_od == 0){
				prototype += "AND (srednia IS NULL OR srednia >= ?) ";
			} else {
				prototype += "AND srednia IS NOT NULL AND srednia >= ? ";
			}
		}
		
		if(ilosc_od < 0) ilosc_od = 0;
		if(ilosc_do > 0.0){
			prototype += "AND ilosc BETWEEN ? AND ? ";
		} else {
			prototype += "AND ilosc >= ? ";
		}
		
		if(sklad > 0){
			prototype += "AND sklad = ? ";
		}
		
		System.out.println(prototype);
		int ile_danych = 0;
		PreparedStatement psmt = Session.instance.connection.prepareStatement(prototype);
		
		if(nazwa!=null && nazwa.length() != 0) {
			psmt.setString(ile_danych+1,"%"+nazwa+"%");
			ile_danych++;
		}
		if(nazwa_pizzerii!=null && nazwa_pizzerii.length() != 0){
			psmt.setString(ile_danych+1, "%"+nazwa_pizzerii+"%");
			ile_danych++;
		}
		if(cena_do > 0.0) {
			psmt.setObject(ile_danych+1, cena_od);
			ile_danych++;
			psmt.setObject(ile_danych+1, cena_do);
			ile_danych++;
		} else {
			psmt.setObject(ile_danych+1, cena_od);
			ile_danych++;
		}
		if(ocena_do > 0.0) {
			psmt.setObject(ile_danych+1, ocena_od);
			ile_danych++;
			psmt.setObject(ile_danych+1, ocena_do);
			ile_danych++;
		} else {
			psmt.setObject(ile_danych+1, ocena_od);
			ile_danych++;
		}
		if(ilosc_do > 0.0) {
			psmt.setInt(ile_danych+1, ilosc_od);
			ile_danych++;
			psmt.setInt(ile_danych+1, ilosc_do);
			ile_danych++;
		} else {
			psmt.setInt(ile_danych+1, ilosc_od);
			ile_danych++;
		}
		if(sklad > 0){
			psmt.setInt(ile_danych+1, sklad);
			ile_danych++;
		}
		
		return psmt.executeQuery();
	}
	
	//tymczasowo
	private int getSize(String size) {
		if(size.equals("Mała")) {
			return 20;
		} else if(size.equals("Srednia")) {
			return 30;
		} else
			return 40;
	}
	
	private final String pizzeriaSelectPath = 
			" pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id) ";
	private final String ofertaSelectPath = 
			" oferta left join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza)" +
			" join (SELECT pizzeria_id, nazwa AS pizzeria_nazwa, wlasciciel FROM pizzeria) P on (oferta.pizzeria_id = P.pizzeria_id) ";
}