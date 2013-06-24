/*
 * Menu właściciela
 * TODO menu na razie nic nie robi, należy dodać funkcjonalności
 */

package window.menu;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridLayout;

import roles.Owner;

public class OwnerMenu extends JFrame {
	private JButton addPizzeriaButton;
	private JButton addOfertaButton;
	
	//model, który okienko informuje co należy zrobić
	//(na razie nic nie umie)
	Owner model;
	
    public OwnerMenu(Owner model) {
    	super();
    	this.model = model;
    	
    	initComponents();  	
    }
    
    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setMinimumSize(new Dimension(300,300));
		
    	getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
    	
    	addPizzeriaButton = new JButton("Dodaj pizzerię");
    	getContentPane().add(addPizzeriaButton);
    	
    	addOfertaButton = new JButton("Dodaj ofertę");
    	getContentPane().add(addOfertaButton);
    }
}