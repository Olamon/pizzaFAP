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

import javax.swing.JToolBar;
import javax.swing.UIManager;

//UWAGA teraz SearchWindow jest abstract (należy tworzyć UserMenu albo OwnerMenu
public abstract class SearchWindow extends JFrame {
	public SearchWindow() {
		
		initComponents();
		
		pizzeriaList.setCellRenderer(new PizzeriaCellRenderer());
		pizzeriaToolBar = new JToolBar();
		pizzeriaPanel.add(pizzeriaToolBar, "cell 1 1 1 2,growx");
        pizzaList.setCellRenderer(new PizzaCellRenderer());
        
        pizzaToolBar = new JToolBar();
        pizzaPanel.add(pizzaToolBar, "cell 1 2,growx");
		pizzeriaList.addListSelectionListener(new PizzeriaSelectionListener(this));
		pizzaList.addListSelectionListener(new PizzaSelectionActionListener(this));
	}
	
	//odświeża listy
	public abstract void refresh();
	
	//wygenerowany kod (tym razem czytelnejszy!)
	private void initComponents() {
		setTitle("Wyszukaj");
		setPreferredSize(new Dimension(800, 600));
		setLocation(100, 100);
		setMinimumSize(new Dimension(450, 300));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		menu = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(menu);
		
		pizzeriaPanel = new JPanel();
		pizzeriaPanel.setBorder(null);
		menu.addTab("Pizzeria", null, pizzeriaPanel, null);
		pizzeriaPanel.setLayout(new MigLayout("", "[160px][160px,grow]", "[25px][203px,grow]"));
		
		pizzeriaAdvancedSearchButton = new JButton("Zaawansowane");
		pizzeriaPanel.add(pizzeriaAdvancedSearchButton, "cell 0 0,growx,aligny top");
		
		pizzeriaNazwa = new JLabel("Pizzeria");
		pizzeriaPanel.add(pizzeriaNazwa, "cell 1 0,alignx center");
		
		pizzeriaScrollPane = new JScrollPane();
		pizzeriaPanel.add(pizzeriaScrollPane, "flowy,cell 0 1,grow");
		
		pizzeriaList = new JList();
		pizzeriaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pizzeriaScrollPane.setViewportView(pizzeriaList);
		
		pizzeriaDetails = new JPanel();
		pizzeriaDetails.setBorder(new LineBorder(Color.GRAY, 1, true));
		pizzeriaPanel.add(pizzeriaDetails, "flowy,cell 1 1,grow");
		pizzeriaDetails.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		noMatter = new JLabel("Adres:");
		pizzeriaDetails.add(noMatter, "2, 2");
		
		pizzeriaAdres = new JLabel("");
		pizzeriaDetails.add(pizzeriaAdres, "4, 4, center, center");
		
		lblIlo = new JLabel("Ilość:");
		pizzeriaDetails.add(lblIlo, "2, 6");
		
		ileOcen = new JLabel("");
		pizzeriaDetails.add(ileOcen, "4, 6");
		
		lblrednia = new JLabel("Średnia:");
		pizzeriaDetails.add(lblrednia, "2, 8");
		
		sredniaOcen = new JLabel("");
		pizzeriaDetails.add(sredniaOcen, "4, 8");
		
		
		pizzaPanel = new JPanel();
		pizzaPanel.setBorder(null);
		menu.addTab("Pizza", null, pizzaPanel, null);
		pizzaPanel.setLayout(new MigLayout("", "[160px][160px,grow]", "[25px][203px,grow][]"));
		
		pizzaAdvancedSearchButton = new JButton("Zaawansowane");
		pizzaPanel.add(pizzaAdvancedSearchButton, "cell 0 0,growx,aligny top");
		
		pizzaNazwa = new JLabel("Pizza");
		pizzaPanel.add(pizzaNazwa, "cell 1 0,alignx center");
		
		pizzaScrollPane = new JScrollPane();
		pizzaPanel.add(pizzaScrollPane, "cell 0 1 1 2,grow");
		
		pizzaList = new JList();
		pizzaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pizzaScrollPane.setViewportView(pizzaList);
		
		pizzaDetails = new JPanel();
		pizzaDetails.setBorder(new LineBorder(Color.GRAY, 1, true));
		pizzaPanel.add(pizzaDetails, "cell 1 1,alignx center,growy");
		
		lblPizzeria = new JLabel("Pizzeria:");
		
		pizzaPizzeria = new JLabel("");
		
		lblCena = new JLabel("Cena:");
		
		pizzaCena = new JLabel("");
		
		lblOcena = new JLabel("Ocena:");
		
		pizzaOcena = new JLabel("");
		
		lblLiczbaOcen = new JLabel("Liczba ocen:");
		
		pizzaLiczbaOcen = new JLabel("");
		
		lblSkad = new JLabel("Skład:");
		
		pizzaSklad = new JList();
		pizzaSklad.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		pizzaSklad.setSize(new Dimension(119, 0));
		pizzaDetails.setLayout(new MigLayout("", "[119px][119px]", "[15px,grow][15px,grow][15px,grow][15px,grow][15px,grow]"));
		pizzaDetails.add(lblPizzeria, "cell 0 0,growx,aligny top");
		pizzaDetails.add(pizzaPizzeria, "cell 1 0,growx,aligny top");
		pizzaDetails.add(lblCena, "cell 0 1,growx,aligny top");
		pizzaDetails.add(pizzaCena, "cell 1 1,growx,aligny top");
		pizzaDetails.add(lblOcena, "cell 0 2,growx,aligny top");
		pizzaDetails.add(pizzaOcena, "cell 1 2,growx,aligny top");
		pizzaDetails.add(lblLiczbaOcen, "cell 0 3,growx,aligny top");
		pizzaDetails.add(pizzaLiczbaOcen, "cell 1 3,alignx left,aligny top");
		pizzaDetails.add(lblSkad, "cell 0 4,growx,aligny top");
		pizzaDetails.add(pizzaSklad, "cell 1 4,grow");
	}
	
	//te pola są modyfikowane przez listenery i renderery
	JList pizzeriaList;
	JList pizzaList;
	public JLabel pizzeriaNazwa;
	public JLabel pizzeriaAdres;
	JLabel pizzaNazwa;
	
	protected JButton pizzeriaAdvancedSearchButton;
	protected JButton pizzaAdvancedSearchButton;
	protected JToolBar pizzeriaToolBar;
	protected JToolBar pizzaToolBar;
	
	//a te nie
	private JTabbedPane menu;
	private JPanel pizzeriaPanel;
	private JScrollPane pizzeriaScrollPane;
	public JPanel pizzeriaDetails;
	private JPanel pizzaPanel;
	private JScrollPane pizzaScrollPane;
	public JPanel pizzaDetails;
	JLabel pizzaPizzeria;
	JLabel pizzaCena;
	private JLabel lblPizzeria;
	private JLabel lblCena;
	private JLabel lblSkad;
	private JLabel lblOcena;
	private JLabel lblLiczbaOcen;
	JLabel pizzaOcena;
	JLabel pizzaLiczbaOcen;
	JList pizzaSklad;
	private JLabel noMatter;
	private JLabel lblIlo;
	private JLabel lblrednia;
	JLabel ileOcen;
	JLabel sredniaOcen;
}