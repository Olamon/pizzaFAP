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
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(2,2));
		
		//Pobieranie nazwy pizzerii, którą chcemy wyszukać
		Label nameLabel = new Label("Nazwa Pizzerii:");
		mainContainer.add(nameLabel);
		JTextField name = new JTextField();
		this.textFields.add(name);
		mainContainer.add(name);
		
		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new SearchForm.SearchActionListener());
		mainContainer.add(submit);
	}
}
