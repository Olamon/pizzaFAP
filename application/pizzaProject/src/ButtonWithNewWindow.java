import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;

import java.awt.event.*;

public class ButtonWithNewWindow extends JButton implements ActionListener{
	
	JFrame frame;
	
	public ButtonWithNewWindow(String s){
		super(s);
		this.addActionListener(this);
		this.frame = new JFrame();
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setLocation(200,200);
		JTextField textfield = new JTextField("Witajcie!");
		textfield.setHorizontalAlignment(JTextField.CENTER);
		(frame.getContentPane()).add(textfield);
		frame.setVisible(true);
	}

}
