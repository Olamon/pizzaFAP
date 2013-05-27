import fapDB.Session;

public class Run {
	public static void main(String args[]){
		//przed wykonaniem jakichkolwiek czynności w bazie trzeba zainicjować sesję!
		Session.login("jdbc:postgresql://localhost/pizzadb", "uzytkownikrole", "uzytkownikrole");
		UserMenu userMenu = new UserMenu();
		userMenu.show();
	}
}
