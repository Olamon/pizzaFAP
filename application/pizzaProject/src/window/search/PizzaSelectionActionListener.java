/*
 * "Nasłuchiwacz" zaznaczenia elementu na liście pizz
 */
package window.search;

import java.util.Vector;

import javax.swing.event.*;
import javax.swing.JList;

import objects.Oferta;
import objects.Pizza;
import objects.Pizzeria;

class PizzaSelectionActionListener implements ListSelectionListener {
	private SearchWindow window;
	
	public PizzaSelectionActionListener(SearchWindow window) {
		this.window = window;
	}
    public void valueChanged(ListSelectionEvent e) {
    	JList list = (JList) e.getSource();
    	
    	if( !list.isSelectionEmpty() ) {
    		Oferta selected = (Oferta) list.getSelectedValue();
    		window.pizzaNazwa.setText(selected.nazwa);
    		window.pizzaPizzeria.setText(""+selected.nazwa_pizzerii);
    		window.pizzaOcena.setText(""+selected.sredniaOcen);
    		window.pizzaLiczbaOcen.setText(""+selected.iloscOcen);
    		window.pizzaCena.setText(""+selected.cena);
    		Vector<String> temp = IngredientsHelper.getIngredients(selected.sklad);
    		window.pizzaSklad.setListData(temp);
    	}
    }
}
