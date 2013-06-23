/*
* Okienko do wyszukiwania pizzy i pizzerii
*/
package window.search;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;

import objects.Oferta;
import objects.Pizzeria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchWindow extends JFrame {
	public SearchWindow() {
		super();
		initComponents();

		Vector<Pizzeria> queryRes = null;
        Vector<Oferta> oferty = null;
		try {      
	        queryRes = new Vector<Pizzeria>( Pizzeria.GetAll() );
            oferty = new Vector<Oferta>(Oferta.GetAll());
		} catch (SQLException ex) {
			//TODO poprawić obsługę i logowanie wyjątków
			Logger lgr = Logger.getLogger(SearchWindow.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            System.exit(1);
		}
		
		pizzeriaList.setListData(queryRes);
		pizzeriaList.setCellRenderer(new PizzeriaCellRenderer());
        pizzaList.setListData(oferty);
        pizzaList.setCellRenderer(new PizzaCellRenderer());
		pizzeriaList.addListSelectionListener(new PizzeriaSelectionListener(this));
		//TODO obsługa listy pizz
	}
	
	//wygenerowany kod (tym razem czytelnejszy!)
	private void initComponents() {
		setTitle("Wyszukaj");
		setPreferredSize(new Dimension(800, 600));
		setLocation(100, 100);
		setMinimumSize(new Dimension(400, 300));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		//do przekazywania do ActionListenerów
		final SearchWindow that = this;
		
		menu = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(menu);
		
		pizzeriaPanel = new JPanel();
		pizzeriaPanel.setBorder(null);
		menu.addTab("Pizzeria", null, pizzeriaPanel, null);
		pizzeriaPanel.setLayout(new MigLayout("", "[160px][160px,grow]", "[25px][203px,grow]"));
		
		pizzeriaAdvancedSearchButton = new JButton("Zaawansowane");
		pizzeriaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzeriaSearchForm pizzeriaSearchForm = 
						new PizzeriaSearchForm("Wyszukaj pizzerię", that);
				pizzeriaSearchForm.setVisible(true);
			}
		});
		pizzeriaPanel.add(pizzeriaAdvancedSearchButton, "cell 0 0,growx,aligny top");
		
		pizzeriaNazwa = new JLabel("Pizzeria");
		pizzeriaPanel.add(pizzeriaNazwa, "cell 1 0,alignx center");
		
		pizzeriaScrollPane = new JScrollPane();
		pizzeriaPanel.add(pizzeriaScrollPane, "cell 0 1,grow");
		
		pizzeriaList = new JList();
		pizzeriaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pizzeriaScrollPane.setViewportView(pizzeriaList);
		
		pizzeriaDetails = new JPanel();
		pizzeriaDetails.setBorder(new LineBorder(Color.GRAY, 1, true));
		pizzeriaPanel.add(pizzeriaDetails, "cell 1 1,grow");
		pizzeriaDetails.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		pizzeriaAdres = new JLabel("Adres");
		pizzeriaDetails.add(pizzeriaAdres, "2, 2");
		
		
		pizzaPanel = new JPanel();
		pizzaPanel.setBorder(null);
		menu.addTab("Pizza", null, pizzaPanel, null);
		pizzaPanel.setLayout(new MigLayout("", "[160px][160px,grow]", "[25px][203px,grow]"));
		
		pizzaAdvancedSearchButton = new JButton("Zaawansowane");
		pizzaAdvancedSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PizzaSearchForm pizzaSearchForm = 
						new PizzaSearchForm("Wyszukaj pizzę");
				pizzaSearchForm.setVisible(true);
			}
		});
		pizzaPanel.add(pizzaAdvancedSearchButton, "cell 0 0,growx,aligny top");
		
		pizzaNazwa = new JLabel("Pizza");
		pizzaPanel.add(pizzaNazwa, "cell 1 0,alignx center");
		
		pizzaScrollPane = new JScrollPane();
		pizzaPanel.add(pizzaScrollPane, "cell 0 1,grow");
		
		pizzaList = new JList();
		pizzaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pizzaScrollPane.setViewportView(pizzaList);
		
		pizzaDetails = new JPanel();
		pizzaDetails.setBorder(new LineBorder(Color.GRAY, 1, true));
		pizzaPanel.add(pizzaDetails, "cell 1 1,grow");
		pizzaDetails.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		pizzaAdres = new JLabel("Adres");
		pizzaDetails.add(pizzaAdres, "2, 2");
	}
	
	//te pola są modyfikowane przez listenery i renderery
	JList pizzeriaList;
	JList pizzaList;
	JLabel pizzeriaNazwa;
	JLabel pizzeriaAdres;
	JLabel pizzaNazwa;
	JLabel pizzaAdres;
	
	//a te nie
	private JTabbedPane menu;
	private JPanel pizzeriaPanel;
	private JButton pizzeriaAdvancedSearchButton;
	private JScrollPane pizzeriaScrollPane;
	private JPanel pizzeriaDetails;
	private JPanel pizzaPanel;
	private JButton pizzaAdvancedSearchButton;
	private JScrollPane pizzaScrollPane;
	private JPanel pizzaDetails;
	
}