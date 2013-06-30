package states.can;

import java.util.Vector;

import objects.Oferta;

public interface CanSearchPizza {
	public Vector<Oferta> Oferta_GetSome(String nazwa, String nazwa_pizzerii, float cena_od, float cena_do, 
			float ocena_od, float ocena_do, int ilosc_od, int ilosc_do, int sklad);
}