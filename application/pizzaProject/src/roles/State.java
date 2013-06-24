/*
 * Klasa reprezentująca "stan" aplikacji.
 * Generalnie program może znajdować się w kilku stanach:
 * - login, user, owner, (TODO admin)
 * Każdy taki stan to tak właściwie inny podprogram: inne okienka, inne akcje
 * do wykonania. Ponadto stany te mogą się zmieniać (na razie login->coś innego).
 * Żeby to wszystko ładnie wyglądało, wprowadziłem tę klasę.
 * Klasa tak naprawdę potrafi jedną rzecz, mianowicie przejść z jednego stanu
 * do drugiego. Takie przejście to jednocześnie utworzenie odpowiedniego modelu
 * stanu i jego okienka. Żeby zmienić stan należy przekazać funkcji transition
 * odpowiednią Rolę (stan) docelową
 */

package roles;

import javax.swing.JFrame;
import window.login.LoginWindow;
import window.menu.UserMenu;
import window.menu.OwnerMenu;

public class State {
	//stan początkowy to login
	public static void init() {
		transition(Role.login);
	}
	public static boolean transition(Role role) {	
		if(role == Role.invalid || role == instance.current)
			return false; //do nothing
		//zamykamy okienko starego stanu
		if(instance.view != null)
			instance.view.setVisible(false);
		
		if(role == Role.login) {
			instance.view = new LoginWindow(new Login());
		} else if(role == Role.user) {
			instance.view = new UserMenu(new User());
		} else if(role == Role.owner) {
			instance.view = new OwnerMenu(new Owner());
		}
		
		instance.current = role;
		instance.view.setVisible(true);
		return true;
	}
	
	private static final State instance = new State();;

	private State() { }
	private JFrame view;
	private Role current;
}