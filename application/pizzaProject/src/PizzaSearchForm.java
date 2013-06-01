import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JTextField;

public class PizzaSearchForm extends SearchForm{
	
	public PizzaSearchForm(String s){
		super(s);
	}
	
	public void createForm(){
		this.setSize(300,200);
		
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(6,2));
		
		//Pobieranie nazwy pizzy, którą chcemy wyszukać
		Label nameLabel = new Label("Nazwa Pizzy:");
		mainContainer.add(nameLabel);
		JTextField name = new JTextField();
		this.textFields.add(name);
		mainContainer.add(name);
		
		//Pobieranie nazwy pizzeri
		Label pizzeriaLabel = new Label("Nazwa Pizzerii:");
		mainContainer.add(pizzeriaLabel);
		JTextField pizzeria = new JTextField();
		this.textFields.add(pizzeria);
		mainContainer.add(pizzeria);
		
		//Cena
		Label priceLabel = new Label("Cena:");
		mainContainer.add(priceLabel);
		Container priceContainer = new Container();
		priceContainer.setLayout(new GridLayout(1,4));
		Label priceFromLabel = new Label("Od"), priceToLabel = new Label("Do");
		JTextField priceFrom = new JTextField(), priceTo = new JTextField();
		this.textFields.add(priceFrom);
		this.textFields.add(priceTo);
		priceContainer.add(priceFromLabel);
		priceContainer.add(priceFrom);
		priceContainer.add(priceToLabel);
		priceContainer.add(priceTo);
		mainContainer.add(priceContainer);
		
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
		
		//Liczba ocen
		Label rateCountLabel = new Label("Liczba ocen:");
		mainContainer.add(rateCountLabel);
		Container rateCountContainer = new Container();
		rateCountContainer.setLayout(new GridLayout(1,4));
		Label rateCountFromLabel = new Label("Od"), rateCountToLabel = new Label("Do");
		JTextField rateCountFrom = new JTextField(), rateCountTo = new JTextField();
		this.textFields.add(rateCountFrom);
		this.textFields.add(rateCountTo);
		rateCountContainer.add(rateCountFromLabel);
		rateCountContainer.add(rateCountFrom);
		rateCountContainer.add(rateCountToLabel);
		rateCountContainer.add(rateCountTo);
		mainContainer.add(rateCountContainer);
		
		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new SearchForm.SearchActionListener());
		mainContainer.add(submit);
	}
}
