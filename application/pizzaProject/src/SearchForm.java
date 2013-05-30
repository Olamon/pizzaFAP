import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;


public abstract class SearchForm extends JFrame{
	
	protected JTextField[] textFields;
	
	public SearchForm(String s){
		super(s);
	}
	
	abstract void createForm();
	
	public class SearchActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Przesyłąnie danych, póki co logowanie na kosoli
			System.out.println("Trututu!");
		}
	}
}
