import java.awt.event.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import fapDB.Session;
import objects.User;

public class LoginWindow extends JFrame {

    public LoginWindow() {
        initComponents();
        //otwieramy sesję w bazie
        //lepiej byłoby mieć tryb "login", bo tryb "użytkownik" powinien być
        //nadawany dopiero po udanej autoryzacji
		Session.start("jdbc:postgresql://localhost/pizzadb", "uzytkownikrole", "uzytkownikrole");
		
		//być moze lepiej byłoby to wydzielić do osobnej klasy
		ActionListener doLogin = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		try {
            		//odczytujemy hasło, tworzymy prototyp użytkownika
            		char[] password = passwordField.getPassword();
            		User user = User.CreateUser(loginField.getText(), new String(password));
            	        		
            		//jeśli dane są poprawne to chowamy okienko i przechodzimy do menu
            		//docelowo user będzie przekazywany do UserMenu
            		if( user.Authenticate() ) {
                		setVisible(false);
                		UserMenu userMenu = new UserMenu();
                		userMenu.show();
            		} else {
            			passwordField.setText("");
            		}
            		Arrays.fill(password, '0');
        		} 
        		catch (Exception ex) {
        			Logger lgr = Logger.getLogger(LoginWindow.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
        		}
            }
		};
		
		//po wprowadzeniu danych można kliknąć loguj albo nacisnąć enter
        loginButton.addActionListener(doLogin);
        passwordField.addActionListener(doLogin);
    }
                   
    //wygenerowany kod...
    private void initComponents() {

        passwordField = new javax.swing.JPasswordField();
        loginField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        horizontalSeparator = new javax.swing.JSeparator();
        registerButton = new javax.swing.JButton();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        verticalSeparator = new javax.swing.JSeparator();
        registerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Wrocławskie pizzerie");

        registerButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        registerButton.setText("Zerejestruj");

        loginLabel.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        loginLabel.setText("Login");

        passwordLabel.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        passwordLabel.setText("Hasło");

        loginButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        loginButton.setText("Zaloguj");

        verticalSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        registerLabel.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        registerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registerLabel.setText("Nie masz konta?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(horizontalSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(passwordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(verticalSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(registerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(horizontalSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(verticalSeparator)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loginLabel)
                            .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(registerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }
               
    private javax.swing.JSeparator horizontalSeparator;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel registerLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JSeparator verticalSeparator;                
}