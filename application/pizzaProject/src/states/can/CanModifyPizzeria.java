package states.can;

public interface CanModifyPizzeria {
	public boolean Pizzeria_insert(String nazwa, String adres, String strona, String telefon, String[] godziny);
	public boolean Pizzeria_update(int id, String nazwa, String adres, String strona, String telefon, String[] godziny);
}