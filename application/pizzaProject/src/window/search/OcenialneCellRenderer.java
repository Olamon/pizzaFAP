package window.search;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import objects.Ocena;
import objects.Ocenialne;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

public class OcenialneCellRenderer extends JPanel implements ListCellRenderer{
	JLabel sredniaOcen, iloscOcen, nazwaPodmiotu;
	
	public Component getListCellRendererComponent(
	        JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean hasFocus)
	    {
	        if(value instanceof Ocenialne) {
	        	nazwaPodmiotu.setText(((Ocenialne) value).getNazwa());
	        	sredniaOcen.setText(""+((Ocenialne) value).sredniaOcen);
	        	iloscOcen.setText(""+((Ocenialne) value).iloscOcen);
	        }
	        return this;
	    }
	
	public OcenialneCellRenderer(){
		super();
		setMinimumSize(new Dimension(0, 0));
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.setSize(300,76);
		setLayout(new MigLayout("", "[100px,grow][50px,grow][100px,grow][50px,grow]", "[30px,grow][30px,grow]"));
		
		JLabel lblNazwa = new JLabel("Nazwa:");
		add(lblNazwa, "cell 0 0");
		
		nazwaPodmiotu = new JLabel("");
		add(nazwaPodmiotu, "cell 2 0");
		
		JLabel lblIloOcen = new JLabel("Ilość ocen:");
		add(lblIloOcen, "cell 0 1");
		
		iloscOcen = new JLabel("");
		add(iloscOcen, "cell 1 1");
		
		JLabel lblrednia = new JLabel("Średnia:");
		add(lblrednia, "cell 2 1");
		
		sredniaOcen = new JLabel("");
		add(sredniaOcen, "cell 3 1");
	}

}
