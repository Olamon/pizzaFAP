/*
 * Obsługa przycisku "Login"
 */

package window.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import objects.User;
import window.UserMenu;

class LoginActionListener implements ActionListener {
	public LoginActionListener(LoginWindow window) {
		this.window = window;
	}
	public void actionPerformed(ActionEvent e) {
		try {
    		//odczytujemy hasło, tworzymy prototyp użytkownika
			//TODO napisać wczytywanie typu konta!
    		char[] password = window.passwordField.getPassword();
    		User user = User.CreateUser(window.loginField.getText(), 
    									new String(password));
    	        		
    		//jeśli dane są poprawne to chowamy okienko i przechodzimy do menu
    		//docelowo user będzie przekazywany do UserMenu
    		if( user.Authenticate() ) {
        		window.setVisible(false);
        		UserMenu userMenu = new UserMenu();
        		userMenu.show();
    		} else {
    			window.passwordField.setText("");
    		}
    		Arrays.fill(password, '0');
		} 
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(LoginActionListener.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	};
	
	private LoginWindow window;
};
