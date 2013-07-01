package window.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import states.StateManager;
import states.User;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AccountWindow extends JFrame {
	final User model;
	
	public AccountWindow(String s, final User model){
		super(s);
		this.setSize(300,300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.model = model;
		getContentPane().setLayout(new MigLayout("", "[grow 50][grow]", "[grow 30][grow 30][grow 30][grow 30]"));
		
		JLabel lblEmail = new JLabel("Email:");
		getContentPane().add(lblEmail, "cell 0 0");
		
		JLabel email = new JLabel(StateManager.user_id);
		getContentPane().add(email, "cell 1 0");
		
		
		JLabel lblIloOcen = new JLabel("Liczba ocen:");
		getContentPane().add(lblIloOcen, "cell 0 1");
		
		JLabel ilosc = new JLabel("" + model.Ocena_GetCountByUser(StateManager.user_id));
		getContentPane().add(ilosc, "cell 1 1");
		
		JLabel lblredniaOcen = new JLabel("Średnia ocen:");
		getContentPane().add(lblredniaOcen, "cell 0 2");
		
		JLabel srednia = new JLabel("" + model.Ocena_GetAverageByUser(StateManager.user_id));
		getContentPane().add(srednia, "cell 1 2");
		
		JButton btnZobaczOceny = new JButton("Zobacz oceny");
		getContentPane().add(btnZobaczOceny, "cell 1 3");
		btnZobaczOceny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OcenaWindow ow = new OcenaWindow("Moje oceny", model.Ocena_GetAllByUser(StateManager.user_id), model);
				ow.setVisible(true);
			}
		});
	}
}
