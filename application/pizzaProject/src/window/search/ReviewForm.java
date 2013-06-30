package window.search;

import java.awt.Button;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;

import javax.jws.WebParam.Mode;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import states.StateManager;
import states.User;
import states.can.CanSearchPizza;
import window.search.PizzaSearchForm.PizzaSearchActionListener;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewForm extends JFrame{
	private User model;
	private SearchWindow parent;
	private JTextArea recenzja;
	private int podmiot;
	private JSlider gwiazdki;
	
	public ReviewForm(String s, SearchWindow parent, User model, int podmiot){
		super(s);
		this.parent = parent;
		this.recenzja = new JTextArea();
		this.gwiazdki = new JSlider(0,5,0);
		this.podmiot = podmiot;
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,200);
		createForm();
	}
	
	private void createForm(){
		this.setSize(300,250);
		
		Container mainContainer  = this.getContentPane();
		getContentPane().setLayout(new MigLayout("", "[298px]", "[27px][27px][27px][27px,grow][27px]"));
		
		//Slider z oceną
		Label nameLabel = new Label("Ocena:");
		mainContainer.add(nameLabel, "cell 0 0,grow");
		gwiazdki.setMinorTickSpacing(1);
		gwiazdki.setMajorTickSpacing(1);
		gwiazdki.setPaintLabels(true);
		gwiazdki.setPaintTicks(true);
		mainContainer.add(gwiazdki, "cell 0 1,grow");
		
		//Pobieranie nazwy pizzeri
		Label pizzeriaLabel = new Label("Recenzja:");
		mainContainer.add(pizzeriaLabel, "cell 0 2,grow");
		mainContainer.add(recenzja, "cell 0 3,grow");
		
		//Przycisk oceń
		Button submit = new Button("Oceń");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.Ocena_Insert(podmiot, StateManager.user_id, recenzja.getText(), gwiazdki.getValue());
				dispose();
			}
		});
		//submit.addActionListener(new PizzaSearchActionListener(parent));
		mainContainer.add(submit, "cell 0 4,grow");
	}
}
