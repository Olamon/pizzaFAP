/*
 * "Nasłuchiwacz" zaznaczenia elementu na liście
 * Po wyborze pizzerii ustawia odpowiednie JLabele
 * w oknie przekazywanym w konstruktorze zgodnie
 * z wartościami wybranej pizzerii
 * TODO zrobić analogiczny dla pizzy
 */

import javax.swing.event.*;
import javax.swing.JList;
import objects.Pizzeria;

class PizzeriaSelectionListener implements ListSelectionListener {
	private SearchWindow window;
	
	public PizzeriaSelectionListener(SearchWindow window) {
		this.window = window;
	}
    public void valueChanged(ListSelectionEvent e) {
    	JList list = (JList) e.getSource();
    	
    	if( !list.isSelectionEmpty() ) {
    		Pizzeria selected = (Pizzeria) list.getSelectedValue();
    		window.pizzeriaNazwa.setText(selected.nazwa);
    		window.pizzeriaAdres.setText(selected.ulica + " " 
    								   + selected.nr_budynku + "/"
    								   + selected.nr_lokalu);
    	}
    }
}