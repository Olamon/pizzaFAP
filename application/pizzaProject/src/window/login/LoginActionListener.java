/*
 * Obsługa przycisku "Login"
 */

package window.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import states.StateManager;

class LoginActionListener implements ActionListener {
	public LoginActionListener(LoginWindow window) {
		this.window = window;
	}
	public void actionPerformed(ActionEvent e) {
		try {
    		String 	login = window.loginField.getText(),
    				password = new String( window.passwordField.getPassword() );
    		
    		//całe logowanie wykonuje teraz model
    		if( !window.model.login(login, password) )
    			window.passwordField.setText("");
    		else StateManager.setUserId(login);
		} 
		catch (Exception ex) {
			Logger lgr = Logger.getLogger(LoginActionListener.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	};
	
	private LoginWindow window;
};
