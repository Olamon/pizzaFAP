import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

public class ButtonWithNewWindow extends JButton implements ActionListener{
	
	JFrame frame;
	SearchForm form;
	
	public ButtonWithNewWindow(String s){
		super(s);
		this.addActionListener(this);
		this.setActionCommand(s);
		this.frame = new JFrame();
	}
	
	public ButtonWithNewWindow(String s, SearchForm form){
		super(s);
		this.addActionListener(this);
		this.form = form;
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		String command = action.getActionCommand();
		if(command.equals("Wyszukaj")) {
			SearchWindow sw = new SearchWindow();
			sw.show();
		} else {
			
			//Gdy formularz jeszcze nie gotowy...
			if(form == null){
				System.out.println("Not ready yet");
			}
			
			form.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			form.setSize(300, 100);
			form.setLocation(200,200);
			form.createForm();
			form.setVisible(true);
		}
	}

}
