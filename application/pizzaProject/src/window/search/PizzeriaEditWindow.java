/*
 * Prototyp okienka do dodawania i edytowania pizzerii
 */

package window.search;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

public class PizzeriaEditWindow extends JFrame {
	private JPanel mainPanel;
	private JPanel basicsCard;
	private JButton nextButton1;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel titleLabel1;
	private JPanel hoursCard;
	private JLabel titleLabel2;
	private JButton nextButton2;
	private JLabel dayLabel;
	private JComboBox dayComboBox;
	private JLabel fromLabel;
	private JTextField fromField;
	private JLabel toLabel;
	private JTextField toField;
	private JCheckBox openingHoursCheckbox;
	private JLabel openingHoursLabel;
	private JButton previousButton2;
	private JPanel contactCard;
	private JLabel titleLabel3;
	private JLabel siteLabel;
	private JLabel phoneLabel;
	private JTextField siteField;
	private JTextField phoneField;
	private JButton endButton;
	private JButton previousButton3;
	
	static String[] days = new String[] { "Poniedziałek", "Wtorek", "Środa",
		"Czwartek", "Piątek", "Sobota", "Niedziela" };
	private JLabel adresLabel;
	private JTextField adresField;;
	
	public PizzeriaEditWindow() {
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Kreator pizzeriów");
		setPreferredSize(new Dimension(450, 300));
		setLocation(100, 100);
		setMinimumSize(new Dimension(450, 300));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		final CardLayout cl_mainPanel = new CardLayout(0, 0);
		mainPanel.setLayout(cl_mainPanel);
		
		basicsCard = new JPanel();
		mainPanel.add(basicsCard, "name_5161336780321");
		basicsCard.setLayout(new MigLayout("", "[][grow][]", "[75][25px,grow][grow][]"));
		
		titleLabel1 = new JLabel("Kreator pizzeriów");
		titleLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
		titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		basicsCard.add(titleLabel1, "cell 0 0 3 1,grow");
		
		nameLabel = new JLabel("Nazwa");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		basicsCard.add(nameLabel, "cell 0 1,alignx right,aligny center");
		
		nextButton1 = new JButton("Dalej");
		nextButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_mainPanel.next(mainPanel);
			}
		});
		
		nameField = new JTextField();
		basicsCard.add(nameField, "cell 1 1,growx,aligny center");
		nameField.setColumns(10);
		
		adresLabel = new JLabel("Adres");
		adresLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		basicsCard.add(adresLabel, "cell 0 2,alignx trailing");
		
		adresField = new JTextField();
		basicsCard.add(adresField, "cell 1 2,growx");
		adresField.setColumns(10);
		basicsCard.add(nextButton1, "cell 2 3,alignx right,aligny bottom");
		
		hoursCard = new JPanel();
		mainPanel.add(hoursCard, "name_6314720138332");
		hoursCard.setLayout(new MigLayout("", "[][grow][][]", "[75][grow][grow][grow][grow][]"));
		
		titleLabel2 = new JLabel("Kreator pizzeriów");
		titleLabel2.setFont(new Font("Dialog", Font.BOLD, 24));
		hoursCard.add(titleLabel2, "cell 0 0 4 1,alignx center,aligny center");
		
		openingHoursLabel = new JLabel("Godziny otwarcia");
		openingHoursLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		hoursCard.add(openingHoursLabel, "cell 0 1");
		
		openingHoursCheckbox = new JCheckBox("Te same godziny codziennie");
		hoursCard.add(openingHoursCheckbox, "cell 1 1 3 1");
		
		dayLabel = new JLabel("Dzień");
		dayLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		hoursCard.add(dayLabel, "cell 0 2,alignx left");
		
		dayComboBox = new JComboBox();
		hoursCard.add(dayComboBox, "cell 1 2,growx");
		dayComboBox.setModel(new DefaultComboBoxModel(days));
		
		fromLabel = new JLabel("Od");
		fromLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		hoursCard.add(fromLabel, "cell 0 3,alignx left");
		
		fromField = new JTextField();
		hoursCard.add(fromField, "cell 1 3,growx");
		fromField.setColumns(10);
		
		toLabel = new JLabel("Do");
		toLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		hoursCard.add(toLabel, "cell 0 4,alignx left");
		
		toField = new JTextField();
		hoursCard.add(toField, "cell 1 4,growx");
		toField.setColumns(10);
		
		previousButton2 = new JButton("Cofnij");
		previousButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_mainPanel.previous(mainPanel);
			}
		});
		hoursCard.add(previousButton2, "cell 2 5");
		
		nextButton2 = new JButton("Dalej");
		nextButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_mainPanel.next(mainPanel);
			}
		});
		hoursCard.add(nextButton2, "cell 3 5,alignx right,aligny bottom");
		
		contactCard = new JPanel();
		mainPanel.add(contactCard, "name_7774263886040");
		contactCard.setLayout(new MigLayout("", "[][grow][][]", "[75][grow][grow][]"));
		
		titleLabel3 = new JLabel("Kreator pizzeriów");
		titleLabel3.setFont(new Font("Dialog", Font.BOLD, 24));
		contactCard.add(titleLabel3, "cell 0 0 4 1,alignx center,aligny center");
		
		siteLabel = new JLabel("Strona");
		siteLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contactCard.add(siteLabel, "cell 0 1,alignx trailing");
		
		siteField = new JTextField();
		contactCard.add(siteField, "cell 1 1,growx,aligny center");
		siteField.setColumns(10);
		
		phoneLabel = new JLabel("Telefon");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		contactCard.add(phoneLabel, "cell 0 2,alignx trailing");
		
		phoneField = new JTextField();
		contactCard.add(phoneField, "cell 1 2,growx,aligny center");
		phoneField.setColumns(10);
		
		previousButton3 = new JButton("Cofnij");
		previousButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_mainPanel.previous(mainPanel);
			}
		});
		contactCard.add(previousButton3, "cell 2 3,aligny bottom");
		
		endButton = new JButton("Koniec");
		contactCard.add(endButton, "cell 3 3,alignx right,aligny bottom");
	}
}
