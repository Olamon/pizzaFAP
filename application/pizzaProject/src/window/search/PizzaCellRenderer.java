/*
 * Prosty "view" do listy pizzy
 */
package window.search;

import objects.Oferta;
import objects.Pizzeria;

import javax.swing.*;
import java.awt.*;

class PizzaCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
        JList list,
		Object value,
		int index,
		boolean isSelected,
		boolean hasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        if(value instanceof Oferta) {
        	setText(((Oferta) value).nazwa);
        }
        return this;
    }
}

