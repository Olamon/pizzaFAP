/*
 * Role (stany) aplikacji, generalnie pokrywają się z tymi z bazy.
 * Tworzenie nowych nie jest możliwe z aplikacji.
 * Służą one do przechodzenia między stanami aplikacji przez funkcję State.transition
 */

package roles;

public class Role {
	static Role user = new Role();
	static Role owner = new Role();
	static Role admin = new Role();
	static Role login = new Role();
	static Role invalid = new Role();
	
	private Role() {}
	
	//możemy wybrać rolę podając stringa, ale to chyba zły pomysł, raczej to zmienię
	static Role StringToRole(String str) {
		if(str == null)
			return invalid;
		else if(str.equals("uzytkownik"))
			return user;
		else if(str.equals("wlasciciel"))
			return owner;
		else if(str.equals("admin"))
			return admin;
		else if(str.equals("login"))
			return login;
		else if(str.equals("invalid"))
			return invalid;
		else
			return invalid;	//na wszelki wypadek opcja ostateczna
	}
}