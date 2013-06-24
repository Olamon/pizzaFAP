/*
 * Obsługa przycisku "Rejestruj"
 */

package window.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

class RegisterActionListener implements ActionListener {
	public RegisterActionListener(RegisterWindow window) {
		this.window = window;
	}
	public void actionPerformed(ActionEvent e) {
		String  password = new String( window.passwordField.getPassword() ),
				confirm  = new String( window.confirmField.getPassword() );
		//sprawdzamy czy hasło i powtórzone hasło do siebie pasują
		if( !password.equals(confirm) ) {
			JOptionPane.showMessageDialog(null, "Podane hasła różnią się!", 
				"Błąd", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		//jeśli nie udało się zarejestrować, informujemy użytkownika
		//rejestrację wykonuje teraz model
		if( !window.model.register(	window.loginField.getText(), password, 
									window.selected) ) {
			JOptionPane.showMessageDialog(null, "Podany login jest zajęty!", 
    				"Błąd", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		window.setVisible(false);
	};
	
	private RegisterWindow window;
};
