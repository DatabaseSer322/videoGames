package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.UserAccountGUI;
import database.Games;

public class UserAccountGUIClient extends UserAccountGUI implements ActionListener {
	
	/*
	 * UserAccountGUIClient() constructor allows to add action listener to the
	 * 		buttons on the User Account page and automatically populates all 
	 * 		the games from the database into the table
	 */
	public UserAccountGUIClient(){
		searchButton.addActionListener(this);
		addToWishListButton.addActionListener(this);
		addToCurrentListButton.addActionListener(this);
		seeWishListButton.addActionListener(this);
		seeCurrentListButton.addActionListener(this);
		updateToCurrentListButton.addActionListener(this);
		deleteFromListButton.addActionListener(this);
		
		populateAllGames();
		Database.close();
	}

	/*
	 * Buttons able to push:
	 * 		Search - lets user search for a game
	 * 		Add to Wish List - adds a game to user's wish list
	 * 		Add to Current List - adds a game to user's current list
	 * 		See Wish List - allows the user to see their wish list
	 * 		See Current List - allows the user to see their current list
	 * 		Update to Current List - allows user to switch a wish game to current game
	 * 		Delete from List - allows user to delete a game from their list
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("searchButton")){
			//System.out.println("You clicked the search button");
			
			populateSearchedGames();
			Database.close();
		}
		
		if (e.getActionCommand().equals("addToWishListButton")){
			//System.out.println("You clicked add to wish list button");
			
			addToList("Wish");
			
		}
		
		if (e.getActionCommand().equals("addToCurrentListButton")){
			//System.out.println("You clicked add to current list button");
			
			addToList("Current");
		}
		
		if (e.getActionCommand().equals("seeWishListButton")){
			//System.out.println("You clicked see wish list button");
			
			populateUserList("Wish");
		}
		
		if (e.getActionCommand().equals("seeCurrentListButton")){
			//System.out.println("You clicked see current list button");
			
			populateUserList("Current");
		}
		
		if (e.getActionCommand().equals("updateToCurrentListButton")){
			//System.out.println("You clicked update to current list button");
			
			updateToCurrent();
		}
		
		if (e.getActionCommand().equals("deleteFromListButton")){
			//System.out.println("You clicked delete from list button");
			
			deleteFromList();
		}
	}
	
	/*
	 * populateAllGames() populates all games form the database to the table
	 */
	public void populateAllGames(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList){
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_2.setModel(newTable);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	
	/*
	 * populateUserList(String status) takes in the status as string to add a game
	 * 		to a user's wish or current list
	 */
	public void populateUserList(String status){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating" }, 0);
		
		if(User.getCurrentUser() != null){
			User currentlyLoggedInUser = User.getCurrentUser();
			int loggedInUserID = currentlyLoggedInUser.getUserId();
			
			ArrayList<Games> gamesList = Games.getUserGamesFromDatabase(status, loggedInUserID);
			
			for (Games g : gamesList){
				Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
								g.getGameGenre(), g.getGameRatingAge() };
				newTable.addRow(row);
			}
			
			table_1.setModel(newTable);
			table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
			table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(75);
		}
	}
	
	/*
	 * populateSearchedGames() allows the user to see their search results in the table
	 */
	public void populateSearchedGames(){
		
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"ID", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		Games filter = new Games(checkForString(txtTitle.getText()), 
								checkForString(txtStarRate.getText()), 
								checkForString(txtGenre.getText()), 
								checkForString(txtRating.getText()));
		ArrayList<Games> gamesList = Games.getFilteredGamesFromDatabase(filter);
		
		for (Games g : gamesList){
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_2.setModel(newTable);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	
	/*
	 * checkForString(String userInput) takes in user's input one JTextField at a time
	 * 		and checks for any changes. If no changes made, return null, otherwise
	 * 		return string
	 */
	public String checkForString(String userInput){
		String result;
		
		if (userInput.equals("") ||
			userInput.equals("Title") ||
			userInput.equals("Star Rate") ||
			userInput.equals("Genre") ||
			userInput.equals("Rating") ||
			userInput.length() == 0) {
			
			result = null;
		} else {
			result = userInput;
		}
		
		return result;
	}
	
	/*
	 * addToWishList(String status) takes in the row highlighted and
	 * 		the status to add to the database then re-populate table
	 */
	public void addToList(String status){
		int currentSelectedRow = table_2.getSelectedRow();
		if (currentSelectedRow >= 0){
			try{
				
				int currentUserId = User.getCurrentUser().getUserId();
				String gidString = (String) table_2.getModel().getValueAt(currentSelectedRow, 0);
				int gid = Integer.parseInt(gidString);
				
				Games selectedGame = new Games();
				selectedGame.addGameFromList(gid,currentUserId, status);
				
				populateUserList("Wish");
			} catch (RuntimeException ex) {
				throw ex;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Please select a row in which you would like to add",
					"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*
	 * updateToCurrent() takes in the row highlighted in the wish list and
	 * 		add to the database as current then re-populate table
	 */
	public void updateToCurrent(){
		String status = "Current";
		int currentSelectedRow = table_1.getSelectedRow();
		if (currentSelectedRow >= 0){
			try{
				int currentUserId = User.getCurrentUser().getUserId();
				String gidString = (String) table_1.getModel().getValueAt(currentSelectedRow, 0);
				int gid = Integer.parseInt(gidString);
				
				Games selectedGame = new Games();
				selectedGame.updateGameFromWishToCurrentList(currentUserId, gid);
				/*
				selectedGame.deleteGameFromList(gid,currentUserId);
				selectedGame.addGameFromList(gid, currentUserId, status);
				*/
				
				populateUserList("Wish");
			} catch (RuntimeException ex) {
				throw ex;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Please select a row in which you would like to update",
					"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*
	 * deleteFromList() allows the user to delete any game they highlight
	 * 		 from either list
	 */
	public void deleteFromList(){
		int currentSelectedRow = table_1.getSelectedRow();
		if (currentSelectedRow >= 0){
			/*
			 * Displays warning window asking the user if they would like to follow
			 * through with deleting the selected game from their list.
			 */
			int result = JOptionPane.showConfirmDialog(
							null, "Are you sure you want to delete the highlighted game from your list?",
							null, JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION){
				try{
					int currentUserId = User.getCurrentUser().getUserId();
					String gidString = (String) table_1.getModel().getValueAt(currentSelectedRow, 0);
					int gid = Integer.parseInt(gidString);
					
					Games selectedGame = new Games();
					selectedGame.deleteGameFromList(gid,currentUserId);

					populateUserList("Wish");
				} catch (RuntimeException ex) {
					throw ex;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Please select a row in which you would like to delete",
					"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*
	 * clearAllFieldsInUserAccountPage() allows for the fields to be empty when the
	 * 		current user logs out and a new user logs in
	 */
	public static void clearAllFieldsInUserAccountPage(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		Object[] row = { null, null, null, null, null };
		newTable.addRow(row);
		table_1.setModel(newTable);
		table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(75);
		
	}
}
