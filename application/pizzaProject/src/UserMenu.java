import java.awt.*;

import javax.swing.JFrame;


public class UserMenu {
	
	JFrame frame;
	
	public UserMenu(){
		frame = new JFrame("Menu Użytkownika");
	}
	
	public void show(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setMinimumSize(new Dimension(300,300));
		Container mainContainer = frame.getContentPane();
		mainContainer.setLayout(new GridLayout(2,1));
		mainContainer.add(new ButtonWithNewWindow("Wyszukaj"));
		mainContainer.add(new ButtonWithNewWindow("Moje konto"));
		frame.setVisible(true);
	}

}
