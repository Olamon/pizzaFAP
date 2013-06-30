/*
 * Prototyp modelu właściciela
 */

package states;

import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import objects.Oferta;
import objects.Pizzeria;
import database.OwnerRole;
import states.can.*;

public class Owner implements CanSearchPizzeria, CanSearchPizza, CanInsertPizzeria {
	Owner() {
		try {
			//pozyskujemy uprawnienia
			role = new OwnerRole();
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Owner.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	public boolean Pizzeria_insert(String nazwa, String adres, String strona, String telefon) {
		try {
			return role.Pizzeria_insert(nazwa, adres, strona, telefon) > 0? true : false;
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Owner.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
            return false;
		}
	}
	
	//tymczasowo Copy-Pasta kodu dla użytkownika
	public Vector<Pizzeria> Pizzeria_GetAll() {
		Vector<Pizzeria> result = null;
		try {
			ResultSet rs = role.Pizzeria_GetAll();
			result = Pizzeria.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	public Vector<Oferta> Oferta_GetAll() {
		Vector<Oferta> result = null;
		try {
			ResultSet rs = role.Oferta_GetAll();
			result = Oferta.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	public Vector<Pizzeria> Pizzeria_GetSome(String nazwa, String ulica, String telefon, 
			float ocenaMin, float ocenaMax, int iloscMin, int iloscMax) {
		Vector<Pizzeria> result = null;
		try {
			ResultSet rs = role.Pizzeria_GetSome(nazwa, ulica, telefon, ocenaMin,
				ocenaMax, iloscMin, iloscMax);
			result = Pizzeria.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	//kolejna tymczasowa Copy-Pasta
	public Vector<Oferta> Oferta_GetSome(String nazwa, String nazwa_pizzerii, float cena_od, float cena_do, 
			float ocena_od, float ocena_do, int ilosc_od, int ilosc_do, int sklad) {
		Vector<Oferta> result = null;
		try {
			ResultSet rs = role.Oferta_GetSome(nazwa, nazwa_pizzerii, cena_od, cena_do, ocena_od, ocena_do, ilosc_od, ilosc_do, sklad);
			result = Oferta.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}
	
	private OwnerRole role;
}