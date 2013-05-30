import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class PizzeriaSearchForm extends SearchForm{
	
	private JTextField name;
	
	public PizzeriaSearchForm(String s){
		super(s);
	}
	
	public void createForm(){
		Container mainContainer  = this.getContentPane();
		mainContainer.setLayout(new GridLayout(2,2));
		
		//Pobieranie nazwy pizzerii, którą chcemy wyszukać
		Label nameLabel = new Label("Nazwa Pizzerii:");
		mainContainer.add(nameLabel);
		this.name = new JTextField();
		mainContainer.add(this.name);
		
		//Przycisk wyszukaj
		Button submit = new Button("Szukaj");
		submit.addActionListener(new SearchForm.SearchActionListener());
		mainContainer.add(submit);
	}
}
