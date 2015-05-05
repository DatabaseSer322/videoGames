package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import database.*;

public class UserLoginGUI extends JPanel {

	//members
	protected ModifiedJTextField userEmail = new ModifiedJTextField();
	protected ModifiedJPasswordField password = new ModifiedJPasswordField();
	protected ModifiedJButtonField submitButton = new ModifiedJButtonField("SIGN IN");
	protected ModifiedJButtonField createAccountButton = new ModifiedJButtonField("CREATE ACCOUNT");
	protected Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private final JLabel userNameLabel = new JLabel("User Email:");
	private final JLabel passwordLabel = new JLabel("Password:");
	private final String usr = "Email";
	private final String pwd = "";
	private final Font font = new Font("Elephant", Font.PLAIN, 18);
	
	MainGUI main = null;
	
	/*
	 * UserLoginGUI() sets up the login page and allows to submit with proper credentials
	 * 			or create a new account
	 */
	public UserLoginGUI(){
		setLayout(null);
		setPreferredSize(new Dimension(1000,650));
		setBackground(new Color(105,105,105));
		
		userNameLabel.setFont(font);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setForeground(new Color(173,216,230));
		userNameLabel.setBounds(350, 197, 300, 29);
		add(userNameLabel);
		
		passwordLabel.setFont(font);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(new Color(173,216,230));
		passwordLabel.setBounds(350, 282, 300, 23);
		add(passwordLabel);
		
		userEmail.setBounds(350, 226, 300, 28);
		userEmail.setText(usr);
		add(userEmail);
		userEmail.addFocusListener(new ModifiedFocusAdapter(userEmail, usr));
		
		password.setLocation(350,310);
		password.setSize(300,28);
		password.setText(pwd);
		add(password);
		password.addFocusListener(new ModifiedFocusAdapter(password, pwd));
		
		submitButton.setBounds(350, 375, 300, 29);
		add(submitButton);
		
		createAccountButton.setBounds(350, 415, 300, 28);
		add(createAccountButton);
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				User user = User.authenticateUser(userEmail.getText(), new String(password.getPassword()));
				if (user != null){
					resetTextFields();
					main.showApplicationGUI();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Username / Password!", "InfoBox: SER SPORTS", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		createAccountButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main.showCreateAccount();
			}
		});
	}
	
	/*
	 * resetTextFields() resets the password and email credentials
	 * 				from previous login
	 */
	public void resetTextFields(){
		password.setText(pwd);
		userEmail.setText(usr);
	}
}
