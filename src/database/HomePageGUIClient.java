package database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.HomePageGUI;

public class HomePageGUIClient extends HomePageGUI implements ActionListener {

	public HomePageGUIClient(){
		submitSearchButton.addActionListener(this);
		seeDetailsButton_1.addActionListener(this);
		seeDetailsButton_2.addActionListener(this);
		
		populateAllGames();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("submitSearchButton")){
			//System.out.println("You clicked the search button");
			
			populateSearchedGames();
			resetFields();
			
		}
		
		if (e.getActionCommand().equals("seeDetailsButton_1")){
			System.out.println("You clicked the details 1 button");
			
		}
		
		if (e.getActionCommand().equals("seeDetailsButton_2")){
			System.out.println("You clicked the details 2 button");
			
		}
	}
	
	/*  NOT FINISHED  */
	public void populateUserList(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Title", "Star Rate", "Genre", "Rating" }, 0);
		
		if(User.getCurrentUser() != null){
			User currentlyLoggedInUser = User.getCurrentUser();
			int loggedInUserID = currentlyLoggedInUser.getUserId();
		}
	}
	
	public void populateSearchedGames(){
		
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Title", "Star Rate", "Genre", "Rating"	}, 0);
		
		Games filter = new Games(checkForString(txtTitle.getText()), 
								checkForString(txtStarRate.getText()), 
								checkForString(txtGenre.getText()), 
								checkForString(txtRating.getText()));
		ArrayList<Games> gamesList = Games.getFilteredGamesFromDatabase(filter);
		
		for (Games g : gamesList)
		{
			Object[] row = { g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_3.setModel(newTable);
	}
	
	public String checkForString(String userInput){
		String result;
		
		if (userInput.equals("") ||
			userInput.equals("Title") ||
			userInput.equals("Star Rate") ||
			userInput.equals("Genre") ||
			userInput.equals("Rating") ||
			userInput.length() == 0) {
			
			result = null;
		}
		else {
			result = userInput;
		}
		
		return result;
	}
	
	public void populateAllGames(){
		//Gid is added behind the scenes
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Star Rate", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList)
		{
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_3.setModel(newTable);
		table_3.removeColumn(table_3.getColumnModel().getColumn(0)); //Gid column is removed but not gone
	}
}
