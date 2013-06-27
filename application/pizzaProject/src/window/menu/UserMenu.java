package window.menu;

import java.awt.event.*;
import java.awt.*;
import javax.swing.JFrame;

import window.search.SearchWindow;
import window.ButtonWithNewWindow;

import states.User;

public class UserMenu extends JFrame {
	private ButtonWithNewWindow search;
	private ButtonWithNewWindow account;
	
	//model, który okienko informuje co należy zrobić
	//(na razie nic nie umie) 
	User model;
	
	public UserMenu(final User model) {
		super("Menu Użytkownika");
		this.model = model;
		//wydaje mi się, że lepiej wszystko przenieść do konstruktora
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setMinimumSize(new Dimension(300,300));
		
		search = new ButtonWithNewWindow("Wyszukaj");
		account = new ButtonWithNewWindow("Moje konto");
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//przekazujemy model okienkom pochodnym (chyba lepiej przekazywać okienko rodzica)
				SearchWindow sw = new SearchWindow(model);
				sw.setVisible(true);
            }
		});
		
		Container mainContainer = getContentPane();
		mainContainer.setLayout(new GridLayout(2,1));
		mainContainer.add(search);
		mainContainer.add(account);
	}
}
