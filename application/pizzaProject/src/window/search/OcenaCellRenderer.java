/*
 * Prosty "view" do listy pizzy
 */
package window.search;

import objects.Ocena;

import javax.swing.*;

import java.awt.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

class OcenaCellRenderer extends JPanel implements ListCellRenderer {
	
	JLabel gwiazdki, autor;
	JTextArea recenzja;
	
	public OcenaCellRenderer() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setSize(300,100);
		setLayout(new MigLayout("", "[50px,grow][150px,grow]", "[30px,grow][30px,grow][30px,grow]"));
		JLabel autorLabel = new JLabel("Autor:");
    	this.add(autorLabel, "cell 0 0,alignx left,growy");
    	autor = new JLabel("");
    	this.add(autor, "cell 1 0,grow");
    	JLabel gwiazdkiLabel = new JLabel("Ocena:");
    	this.add(gwiazdkiLabel, "cell 0 1,alignx left,growy");
    	gwiazdki = new JLabel("");
    	this.add(gwiazdki, "cell 1 1,grow");
    	JLabel recenzjaLabel = new JLabel("Recenzja:");
    	this.add(recenzjaLabel, "cell 0 2,grow");
    	recenzja = new JTextArea("");
    	recenzja.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
    	recenzja.setLineWrap(true);
    	recenzja.setWrapStyleWord(true);
    	recenzja.setBorder(UIManager.getBorder("List.noFocusBorder"));
    	this.add(recenzja, "cell 1 2,grow");
	}
    public Component getListCellRendererComponent(
        JList list,
		Object value,
		int index,
		boolean isSelected,
		boolean hasFocus)
    {
        if(value instanceof Ocena) {
        	autor.setText(((Ocena) value).email);
        	gwiazdki.setText(""+((Ocena) value).gwiazdki);
        	recenzja.setText(((Ocena) value).recenzja);
        }
        return this;
    }
}

