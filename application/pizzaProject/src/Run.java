import states.StateManager;

public class Run {
	public static void main(String args[]){
		//aplikacja działa w "stanach", obecnie są 3:
		//- login, user, owner (TODO admin)
		//na początku inicjalizujemy stan programu (domyślnie do loginu)
		StateManager.init();
	}
}
