package database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import gui.HomePageGUI;

public class HomePageGUIClient extends HomePageGUI implements ActionListener {

	//member
	int currentSelectedRow;
	
	/*
	 * HomePageGUIClient() constructor adds action listeners to the buttons
	 * 		and automatically populates all games in from the database to 
	 * 		the table on the Home Page
	 */
	public HomePageGUIClient(){
		submitSearchButton.addActionListener(this);
		seeDetailsButton_1.addActionListener(this);
		seeDetailsButton_2.addActionListener(this);
		removeCompareButton_1.addActionListener(this);
		removeCompareButton_2.addActionListener(this);
		
		populateAllGames();
		Database.close();
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
			
			populateCompareList(table_1);
		}
		
		if (e.getActionCommand().equals("seeDetailsButton_2")){
			//System.out.println("You clicked the details 2 button");
			
			populateCompareList(table_2);
		}
		
		if (e.getActionCommand().equals("removeCompareButton_1")){
			//System.out.println("Remove Game 1.");
			
			removeComp(table_1);
		}
		
		if (e.getActionCommand().equals("removeCompareButton_2")){
			//System.out.println("Remove Game 2.");
			
			removeComp(table_2);
		}
	}

	
	/*
	 * populateSearchedGames() displays the games that the user is searching for
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
		
		table_3.setModel(newTable);
		table_3.removeColumn(table_3.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_3.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(3).setPreferredWidth(75);
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
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList){
			Object[] row = { g.getGameID(), g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_3.setModel(newTable);
		table_3.removeColumn(table_3.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_3.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	
	/*
	 * removeComp(JTable table) allows to remove game from tables
	 */
	public void removeComp(JTable table){
		while(table.getRowCount() > 0){
			((DefaultTableModel)table.getModel()).removeRow(0);
		}
	}
	
	public void populateCompareList(JTable table){
		
		currentSelectedRow = table_3.getSelectedRow();
		if (currentSelectedRow >= 0){
			try{
				String gidString = (String) table_3.getModel().getValueAt(currentSelectedRow, 0);
				int gid = Integer.parseInt(gidString);

				DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
						"Gid", "Title", "Star Rate", "Genre", "Rating" }, 0);
					
					Games g1 = Games.getGamesFromDatabaseWithID(gid);		
					
					Object[] row = { g1.getGameID(), g1.getGameTitle(), g1.getGameRateStar(), 
										g1.getGameGenre(), g1.getGameRatingAge() };
					newTable.addRow(row);
					
					table.setModel(newTable);
					table.removeColumn(table.getColumnModel().getColumn(0)); //Gid column is removed but not gone
					table.getColumnModel().getColumn(0).setPreferredWidth(200);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(75);
			} catch (RuntimeException ex) {	
				System.out.println("Runtime Exception");
				throw ex;
			} catch (Exception ex) {
				System.out.println("Exception");
				ex.printStackTrace();
			} 
		} else {
				JOptionPane.showMessageDialog(null,
						"Please select a row in which you would like to compare",
				"	InfoBox: Video Games", JOptionPane.INFORMATION_MESSAGE);
		}
	}
		
	/*
	 * compareToTable1(int currSelRow) takes in the row number selected by the user
	 * 		and gets all the information to display in the comparison table
	 */
	public void compareToTable1(int currSelRow){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);

		String idToCompare = (String) table_3.getModel().getValueAt(currSelRow, 0);
		String titleToCompare = (String) table_3.getModel().getValueAt(currSelRow, 1);
		String rateToCompare = (String) table_3.getModel().getValueAt(currSelRow, 2);
		String genreToCompare = (String) table_3.getModel().getValueAt(currSelRow, 3);
		String ratingToCompare = (String) table_3.getModel().getValueAt(currSelRow, 4);
		
		Object[] row = { idToCompare, titleToCompare, rateToCompare, genreToCompare, ratingToCompare };
		newTable.addRow(row);
		table_2.setModel(newTable);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	
	/*
	 * compareToTable2(int currSelRow) takes in the row number selected by the user
	 * 		and gets all the information to display in the comparison table
	 */
	public void compareToTable2(int currSelRow){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		String idToCompare = (String) table_3.getModel().getValueAt(currSelRow, 0);
		String titleToCompare = (String) table_3.getModel().getValueAt(currSelRow, 1);
		String rateToCompare = (String) table_3.getModel().getValueAt(currSelRow, 2);
		String genreToCompare = (String) table_3.getModel().getValueAt(currSelRow, 3);
		String ratingToCompare = (String) table_3.getModel().getValueAt(currSelRow, 4);
		
		Object[] row = { idToCompare, titleToCompare, rateToCompare, genreToCompare, ratingToCompare };
		newTable.addRow(row);
		table_1.setModel(newTable);
		table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
	
/*
 *  clearAllFieldsInHomePage() allows for the fields to be empty when the
 * 		current user logs out and a new user logs in
 */
	public static void clearAllFieldsInHomePage(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Gid", "Title", "Stars", "Genre", "Rating"	}, 0);
		
		Object[] row = { null, null, null, null, null };
		newTable.addRow(row);
		table_1.setModel(newTable);
		table_1.removeColumn(table_1.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(75);
		
		newTable.addRow(row);
		table_2.setModel(newTable);
		table_2.removeColumn(table_2.getColumnModel().getColumn(0)); //Gid column is removed but not gone
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(75);
	}
}
