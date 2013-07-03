/*
 * Prototyp modelu użytkownika
 * Ta klasa zawiera implementację czynności jakie użyszkodnik może wykonać
 * poprzez uporczywe klikanie będąc zalogowanym jako "uzytkownik", przy czym
 * akcje dotyczące bazy są wykonywane przez UserRole.
 */

package states;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.UserRole;
import objects.Ocena;
import objects.Pizzeria;
import objects.Oferta;
import states.can.*;

public class User implements CanSearchPizzeria, CanSearchPizza {
	User() {
		try {
			//pozyskujemy uprawnienia
			role = new UserRole();
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(Login.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	public void logout() {
		if( StateManager.transition(StateManager.State.login) )
			StateManager.user_id = null;
	}
	
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
	
	public Vector<Ocena> Ocena_GetAll(int id) {
		Vector<Ocena> result = null;
		try {
			ResultSet rs = role.Ocena_GetAll(id);
			result = Ocena.converter.convert(rs);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return result;
	}

    public Ocena Ocena_GetOne(int ocenialne_id, String email) {
        Vector<Ocena> result = null;
        try {
            ResultSet rs = role.Ocena_GetOne(ocenialne_id, email);
            result = Ocena.converter.convert(rs);
        }
        catch (Exception ex) {
            Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (result == null || result.isEmpty())
            return null;
        return result.elementAt(0);
    }
	
	public Vector<Ocena> Ocena_GetAllByUser(String email) {
		Vector<Ocena> result = null;
		try {
			ResultSet rs = role.Ocena_GetAllByUser(email);
			result = Ocena.converter.convert(rs);
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
	
	public void Ocena_InsertOrUpdate(int podmiot, String email, String recenzja, int gwiazdki){
		try {
			role.Ocena_InsertOrUpdate(podmiot, email, recenzja, gwiazdki);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}



	
	public int Ocena_GetCountByUser(String email){
		int res = 0;
		try {
			res = role.Ocena_GetCountByUser(email);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return res;
	}
	
	public float Ocena_GetAverageByUser(String email){
		float res = 0;
		try {
			res = role.Ocena_GetAverageByUser(email);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return res;
	}
	
	public String Ocenialne_GetNazwa(int id){
		String res = "";
		try {
			res = role.Ocenialne_GetNazwa(id);
		}
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return res;
	}
	
	private UserRole role;
}