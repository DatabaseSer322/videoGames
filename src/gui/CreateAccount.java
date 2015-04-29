package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import database.*;

public class CreateAccount extends JPanel {

	//members
	private ModifiedJTextField textUserEmail = new ModifiedJTextField();
	private ModifiedJTextField textPassword = new ModifiedJTextField();
	private ModifiedJTextField textFirstName = new ModifiedJTextField();
	private ModifiedJTextField textLastname = new ModifiedJTextField();
	private ModifiedJTextField textDateOfBirth = new ModifiedJTextField();
	
	protected ModifiedJButtonField backButton = new ModifiedJButtonField("BACK");
	MainGUI main = null;
	
	/*
	 * CreatAccount(MainGUI mainGuiObject) sets up the layout of the
	 * 			create account page
	 */
	public CreateAccount(MainGUI mainGuiObject){
		setBackground(new Color(105,105,105));
		setPreferredSize(new Dimension(1000, 650));
		setLayout(null);
		this.main = mainGuiObject;
		
		JPanel panel = new JPanel();
		panel.setBounds(340, 133, 320, 272);
		add(panel);
		panel.setLayout(null);
		panel.setBorder(null);
		panel.setBackground(new Color(105,105,105));
		
		textUserEmail.setText("User Email");
		textUserEmail.setBounds(60, 33, 200, 28);
		textUserEmail.setColumns(10);
		textUserEmail.addFocusListener(new ModifiedFocusAdapter(textUserEmail, "User Email"));
		panel.add(textUserEmail);
		
		textPassword.setText("Password");
		textPassword.setBounds(60, 73, 200, 28);		
		textPassword.setColumns(10);
		textPassword.addFocusListener(new ModifiedFocusAdapter(textPassword, "Password"));
		panel.add(textPassword);
		
		textFirstName.setText("First Name");
		textFirstName.setBounds(60, 113, 200, 28);
		textFirstName.setColumns(10);
		textFirstName.addFocusListener(new ModifiedFocusAdapter(textFirstName, "First Name"));
		panel.add(textFirstName);
		
		textLastname.setText("Last Name");
		textLastname.setColumns(10);
		textLastname.setBounds(60, 153, 200, 28);
		textLastname.addFocusListener(new ModifiedFocusAdapter(textLastname, "Last Name"));
		panel.add(textLastname);
		
		textDateOfBirth.setText("Date Of Birth");
		textDateOfBirth.setColumns(10);
		textDateOfBirth.setBounds(60, 193, 200, 28);
		textDateOfBirth.addFocusListener(new ModifiedFocusAdapter(textDateOfBirth, "Date Of Birth"));
		panel.add(textDateOfBirth);
				
		backButton.setActionCommand("backButton");
		backButton.setBounds(6, 6, 87, 28);
		add(backButton);
		
		JButton createNewAccountButton = new JButton("Push here to create your account");
		createNewAccountButton.setBounds(292, 416, 429, 48);
		createNewAccountButton.setContentAreaFilled(false);
		createNewAccountButton.setFont(new Font("Elephant", Font.PLAIN, 20));
		createNewAccountButton.setActionCommand("createNewAccountButton");
		createNewAccountButton.setForeground(new Color(173,216,230));
		add(createNewAccountButton);
		
		JLabel lblStartYourAccount = new JLabel("Start your new account by entering the following:");
		lblStartYourAccount.setFont(new Font("Elephant", Font.PLAIN, 20));
		lblStartYourAccount.setForeground(new Color(173, 216, 230));
		lblStartYourAccount.setBounds(272, 38, 503, 70);
		add(lblStartYourAccount);
		
		createNewAccountButton.addActionListener(new ActionListener(){
	   		public void actionPerformed(ActionEvent e){
	   			
	   			User user = User.newUser(textFirstName.getText(),
						textLastname.getText(), textDateOfBirth.getText(),
						textUserEmail.getText(), textPassword.getText());
	   			Database.close();
	   			
	   			if (user != null){
					//main.loadUserInfoIntoControls();
					MainGUI.showApplicationGUI();
				} 
	   			else {
	   			
					JOptionPane.showMessageDialog(null, "User is already being used. Please try a new username.", "InfoBox: SER SPORTS", JOptionPane.INFORMATION_MESSAGE);
				}
	   		}
	   	});
		
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
					MainGUI.showLoginGUI();
			}
		});
	}

	/*
	 * isUniqueUser() allows to scan through the database to prevent duplicate accounts
	 */
	public boolean isUniqueUser(){
		boolean isUnique = false;
		
		String sqlString = "SELECT COUNT(*) AS rowcount FROM " + User.getTableName() + 
				    " WHERE " + User.getFieldUserEmail() + " = " + "\"" + textUserEmail.getText() + "\"";
		ResultSet rs = Database.getResultSetFromSQL(sqlString);
		
		try {
			rs.next();
			int count = rs.getInt("rowcount");
			System.out.println("count: "+count);
			if(count < 1){
				return true;
			} else{
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Issue in CreateAccount, isUniqueUser");
		}
		
		return isUnique;
	}
}
