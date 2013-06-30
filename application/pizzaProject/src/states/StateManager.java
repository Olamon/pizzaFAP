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

package states;

import javax.swing.JFrame;
import window.login.LoginWindow;
import window.search.UserMenu;
import window.search.OwnerMenu;

public class StateManager {
	static class State {
		//możliwe stany aplikacji
		static State user = new State();
		static State owner = new State();
		static State admin = new State();
		static State login = new State();
		static State invalid = new State();
		
		private State() {}
	}
	
	//stan początkowy to login
	public static void init() {
		transition(State.login);
	}
	public static boolean transition(State state) {	
		if(state == State.invalid || state == instance.current)
			return false; //do nothing
		//zamykamy okienko starego stanu
		if(instance.view != null)
			instance.view.setVisible(false);
		
		if(state == State.login) {
			instance.view = new LoginWindow(new Login());
		} else if(state == State.user) {
			instance.view = new UserMenu(new User());
		} else if(state == State.owner) {
			instance.view = new OwnerMenu(new Owner());
		}
		
		instance.current = state;
		instance.view.setVisible(true);
		return true;
	}
	
	public static void setUserId(String email){
		StateManager.user_id = email;
	}
	
	private static final StateManager instance = new StateManager();

	private StateManager() { }
	private JFrame view;
	private State current;
	public static String user_id;
}