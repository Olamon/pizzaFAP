/*
 * Ten interfejs określa stany, które mogą wyszukiwać pizzerie, to jest potrzebne
 * do utworzenia PizzeriaSearchForm.
 * Fajnie byłoby zrobić template CanSearch<Klasa>, ale tego chyba nie da się
 * prosto wykonać, bo np. potem gdybyśmy chcieli mieć klasę, która
 * CanSearch<Pizzeria> i CanSearch<Pizza>, to java nie pozwoli, bo type erasure,
 * cokolwiek to jest.
 * Poza tym jest problem natury takiej, że argumenty wyszukiwania są bardzo
 * różne dla różnych klas. Gdyby jednak ktoś miał pomysł jak to zrobić lepiej
 * to chętnie zobaczę.
 */

package states.can;

import java.util.Vector;

import objects.Pizzeria;

public interface CanSearchPizzeria {
	public Vector<Pizzeria> Pizzeria_GetSome(String nazwa, String ulica, String telefon, 
			float ocenaMin, float ocenaMax, int iloscMin, int iloscMax);
}