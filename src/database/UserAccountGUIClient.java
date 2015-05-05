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
	
	public UserAccountGUIClient(){
		searchButton.addActionListener(this);
		addToWishListButton.addActionListener(this);
		addToCurrentListButton.addActionListener(this);
		seeWishListButton.addActionListener(this);
		seeCurrentListButton.addActionListener(this);
		updateToCurrentListButton.addActionListener(this);
		deleteFromListButton.addActionListener(this);
		
		populateAllGames();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("searchButton")){
			//System.out.println("You clicked the search button");
			
			populateSearchedGames();
			
		}
		
		if (e.getActionCommand().equals("addToWishListButton")){
			//System.out.println("You clicked add to wish list button");
			
			String status = "Wish";
			int currentSelectedRow = table_2.getSelectedRow();
			if (currentSelectedRow >= 0)
			{
				try
				{
					int currentUserId = User.getCurrentUser().getUserId();
					String gidString = (String) table_2.getModel().getValueAt(currentSelectedRow, 0);
					int gid = Integer.parseInt(gidString);
					
					Games selectedGame = new Games();
					
					selectedGame.addGameFromList(gid,currentUserId, status);
					
					populateUserWishList("Wish");
				}
				catch (RuntimeException ex)
				{
					throw ex;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to delete",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (e.getActionCommand().equals("addToCurrentListButton")){
			//System.out.println("You clicked add to current list button");
			
			String status = "Current";
			int currentSelectedRow = table_2.getSelectedRow();
			if (currentSelectedRow >= 0)
			{
				try
				{
					int currentUserId = User.getCurrentUser().getUserId();
					String gidString = (String) table_2.getModel().getValueAt(currentSelectedRow, 0);
					int gid = Integer.parseInt(gidString);
					
					Games selectedGame = new Games();
					selectedGame.addGameFromList(gid,currentUserId, status);
					
					populateUserWishList("Current");
				}
				catch (RuntimeException ex)
				{
					throw ex;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to delete",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (e.getActionCommand().equals("seeWishListButton")){
			//System.out.println("You clicked see wish list button");
			
			populateUserWishList("Wish");
		}
		
		if (e.getActionCommand().equals("seeCurrentListButton")){
			//System.out.println("You clicked see current list button");
			
			populateUserWishList("Current");
		}
		
		if (e.getActionCommand().equals("updateToCurrentListButton")){
			//System.out.println("You clicked update to current list button");
			
			String status = "Current";
			int currentSelectedRow = table_1.getSelectedRow();
			if (currentSelectedRow >= 0)
			{
				try
				{
					int currentUserId = User.getCurrentUser().getUserId();
					String gidString = (String) table_1.getModel().getValueAt(currentSelectedRow, 0);
					int gid = Integer.parseInt(gidString);
					
					Games selectedGame = new Games();
					selectedGame.deleteGameFromList(gid,currentUserId);
					selectedGame.addGameFromList(gid, currentUserId, status);
					
					populateUserWishList("Wish");
				}
				catch (RuntimeException ex)
				{
					throw ex;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to update",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (e.getActionCommand().equals("deleteFromListButton")){
			//System.out.println("You clicked delete from list button");
			
			int currentSelectedRow = table_1.getSelectedRow();
			if (currentSelectedRow >= 0)
			{
				
				/*
				 * Displays warning window asking the user if they would like to follow
				 * through with deleting the selected game from their list.
				 */
				int result = JOptionPane.showConfirmDialog(
								null, "Are you sure you want to delete the highlighted game from your list?",
								null, JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION)
				{
					try
					{
						int currentUserId = User.getCurrentUser().getUserId();
						String gidString = (String) table_1.getModel().getValueAt(currentSelectedRow, 0);
						int gid = Integer.parseInt(gidString);
						
						Games selectedGame = new Games();
						selectedGame.deleteGameFromList(gid,currentUserId);
					}
					catch (RuntimeException ex)
					{
						throw ex;
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to delete",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void populateAllGames(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Star Rate", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList)
		{
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_2.setModel(newTable);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
	}
	
	public void populateUserWishList(String status){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Star Rate", "Genre", "Rating" }, 0);
		
		if(User.getCurrentUser() != null){
			User currentlyLoggedInUser = User.getCurrentUser();
			int loggedInUserID = currentlyLoggedInUser.getUserId();
			
			ArrayList<Games> gamesList = Games.getUserGamesFromDatabase(status, loggedInUserID);
			
			for (Games g : gamesList)
			{
				Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
								g.getGameGenre(), g.getGameRatingAge() };
				newTable.addRow(row);
			}
			
			table_1.setModel(newTable);
			table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
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
		
		table_2.setModel(newTable);
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
}
