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
	
	private final String pizzeriaSelectPath = 
			" pizzeria join ocenialneView on (pizzeria.pizzeria_id = ocenialneView.id) ";
	private final String ofertaSelectPath = 
			" oferta left join ocenialneView on (oferta.of_id = ocenialneView.id) join menu on (oferta.sklad = menu.pizza)" +
			" join (SELECT pizzeria_id, nazwa AS pizzeria_nazwa FROM pizzeria) P on (oferta.pizzeria_id = P.pizzeria_id) ";
}