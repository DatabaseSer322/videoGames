package gui;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainGUI {
	
	//members
	private static MainGUI singleton = null;
	protected final static AppGUI panelApplication = new AppGUI();
	JFrame mainFrame = new JFrame("Video Games");
	static CardLayout card1 = new CardLayout();
	static JPanel panelContainer = new JPanel();
	static JScrollPane scrollPane = new JScrollPane();
	static UserLoginGUI login = new UserLoginGUI();
	CreateAccount createAccountGUI = new CreateAccount(this);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	/*
	 * MainGUI() sets up each panel container, login page,
	 * 				create account page, and the home page
	 * 				Also, sets the window to start in the middle
	 * 				of the screen
	 */
	public MainGUI() {

		mainFrame.getContentPane().add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(panelContainer);
		panelContainer.setLayout(card1);
		
		card1.show(panelContainer, "1");
		panelContainer.add(login, "1");
		panelContainer.add(panelApplication, "2");
		panelContainer.add(createAccountGUI, "3");

		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2,
				dim.height / 2 - mainFrame.getSize().height / 2);

	}
	
	/*
	 * showLoginGUI() displays the login gui
	 */
	public static void showLoginGUI() {
		card1.show(panelContainer, "1");
	}

	//showAppliationGUI() displays the main program after logging in
	public static void showApplicationGUI() {
		//panelApplication.loadUserInfoIntoControls();
		card1.show(panelContainer, "2");
		
	}

	/*
	 * showCreateAccount() displays the create account page
	 */
	public static void showCreateAccount() {
		card1.show(panelContainer, "3");
	}

	/*
	 * setApplicationToClose() allows to close the account and let
	 * 				another user to log in
	 */
	public static void setApplicationToClose() {
		if (singleton != null) {
			singleton.mainFrame.dispose();
			singleton.mainFrame.repaint();
			singleton = new MainGUI();
			
		}
	}
	
	/*
	 * setHourGlass(Boolean hourGlassOn) takes in a boolean expression to turn on/off
	 * 				the cursor while attempting to read from the database
	 */
	public static void setHourGlass(Boolean hourGlassOn) {
		if (singleton != null) {
			if (hourGlassOn) {
				singleton.mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			} else {
				singleton.mainFrame.setCursor(Cursor.getDefaultCursor());
			}
		}
	}
	
	/*
	 * main(String[] args) starts the program
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					singleton = new MainGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
