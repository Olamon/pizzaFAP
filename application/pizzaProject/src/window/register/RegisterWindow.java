/*
 * Okienko z rejestracją
 */

package window.register;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame {

    public RegisterWindow() {
        initComponents();
        
        //selected to String należący do okienka, w którym trzymany jest
        //aktualny wybór użytkownika
        uzytkownikRButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		selected = "uzytkownik";
        	}
        });
        wlascicielRButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		selected = "wlasciciel";
        	}
        });
        
        RegisterActionListener doRegister = new RegisterActionListener(this);
        doneButton.addActionListener(doRegister);
        passwordField.addActionListener(doRegister);
        confirmField.addActionListener(doRegister);
    }
    
    //wygenerowany kod...
    //TODO - pimp my window
    private void initComponents() {

        typKonta = new javax.swing.ButtonGroup();
        passwordField = new javax.swing.JPasswordField();
        loginField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        horizontalSeparator = new javax.swing.JSeparator();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        confirmLabel = new javax.swing.JLabel();
        confirmField = new javax.swing.JPasswordField();
        doneButton = new javax.swing.JButton();
        uzytkownikRButton = new javax.swing.JRadioButton();
        wlascicielRButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Rejestracja");

        loginLabel.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        loginLabel.setText("Login");

        passwordLabel.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        passwordLabel.setText("Hasło");

        confirmLabel.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        confirmLabel.setText("Powtórz hasło");

        doneButton.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        doneButton.setText("Gotowe");

        typKonta.add(uzytkownikRButton);
        uzytkownikRButton.setSelected(true);
        uzytkownikRButton.setText("Użytkownik");

        typKonta.add(wlascicielRButton);
        wlascicielRButton.setText("Właściciel");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(loginLabel)
                                        .addGap(55, 55, 55))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(passwordLabel)
                                        .addGap(54, 54, 54)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(confirmLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(uzytkownikRButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(wlascicielRButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uzytkownikRButton)
                    .addComponent(wlascicielRButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
                      
    javax.swing.JPasswordField confirmField;
    javax.swing.JLabel confirmLabel;
    javax.swing.JButton doneButton;
    javax.swing.JSeparator horizontalSeparator;
    javax.swing.JTextField loginField;
    javax.swing.JLabel loginLabel;
    javax.swing.JPasswordField passwordField;
    javax.swing.JLabel passwordLabel;
    javax.swing.JLabel titleLabel;
    javax.swing.ButtonGroup typKonta;
    javax.swing.JRadioButton uzytkownikRButton;
    javax.swing.JRadioButton wlascicielRButton;
    String selected = "uzytkownik";
}