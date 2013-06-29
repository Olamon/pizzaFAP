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
import javax.swing.SwingConstants;

public class SearchWindow extends JFrame {
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
	
	//wygenerowany kod (tym razem czytelnejszy!)
	private void initComponents() {
		setTitle("Wyszukaj");
		setPreferredSize(new Dimension(800, 600));
		setLocation(100, 100);
		setMinimumSize(new Dimension(400, 300));
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
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		pizzeriaAdres = new JLabel("Adres");
		pizzeriaDetails.add(pizzeriaAdres, "2, 2");
		
		
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
		pizzaPanel.add(pizzaDetails, "cell 1 1,grow");
		pizzaDetails.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		pizzaPizzeria = new JLabel("pizzaPizzeria");
		pizzaDetails.add(pizzaPizzeria, "2, 2");
		
		pizzaCena = new JLabel("pizzaCena");
		pizzaDetails.add(pizzaCena, "2, 4");
		
		pizzaSklad = new JLabel("pizzaSklad");
		pizzaDetails.add(pizzaSklad, "2, 6");
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
	private JPanel pizzeriaDetails;
	private JPanel pizzaPanel;
	private JScrollPane pizzaScrollPane;
	JPanel pizzaDetails;
	JLabel pizzaPizzeria;
	JLabel pizzaCena;
	JLabel pizzaSklad;
	
}