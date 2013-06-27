/*
 * Klasa ze stałymi programu - takimi jak loginy i hasła do ról, ścieżka do bazy itp.
 * Używanie tego jest troszeczkę bardziej pracochłonne niż wklepywanie z palca,
 * ale to dobra praktyka.
 */
package database;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Constants {
	public static class ROLE {
		public static final ROLE user = new ROLE("uzytkownik");
		public static final ROLE owner = new ROLE("wlasciciel");
		public static final ROLE admin = new ROLE("admin");
		public static final ROLE login = new ROLE("login");
		public static final ROLE invalid = new ROLE("invalid");
		
		public String toString() {
			return new String(name);
		}
		
		private ROLE(String name) {
			this.name = new String(name);
		}
		private String name;
	}
	
	
	
	public static final String PATH = "jdbc:postgresql://localhost/pizzadb";
	public static final Map<ROLE, String> LOGINS;
	public static final Map<ROLE, String> PASSWORDS;
	
	static {
		Map<ROLE, String> temp = new HashMap<>();
		temp.put(ROLE.login, "loginrole");
		temp.put(ROLE.user, "uzytkownikrole");
		temp.put(ROLE.owner, "wlascicielrole");
		LOGINS = Collections.unmodifiableMap(temp);
	}
	
	static {
		Map<ROLE, String> temp = new HashMap<>();
		temp.put(ROLE.login, "loginrole");
		temp.put(ROLE.user, "uzytkownikrole");
		temp.put(ROLE.owner, "wlascicielrole");
		PASSWORDS = Collections.unmodifiableMap(temp);
	}
			
	private Constants() { }
}