package states.can;

public interface CanModifyPizza {
	public boolean Pizza_insert(String nazwa, int pizzeria_id, int sklad, 
			String ciasto, String rozmiar, double cena);
	//public boolean Pizza_update();
}