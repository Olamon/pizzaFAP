package window;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JFrame;

import window.search.SearchWindow;

public class UserMenu {
	
	private JFrame frame;
	private ButtonWithNewWindow search;
	private ButtonWithNewWindow account;
	
	
	public UserMenu(){
		//wydaje mi się, że lepiej wszystko przenieść do konstruktora
		frame = new JFrame("Menu Użytkownika");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setMinimumSize(new Dimension(300,300));
		
		search = new ButtonWithNewWindow("Wyszukaj");
		account = new ButtonWithNewWindow("Moje konto");
		
		//Oppa Javascript style
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchWindow sw = new SearchWindow();
				sw.setVisible(true);
            }
		});
		
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(new GridLayout(2,1));
		mainContainer.add(search);
		mainContainer.add(account);
	}
	
	public void show(){
		frame.setVisible(true);
	}

}
