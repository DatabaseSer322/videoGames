package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import gui.UserAccountGUI;

public class UserAccountGUIClient extends UserAccountGUI implements ActionListener {
	
	public UserAccountGUIClient(){
		searchButton.addActionListener(this);
		addToWishListButton.addActionListener(this);
		addToCurrentListButton.addActionListener(this);
		seeWishListButton.addActionListener(this);
		seeCurrentListButton.addActionListener(this);
		
		populateAllGames();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("searchButton")){
			System.out.println("You clicked the search button");
			
		}
		
		if (e.getActionCommand().equals("addToWishListButton")){
			System.out.println("You clicked add to wish list button");
			
		}
		
		if (e.getActionCommand().equals("addToCurrentListButton")){
			System.out.println("You clicked add to current list button");
		}
		
		if (e.getActionCommand().equals("seeWishListButton")){
			System.out.println("You clicked see wish list button");
		}
		
		if (e.getActionCommand().equals("seeWishListButton")){
			System.out.println("You clicked see wish list button");
		}
		
		if (e.getActionCommand().equals("seeCurrentListButton")){
			System.out.println("You clicked see current list button");
		}
		
	}
	
	public void populateAllGames(){
		DefaultTableModel newTable = new DefaultTableModel(new Object[] { 
				"Title", "Star Rate", "Genre", "Rating"	}, 0);
		
		ArrayList<Games> gamesList = Games.getAllGamesFromDatabase();
		
		for (Games g : gamesList)
		{
			Object[] row = { g.getGameTitle(), g.getGameRateStar(), 
							g.getGameGenre(), g.getGameRatingAge() };
			newTable.addRow(row);
		}
		
		table_2.setModel(newTable);
	}
}
