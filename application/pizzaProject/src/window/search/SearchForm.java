package window.search;

import objects.Pizzeria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import static utils.StringUtils.asFloat;
import static utils.StringUtils.asInteger;


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
            try {
                pizzerie = new Vector < Pizzeria > (Pizzeria.GetAll(textFields.get(0).getText(), textFields.get(1).getText(), textFields.get(2).getText(),omi,oma,imi,ima));
            } catch (SQLException ex) {
                //TODO poprawić obsługę i logowanie wyjątków
                Logger lgr = Logger.getLogger(SearchWindow.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
                System.exit(1);
            }
            parent.pizzeriaList.setListData(pizzerie);
        }
    }


}
