package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import gui.UserAccountGUI;

public class UserAccountGUIClient extends UserAccountGUI implements ActionListener {
	
	public UserAccountGUIClient(){
		submitSearchButton.addActionListener(this);
		addGameButton.addActionListener(this);
		
		populateAllGames();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
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
