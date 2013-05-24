import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class UserMenu {
	
	JFrame frame;
	
	public UserMenu(){
		frame = new JFrame("UserMenu");
	}
	
	public void show(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setMinimumSize(new Dimension(200,200));
		Container mainContainer = frame.getContentPane();
		JTextField textfield = new JTextField("Witajcie!");
		textfield.setHorizontalAlignment(JTextField.CENTER);
		mainContainer.add(textfield);
		frame.pack();
		frame.setVisible(true);
	}

}
