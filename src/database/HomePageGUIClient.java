package database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gui.HomePageGUI;

public class HomePageGUIClient extends HomePageGUI implements ActionListener {

	/*
	 * HomePageGUIClient() constructor adds action listeners to the buttons
	 * 		and automatically populates all games in from the database to 
	 * 		the table on the Home Page
	 */
	public HomePageGUIClient(){
		submitSearchButton.addActionListener(this);
		seeDetailsButton_1.addActionListener(this);
		seeDetailsButton_2.addActionListener(this);
		
		populateAllGames();
	}

	/*
	 * Buttons able to push:
	 * 		Search - lets user search for a game
	 * 		See Details 1 - allows user to see game in table 1
	 * 		See Details 2 - allows user to see game in table 2
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("submitSearchButton")){
			//System.out.println("You clicked the search button");
			
			populateSearchedGames();
			resetFields();
			
		}
		
		if (e.getActionCommand().equals("seeDetailsButton_1")){
			//System.out.println("You clicked the details 1 button");
			
			DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
					"ID", "Title", "Star Rate", "Genre", "Rating"	}, 0);
			
			int currentSelectedRow = table_3.getSelectedRow();
			if (currentSelectedRow >= 0){
				String idToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 0);
				String titleToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 1);
				String rateToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 2);
				String genreToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 3);
				String ratingToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 4);
				
				Object[] row = { idToCompare, titleToCompare, rateToCompare, genreToCompare, ratingToCompare };
				newTable.addRow(row);
				table_1.setModel(newTable);
				table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
				
			} else {
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to compare",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if (e.getActionCommand().equals("seeDetailsButton_2")){
			//System.out.println("You clicked the details 2 button");
			
			DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
					"ID", "Title", "Star Rate", "Genre", "Rating"	}, 0);
			
			int currentSelectedRow = table_3.getSelectedRow();
			if (currentSelectedRow >= 0){
				String idToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 0);
				String titleToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 1);
				String rateToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 2);
				String genreToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 3);
				String ratingToCompare = (String) table_3.getModel().getValueAt(currentSelectedRow, 4);
				
				Object[] row = { idToCompare, titleToCompare, rateToCompare, genreToCompare, ratingToCompare };
				newTable.addRow(row);
				table_2.setModel(newTable);
				table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
				
			} else {
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to compare",
						"InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	/*
	 * populateSearchedGames() displays the games that the user is searching for
	 */
	public void populateSearchedGames(){
		
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"ID", "Title", "Star Rate", "Genre", "Rating"	}, 0);
		
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
		
		table_3.setModel(newTable);
		table_3.removeColumn(table_3.getColumnModel().getColumn(0)); //Gid column is removed but not gone
	}
	
	/*
	 * checkForString(String userInput) takes in the inputs each JTextField at a time
	 * 		to check for any changes and returns either the string they user typed or null
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
	 * populateAllGames() allows all the games to be shown in the table
	 */
	public void populateAllGames(){
		//Gid is added behind the scenes
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Star Rate", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList){
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_3.setModel(newTable);
		table_3.removeColumn(table_3.getColumnModel().getColumn(0)); //Gid column is removed but not gone
	}
}
