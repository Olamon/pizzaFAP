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

import states.can.*;
import objects.Oferta;
import objects.Pizzeria;

import states.User;

public class PizzaSearchForm extends SearchForm{
	private CanSearchPizza model;
	
	public PizzaSearchForm(String s, SearchWindow parent, CanSearchPizza model){
		super(s, parent);
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(200,200);
		//teraz createForm to część konstrukcji i nie jest wywoływane z zewnątrz
		createForm();
	}
	
	private void createForm(){
		this.setSize(300,250+IngredientsHelper.INGR_NUM*10);
		
		Container mainContainer  = this.getContentPane();
        Container upperContainer = new Container();
        Container lowerContainer = new Container();
        upperContainer.setLayout(new GridLayout(6,2));
        lowerContainer.setLayout(new GridLayout(2,1));
		mainContainer.setLayout(new GridLayout(2, 1));
        mainContainer.add(upperContainer);
        mainContainer.add(lowerContainer);
		
		//Pobieranie nazwy pizzy, którą chcemy wyszukać
		Label nameLabel = new Label("Nazwa Pizzy:");
		upperContainer.add(nameLabel);
		JTextField name = new JTextField();
		this.textFields.add(name);
		upperContainer.add(name);
		
		//Pobieranie nazwy pizzeri
		Label pizzeriaLabel = new Label("Nazwa Pizzerii:");
		upperContainer.add(pizzeriaLabel);
		JTextField pizzeria = new JTextField();
		this.textFields.add(pizzeria);
		upperContainer.add(pizzeria);
		
		//Cena
		Label priceLabel = new Label("Cena:");
		upperContainer.add(priceLabel);
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
		upperContainer.add(priceContainer);
		
		//Średnia ocen
		Label rateLabel = new Label("Ocena:");
		upperContainer.add(rateLabel);
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
		upperContainer.add(rateContainer);
		
		//Liczba ocen
		Label rateCountLabel = new Label("Liczba ocen:");
		upperContainer.add(rateCountLabel);
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
		upperContainer.add(rateCountContainer);
		
		//Skład
        Vector<String> sklad = IngredientsHelper.getAllIngredients();
        Vector<JCheckBox> skladBoxes = new Vector<>();
        Container boxesContainer = new Container();
        boxesContainer.setLayout(new GridLayout(sklad.size()/2+sklad.size()%2, 2));
        for (int i=0; i<sklad.size(); i++)
        {
            JCheckBox box = new JCheckBox(sklad.elementAt(i));
            box.setFont(new Font(Font.SANS_SERIF, 0, 12));
            this.checkBoxes.add(box);
            boxesContainer.add(box);
            skladBoxes.add(box);
        }
        lowerContainer.add(boxesContainer);

		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new PizzaSearchActionListener(parent));
		lowerContainer.add(submit);
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
            boolean[] ingr = new boolean[checkBoxes.size()];
            for (int i=0; i<checkBoxes.size(); i++)
                ingr[i] = checkBoxes.get(i).isSelected();

            pizze = model.Oferta_GetSome(textFields.get(0).getText(), textFields.get(1).getText(), cena_od, cena_do, ocena_od, ocena_do, imi,ima, IngredientsHelper.translateToInt(ingr));
            parent.pizzaList.setListData(pizze);
        }
    }
}
