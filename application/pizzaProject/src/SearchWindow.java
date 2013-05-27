/*
 * Okienko do wyszukiwania pizzy i pizzerii
 */

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JList;

import utils.StringUtils;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

import fapDB.*;

public class SearchWindow {
	
	private JFrame frame;
	
	public SearchWindow() {
		frame = new JFrame("Wyszukaj");
	}
	
	public void show() {
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setMinimumSize(new Dimension(300,300));
		Container mainContainer = frame.getContentPane();
		
		JTabbedPane pane = new JTabbedPane();
		
		Table pizzeria = null;
		try{
			//tworzy pośredni obiekt Table, który umożliwia wykonywanie zapytań na tabeli
			//(na razie tylko selecty)
			pizzeria = Session.current().getTable("pizzeria");
		} catch (SQLException|IllegalStateException|IllegalArgumentException ex) {
			//TODO poprawić obsługę i logowanie wyjątków
			Logger lgr = Logger.getLogger(SearchWindow.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.exit(1);
		}
		
		Vector<String> queryRes = null;
		try {      
			//SELECT nazwa FROM pizzeria;
			//domyślnie select zwraca ResultSet, więc za pomocą pomocniczej funkcji
			//formatujemy do wektora stringów
	        queryRes = StringUtils.RowsToStrings( pizzeria.select(new String[]{"nazwa"}) );
		} catch (SQLException ex) {
			//TODO poprawić obsługę i logowanie wyjątków
			Logger lgr = Logger.getLogger(SearchWindow.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.exit(1);
		}
		
		JList<String> pizzeriaList = new JList<String>(queryRes);
		
		JPanel panel1 = new JPanel();
		panel1.add(pizzeriaList);
		
		JPanel panel2 = new JPanel();
		// TODO - lista dla panelu pizza
		
		pane.addTab("Pizzeria", panel1);
		pane.addTab("Pizza", panel2);
		
		mainContainer.add(pane);
		frame.setVisible(true);
	}
}
