package window.search;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Dimension;

import window.search.SearchWindow;
import states.can.CanModifyPizza;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PizzaEditWindow extends JFrame {
	private JPanel mainPanel;
	private JPanel nameCard;
	private JPanel ingredientsCard;
	private JPanel offerCard;

	private JButton nextButton1;
	private JButton endButton;
	private JButton previousButton3;
	private JButton previousButton2;
	private JButton nextButton2;
	
	private JTextField priceField;
	private JTextField nameField;
	
	private JCheckBox cheeseCheckBox;
	private JCheckBox hamCheckBox;
	private JCheckBox shroomsCheckBox;
	private JCheckBox cornCheckBox;
	private JCheckBox salamiCheckBox;
	private JCheckBox pineCheckBox;
	
	private JLabel titleLabel1;
	private JLabel nameLabel;
	private JLabel titleLabel3;
	private JLabel typeLabel;
	private JLabel sizeLabel;
	private JLabel priceLabel;
	private JLabel titleLabel2;
	private JLabel chooseLabel;
	
	private CardLayout cl_mainPanel;
	private JRadioButton classicRadioButton;
	private JRadioButton thickRadioButton;
	private JRadioButton thinRadioButton;
	private JRadioButton smallRadioButton;
	private JRadioButton mediumRadioButton;
	private JRadioButton bigRadioButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	
	
	public PizzaEditWindow(final SearchWindow parentWindow, final CanModifyPizza model) {
		final PizzaEditWindow that = this;
		initComponents();
		
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parentWindow.refresh();
				that.dispose();
			}
		});
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Kreator pizzałów");
		setPreferredSize(new Dimension(450, 300));
		setLocation(100, 100);
		setMinimumSize(new Dimension(450, 300));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		cl_mainPanel = new CardLayout(0, 0);
		mainPanel.setLayout(cl_mainPanel);
		
		nameCard = new JPanel();
		mainPanel.add(nameCard, "name_9129942298557");
		nameCard.setLayout(new MigLayout("", "[][grow][]", "[75][grow][grow][grow][]"));
		
		titleLabel1 = new JLabel("Kreator pizzałów");
		titleLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
		titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		nameCard.add(titleLabel1, "cell 0 0 3 1,growx");
		
		nameLabel = new JLabel("Nazwa");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nameCard.add(nameLabel, "cell 0 2,alignx trailing");
		
		nameField = new JTextField();
		nameCard.add(nameField, "cell 1 2,growx");
		nameField.setColumns(10);
		
		nextButton1 = new JButton("Dalej");
		nextButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( nameField.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Nie podano nazwy!", 
							"Błąd", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				cl_mainPanel.next(mainPanel);
			}
		});
		nameCard.add(nextButton1, "cell 2 4,alignx right,aligny bottom");
		
		ingredientsCard = new JPanel();
		mainPanel.add(ingredientsCard, "name_9254840333784");
		ingredientsCard.setLayout(new MigLayout("", "[grow][grow][grow][][]", "[75][grow][grow][grow][grow][]"));
		
		titleLabel2 = new JLabel("Kreator pizzałów");
		titleLabel2.setFont(new Font("Dialog", Font.BOLD, 24));
		titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		ingredientsCard.add(titleLabel2, "cell 0 0 5 1,growx");
		
		chooseLabel = new JLabel("Wybierz składniki:");
		chooseLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		ingredientsCard.add(chooseLabel, "cell 0 1 2 1");
		
		cheeseCheckBox = new JCheckBox("Ser");
		ingredientsCard.add(cheeseCheckBox, "cell 1 2");
		
		hamCheckBox = new JCheckBox("Szynka");
		ingredientsCard.add(hamCheckBox, "cell 2 2 2 1");
		
		shroomsCheckBox = new JCheckBox("Pieczarki");
		ingredientsCard.add(shroomsCheckBox, "cell 1 3");
		
		cornCheckBox = new JCheckBox("Kukurydza");
		ingredientsCard.add(cornCheckBox, "cell 2 3 2 1");
		
		salamiCheckBox = new JCheckBox("Salami");
		ingredientsCard.add(salamiCheckBox, "cell 1 4");
		
		pineCheckBox = new JCheckBox("Ananas");
		ingredientsCard.add(pineCheckBox, "cell 2 4 2 1");
		
		previousButton2 = new JButton("Cofnij");
		previousButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_mainPanel.previous(mainPanel);
			}
		});
		ingredientsCard.add(previousButton2, "cell 3 5,alignx right,aligny bottom");
		
		nextButton2 = new JButton("Dalej");
		nextButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_mainPanel.next(mainPanel);
			}
		});
		ingredientsCard.add(nextButton2, "cell 4 5,alignx right,aligny bottom");
		
		offerCard = new JPanel();
		mainPanel.add(offerCard, "name_9989772297332");
		offerCard.setLayout(new MigLayout("", "[][grow][grow][grow][]", "[75][grow][][grow][][grow][][]"));
		
		titleLabel3 = new JLabel("Kreator pizzałów");
		titleLabel3.setFont(new Font("Dialog", Font.BOLD, 24));
		titleLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		offerCard.add(titleLabel3, "cell 0 0 5 1,growx");
		
		typeLabel = new JLabel("Ciasto");
		typeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		offerCard.add(typeLabel, "cell 0 1");
		
		thinRadioButton = new JRadioButton("Chrupkie");
		buttonGroup.add(thinRadioButton);
		offerCard.add(thinRadioButton, "cell 1 1");
		
		classicRadioButton = new JRadioButton("Klasyczne");
		buttonGroup.add(classicRadioButton);
		offerCard.add(classicRadioButton, "cell 2 1");
		
		thickRadioButton = new JRadioButton("Grube");
		buttonGroup.add(thickRadioButton);
		offerCard.add(thickRadioButton, "cell 3 1");
		
		sizeLabel = new JLabel("Rozmiar");
		sizeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		offerCard.add(sizeLabel, "cell 0 3");
		
		smallRadioButton = new JRadioButton("Mała");
		buttonGroup_1.add(smallRadioButton);
		offerCard.add(smallRadioButton, "cell 1 3");
		
		mediumRadioButton = new JRadioButton("Średnia");
		buttonGroup_1.add(mediumRadioButton);
		offerCard.add(mediumRadioButton, "cell 2 3");
		
		bigRadioButton = new JRadioButton("Duża");
		buttonGroup_1.add(bigRadioButton);
		offerCard.add(bigRadioButton, "cell 3 3");
		
		priceLabel = new JLabel("Cena");
		priceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		offerCard.add(priceLabel, "cell 0 5");
		
		priceField = new JTextField();
		offerCard.add(priceField, "cell 1 5,growx");
		priceField.setColumns(10);
		
		previousButton3 = new JButton("Cofnij");
		previousButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_mainPanel.previous(mainPanel);
			}
		});
		offerCard.add(previousButton3, "cell 3 7,alignx right,aligny bottom");
		
		endButton = new JButton("Koniec");
		offerCard.add(endButton, "cell 4 7,alignx right,aligny bottom");
	}
	
}