/*
 * Klasa będąca implementacją czynności wykonywanych przez rolę "uzytkownik" w bazie.
 * Tutaj powinny znajdować się wszystkie zapytania, jakie użytkownik o tym
 * poziomie uprawnień może wykonać za pośrednictwem programu.
 */

package database;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UserRole {
	public UserRole() throws SQLException {
		Session.setRole(Constants.PATH, 
						Constants.LOGINS.get(Constants.ROLE.user), 
						Constants.PASSWORDS.get(Constants.ROLE.user));
	}
	
    public ResultSet Pizzeria_GetAll() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
        return st.executeQuery("SELECT * FROM" + pizzeriaSelectPath);
    }
	public ResultSet Oferta_GetAll() throws SQLException {
		Statement st = Session.instance.connection.createStatement();
        return st.executeQuery("SELECT * FROM" + ofertaSelectPath);
	}
	public ResultSet Pizzeria_GetSome(String nazwa, String ulica, String telefon, 
		float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) throws SQLException {
		String prototype = "Select * FROM" + pizzeriaSelectPath + "WHERE nazwa " +
			"LIKE ? AND adres::varchar LIKE (?,'%','%')::varchar AND (telefon::varchar LIKE ?";
		if (telefon == null || telefon.length()==0)
            prototype += " OR telefon IS NULL";
        prototype += ")";
        if (iloscMin>0)
            prototype += " AND srednia BETWEEN ? and ? ";
        if (iloscMax>=0)
            prototype += " AND ilosc BETWEEN ? and ?";
        PreparedStatement psmt = Session.instance.connection.prepareStatement(prototype);

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

        return psmt.executeQuery();
	}
	
	private final String pizzeriaSelectPath = 
			" pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id) ";
	private final String ofertaSelectPath = 
			" oferta join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza) ";
}