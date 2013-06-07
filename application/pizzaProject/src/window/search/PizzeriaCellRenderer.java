/*
 * Prosty "view" do listy pizzerii
 *TODO zrobić analogiczny dla pizzy
 */
package window.search;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;
import objects.Pizzeria;

class PizzeriaCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(
        JList list,
		Object value,
		int index,
		boolean isSelected,
		boolean hasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        if(value instanceof Pizzeria) {
        	setText(((Pizzeria) value).nazwa);
        }
        return this;
    }
}

