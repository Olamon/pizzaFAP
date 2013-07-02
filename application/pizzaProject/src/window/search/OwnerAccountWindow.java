package window.search;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import states.StateManager;
import states.Owner;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class OwnerAccountWindow extends JFrame {
	final Owner model;
	
	public OwnerAccountWindow(String s, final Owner model){
		super(s);
		this.setSize(300,100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.model = model;
		getContentPane().setLayout(new MigLayout("", "[30px,grow][30px,grow]", "[30px][30px]"));
		
		JLabel lblEmail = new JLabel("Email:");
		getContentPane().add(lblEmail, "cell 0 0");
		
		JLabel email = new JLabel(StateManager.user_id);
		getContentPane().add(email, "cell 1 0");
		
		JButton pizzerie = new JButton("Pizzerie");
		getContentPane().add(pizzerie, "cell 0 1,alignx center");
		pizzerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OcenialneWindow ow = new OcenialneWindow("Moje pizzerie", model.Pizzeria_GetByOwner(StateManager.user_id));
				ow.setVisible(true);
			}
		});
		
		JButton oferty = new JButton("Oferty");
		getContentPane().add(oferty, "cell 1 1,alignx center");
		oferty.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				OcenialneWindow ow = new OcenialneWindow("Moje oferty", model.Oferta_GetByOwner(StateManager.user_id));
				ow.setVisible(true);
			}
		});
	}
}
