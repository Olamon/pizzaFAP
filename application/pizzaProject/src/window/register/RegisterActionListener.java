/*
 * Obsługa przycisku "Rejestruj"
 */

package window.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import objects.User;
import window.UserMenu;

class RegisterActionListener implements ActionListener {
	public RegisterActionListener(RegisterWindow window) {
		this.window = window;
	}
	public void actionPerformed(ActionEvent e) {
		try {
    		char[] password = window.passwordField.getPassword();
    		char[] confirm  = window.confirmField.getPassword();
    		
    		//sprawdzamy czy hasło i powtórzone hasło do siebie pasują
    		if( !Arrays.equals(password, confirm) ) {
    			JOptionPane.showMessageDialog(null, "Podane hasła różnią się!", 
    				"Błąd", JOptionPane.INFORMATION_MESSAGE);
    			return;
    		}
    		
    		//tworzymy prototyp użytkownika
    		User user = User.CreateUser(window.loginField.getText(), 
										new String(password),
										window.selected);
    		
    		if(user.Exists()) {
    			JOptionPane.showMessageDialog(null, "Podany login jest zajęty!", 
        				"Błąd", JOptionPane.INFORMATION_MESSAGE);
    			return;
    		}
    	        		
    		//jeśli dane są poprawne to zapisujemy użytkownika, zerujemy tablice
    		//z hasłami (pro security) i chowamy okienko
    		user.Insert();
    		
    		Arrays.fill(password, '0');
    		Arrays.fill(confirm, '0');
    		window.setVisible(false);
		} 
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(RegisterActionListener.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	};
	
	private RegisterWindow window;
};
