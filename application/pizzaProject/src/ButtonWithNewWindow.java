/* 
 * Spora zmiana (do uzgodnienia) usunąłem interfejs ActionListener z tej klasy
 * uznałem, że zamiast tego lepiej jest używać metody addActionListener
 * dostępnej w JButton i anonimowych listenerów (tak jak w linijkach 22-29).
 * Jeżeli natomiast ActionListener byłby duży to można go wydzielić do osobnej
 * funkcji - i tak kod musi gdzieś trafić. Obecnie wszystko grupowałoby się
 * w actionPerformed i trzeba by pisać ify żeby rozróżnić jaki button został
 * kliknięty. 
 */

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

public class ButtonWithNewWindow extends JButton {
	
	private JFrame frame;
	
	public ButtonWithNewWindow(String s){
		super(s);
		frame = new JFrame();
	}
	
	//SearchForm musi być final, bo kompilator marudzi
	public ButtonWithNewWindow(String s, final SearchForm form){
		super(s);
		frame = form;
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				form.setVisible(true);
			}
		});
	}
}
