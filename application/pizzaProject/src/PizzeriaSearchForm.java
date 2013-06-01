import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JTextField;


public class PizzeriaSearchForm extends SearchForm{
	
	public PizzeriaSearchForm(String s){
		super(s);
	}
	
	public void createForm(){
		this.setSize(300, 200);
		
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(5,2));
		
		//Pobieranie nazwy pizzerii, którą chcemy wyszukać
		Label nameLabel = new Label("Nazwa Pizzerii:");
		mainContainer.add(nameLabel);
		JTextField name = new JTextField();
		this.textFields.add(name);
		mainContainer.add(name);
		
		//Pobieranie ulicy pizzerii
		Label streetLabel = new Label("Ulica:");
		mainContainer.add(streetLabel);
		JTextField street = new JTextField();
		this.textFields.add(street);
		mainContainer.add(street);
		
		//Numer budynku
		Label streetNumberLabel = new Label("Numer:");
		mainContainer.add(streetNumberLabel);
		JTextField streetNumber = new JTextField();
		this.textFields.add(streetNumber);
		mainContainer.add(streetNumber);
		
		//Średnia ocen
		Label rateLabel = new Label("Ocena:");
		mainContainer.add(rateLabel);
		Container rateContainer = new Container();
		rateContainer.setLayout(new GridLayout(1,4));
		Label rateFromLabel = new Label("Od"), rateToLabel = new Label("Do");
		JTextField rateFrom = new JTextField(), rateTo = new JTextField();
		this.textFields.add(rateFrom);
		this.textFields.add(rateTo);
		rateContainer.add(rateFromLabel);
		rateContainer.add(rateFrom);
		rateContainer.add(rateToLabel);
		rateContainer.add(rateTo);
		mainContainer.add(rateContainer);
		
		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new SearchForm.SearchActionListener());
		mainContainer.add(submit);
	}
}
