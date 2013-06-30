package window.search;

import static utils.StringUtils.asFloat;
import static utils.StringUtils.asInteger;

import java.awt.Button;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import objects.Oferta;
import objects.Pizzeria;

import states.User;

public class PizzaSearchForm extends SearchForm{
	private User model;
	
	public PizzaSearchForm(String s, SearchWindow parent, User model){
		super(s, parent);
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,200);
		//teraz createForm to część konstrukcji i nie jest wywoływane z zewnątrz
		createForm();
	}
	
	private void createForm(){
		this.setSize(300,250);
		
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(8,2));
		
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
		
		//Skład
		JCheckBox cheese = new JCheckBox("Ser"), ham = new JCheckBox("Szynka"), mashrooms = new JCheckBox("Pieczarki"), corn = new JCheckBox("Kukurydza");
		cheese.setFont(new Font(Font.SANS_SERIF, 0, 12));
		ham.setFont(new Font(Font.SANS_SERIF, 0, 12));
		mashrooms.setFont(new Font(Font.SANS_SERIF, 0, 12));
		corn.setFont(new Font(Font.SANS_SERIF, 0, 12));
		this.checkBoxes.add(cheese);
		this.checkBoxes.add(ham);
		this.checkBoxes.add(mashrooms);
		this.checkBoxes.add(corn);
		mainContainer.add(cheese);
		mainContainer.add(ham);
		mainContainer.add(mashrooms);
		mainContainer.add(corn);
		
		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new PizzaSearchActionListener(parent));
		mainContainer.add(submit);
	}
	
	public class PizzaSearchActionListener implements ActionListener{
        private SearchWindow parent;

        public PizzaSearchActionListener(SearchWindow parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Vector<Oferta> pizze = null;
            float cena_od = asFloat(textFields.get(2).getText(), 0);
            float cena_do = asFloat(textFields.get(3).getText(), 0);
            float ocena_od = asFloat(textFields.get(4).getText(), 0);
            float ocena_do = asFloat(textFields.get(5).getText(), 0);
            int imi = asInteger(textFields.get(6).getText(), 0);
            int ima = asInteger(textFields.get(7).getText(), -1);
            boolean[] ingr = {checkBoxes.get(0).isSelected(), checkBoxes.get(1).isSelected(), checkBoxes.get(2).isSelected(), checkBoxes.get(3).isSelected()}; 
            /*pizze =*/ model.Oferta_GetSome(textFields.get(0).getText(), textFields.get(1).getText(), cena_od, cena_do, ocena_od, ocena_do, imi,ima, IngredientsHelper.translateToInt(ingr));
            //parent.pizzaList.setListData(pizze);
        }
    }
}
