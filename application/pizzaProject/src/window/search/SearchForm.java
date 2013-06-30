package window.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public abstract class SearchForm extends JFrame{
	
	protected ArrayList<JTextField> textFields;
	protected ArrayList<JCheckBox> checkBoxes;
	protected SearchWindow parent;
	
	public SearchForm(String s, SearchWindow parent){
		super(s);
		this.parent = parent;
		this.textFields = new ArrayList<JTextField>();
		this.checkBoxes = new ArrayList<JCheckBox>();
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
