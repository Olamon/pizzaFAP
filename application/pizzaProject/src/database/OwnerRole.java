/*
 * Klasa będąca implementacją czynności wykonywanych przez rolę "wlasciciel" w bazie.
 * Tutaj powinny znajdować się wszystkie zapytania, jakie użytkownik o tym
 * poziomie uprawnień może wykonać za pośrednictwem programu.
 */

package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class OwnerRole {
	public OwnerRole() throws SQLException {
		Session.setRole(Constants.PATH, 
						Constants.LOGINS.get(Constants.ROLE.owner), 
						Constants.PASSWORDS.get(Constants.ROLE.owner));
	}
	
	//zwraca wartość nowego klucza wstawionego do tabeli Ocenialne
	private int Ocenialne_insert() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
		st.executeUpdate("INSERT INTO ocenialne VALUES ()", Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys();
		if ( rs.next() ) {
		    return rs.getInt(1);
		} else {
			throw new SQLException();
		}
	}
	
	public int Pizzeria_insert(String nazwa, String adres, String strona) throws SQLException {
		int id = Ocenialne_insert();
		PreparedStatement st = Session.instance.connection.prepareStatement(
			"INSERT INTO pizzeria(pizzeria_id, nazwa, adres, strona) VALUES (?,?,?,?)"
		);
		st.setInt(1, id);
		st.setString(2, nazwa);
		st.setString(3, adres);
		st.setString(4, strona);
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
	public ResultSet Pizzeria_GetSome(String nazwa, String adres, String telefon, 
		float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) throws SQLException {
		String prototype = "Select * FROM" + pizzeriaSelectPath + "WHERE nazwa " +
			"LIKE ? AND adres LIKE ? AND (telefon::varchar LIKE ?";
		if (telefon == null || telefon.length()==0)
            prototype += " OR telefon IS NULL";
        prototype += ")";
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
	
	private final String pizzeriaSelectPath = 
			" pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id) ";
	private final String ofertaSelectPath = 
			" oferta join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza) ";
}