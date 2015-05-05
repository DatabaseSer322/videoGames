package gui;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import java.awt.Font;

import database.Database;
import database.HomePageGUIClient;
import database.UserAccountGUIClient;

public class AppGUI extends JLayeredPane {
	//members
	private JPanel panelBodyContainer = new JPanel();
	private JPanel menuPanel = new JPanel();
	private CardLayout card1 = new CardLayout();
	private HomePageGUIClient home = new HomePageGUIClient();
	private UserAccountGUIClient userAccount = new UserAccountGUIClient();
	private UserLoginGUI login = new UserLoginGUI();
	private ModifiedJButtonField signOutButton = new ModifiedJButtonField("SIGN OUT");
	private ModifiedJButtonField homeButton = new ModifiedJButtonField("HOME");
	private ModifiedJButtonField userAccountButton = new ModifiedJButtonField("USER ACCOUNT");
	private Font font_1 = new Font("Elephant", Font.BOLD, 22);
	private Color color_blue = new Color(173,216,230);
	
	/*
	 * AppGUI() activates the pages after login and allows user to log out of their account
	 */
	public AppGUI(){
		setLayout(null);
		setPreferredSize(new Dimension(100,650));
		
		//Menu		
		menuPanel.setBounds(0, 0, 1300, 50);
		menuPanel.setBackground(new Color(119,136,153));
		menuPanel.setLayout(null);
		add(menuPanel);
		
		homeButton.setBounds(34, 10, 117, 29);
		homeButton.setFont(font_1);
		homeButton.setBorder(BorderFactory.createEmptyBorder());
		homeButton.setContentAreaFilled(false);
		homeButton.setForeground(color_blue);
		menuPanel.add(homeButton);
		
		userAccountButton.setBounds(374, 10, 250, 29);
		userAccountButton.setFont(font_1);
		userAccountButton.setBorder(BorderFactory.createEmptyBorder());
		userAccountButton.setContentAreaFilled(false);
		userAccountButton.setForeground(color_blue);
		menuPanel.add(userAccountButton);
		
		
		signOutButton.setBounds(800, 10, 187, 29);
		signOutButton.setFont(font_1);
		signOutButton.setBorder(BorderFactory.createEmptyBorder());
		signOutButton.setContentAreaFilled(false);
		signOutButton.setForeground(color_blue);
		menuPanel.add(signOutButton);
		
		
		panelBodyContainer.setBounds(0,50,1300,650);
		add(panelBodyContainer);
		panelBodyContainer.setLayout(card1);
		panelBodyContainer.add(home, "1");
		panelBodyContainer.add(userAccount, "2");
		
		signOutButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", 
    					null, JOptionPane.YES_NO_OPTION);
        		if(result == JOptionPane.YES_OPTION){
        			MainGUI.setApplicationToClose();
        			MainGUI.showLoginGUI();
        		}
    		}
    	});
		
		homeButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			card1.show(panelBodyContainer, "1");
 
    		}
    	});
		
		userAccountButton.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent arg0){
    			card1.show(panelBodyContainer, "2");
 
    		}
    	});
	}
}
