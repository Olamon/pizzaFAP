/*
 * "Nasłuchiwacz" zaznaczenia elementu na liście
 * Po wyborze pizzerii ustawia odpowiednie JLabele
 * w oknie przekazywanym w konstruktorze zgodnie
 * z wartościami wybranej pizzerii
 * TODO zrobić analogiczny dla pizzy
 */
package window.search;

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
    		window.pizzeriaAdres.setText(selected.adres);
    		window.ileOcen.setText(""+selected.ileOcen);
    		window.sredniaOcen.setText(""+selected.sredniaOcen);
    	}
    }
}