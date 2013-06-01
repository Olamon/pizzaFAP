import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;


public abstract class SearchForm extends JFrame{
	
	protected ArrayList<JTextField> textFields;
	
	public SearchForm(String s){
		super(s);
		this.textFields = new ArrayList<JTextField>();
	}
	
	public class SearchActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0; i<textFields.size(); i++){
				System.out.println((textFields.get(i)).getText());
			}
		}
	}
}
