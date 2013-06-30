package window.search;

import static utils.StringUtils.asFloat;
import static utils.StringUtils.asInteger;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;

import objects.Pizzeria;
import states.can.*;

public class PizzeriaSearchForm extends SearchForm{
	private CanSearchPizzeria model;

	public PizzeriaSearchForm(String s, SearchWindow parent, CanSearchPizzeria model){
		super(s, parent);
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,200);
		//teraz createForm to część konstrukcji i nie jest wywoływane z zewnątrz
		createForm();
	}
	
	private void createForm(){
		this.setSize(300, 200);
		
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(6,2));
		
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
		submit.addActionListener(new PizzeriaSearchActionListener(parent));
		mainContainer.add(submit);
	}
	
    public class PizzeriaSearchActionListener implements ActionListener{
        private SearchWindow parent;

        public PizzeriaSearchActionListener(SearchWindow parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Vector<Pizzeria> pizzerie = null;
            float omi = asFloat(textFields.get(3).getText(), 0);
            float oma = asFloat(textFields.get(4).getText(), 100);
            int imi = asInteger(textFields.get(5).getText(), 0);
            int ima = asInteger(textFields.get(6).getText(), -1);
            pizzerie = model.Pizzeria_GetSome(textFields.get(0).getText(), textFields.get(1).getText(), textFields.get(2).getText(),omi,oma,imi,ima);
            parent.pizzeriaList.setListData(pizzerie);
        }
    }
}
