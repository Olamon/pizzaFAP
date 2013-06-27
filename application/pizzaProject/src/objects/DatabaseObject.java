package objects;

import java.sql.ResultSet;
import java.util.Vector;

/* 
 * wszystkie klasy implemetujące ten interfejs potrafią zamienić ResultSet
 * na wektor swoich instancji. Problem jest taki, że nie wiem jak obejść
 * to, że Java nie pozwala na statyczne abstrakcyjne funkcje ani na statyczne
 * funkcje w interfejsach, więc każda klasa musi mieć też swoją specjalną
 * statyczną instancję (konwerter), która istnieje tylko w celu wywoływania 
 * poniższej funkcji
 */
public interface DatabaseObject<T extends DatabaseObject<T>> {
	Vector<T> convert(ResultSet rs);
}