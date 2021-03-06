package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class UserAccountGUI extends JPanel {
	//members
	protected JLabel userFirstName = null;
	protected JButton signOut;
	protected ModifiedJTextField txtTitle = new ModifiedJTextField();
	protected ModifiedJTextField txtStarRate = new ModifiedJTextField();
	protected ModifiedJTextField txtGenre = new ModifiedJTextField();
	protected ModifiedJTextField txtRating = new ModifiedJTextField();
	protected ModifiedJButtonField searchButton = new ModifiedJButtonField("Search");
	protected ModifiedJButtonField addToWishListButton = new ModifiedJButtonField("Add to wish list");
	protected ModifiedJButtonField addToCurrentListButton = new ModifiedJButtonField("Set as current game");
	protected ModifiedJButtonField updateToCurrentListButton = new ModifiedJButtonField("Switch to current list");
	protected ModifiedJButtonField deleteFromListButton = new ModifiedJButtonField("Delete from list");
	protected ModifiedJButtonField seeWishListButton = new ModifiedJButtonField("See wish list");
	protected ModifiedJButtonField seeCurrentListButton = new ModifiedJButtonField("See current list");
	protected static ModifiedJTable table_1;
	protected static ModifiedJTable table_2;
	
	/*
	 * UserAccountGUI() sets the layout of the User Account page when clicked on
	 */
	public UserAccountGUI(){
		setPreferredSize(new Dimension(1000, 650));
		setBackground(new Color(105,105,105));
		setLayout(null);
		
		txtTitle.setBounds(134, 64, 225, 31);
		add(txtTitle);
		txtTitle.setText("Title");
		txtTitle.addFocusListener(new ModifiedFocusAdapter(txtTitle, "Title"));

		txtStarRate.setBounds(134, 103, 225, 31);
		add(txtStarRate);
		txtStarRate.setText("Star Rate");
		txtStarRate.addFocusListener(new ModifiedFocusAdapter(txtStarRate, "Star Rate"));

		txtGenre.setBounds(134, 142, 225, 31);
		add(txtGenre);
		txtGenre.setText("Genre");
		txtGenre.addFocusListener(new ModifiedFocusAdapter(txtGenre, "Genre"));
		
		txtRating.setBounds(134, 181, 225, 31);
		add(txtRating);
		txtRating.setText("Rating");
		txtRating.addFocusListener(new ModifiedFocusAdapter(txtRating, "Rating"));
		
		searchButton.setBounds(184, 230, 125, 28);
		add(searchButton);
		searchButton.setActionCommand("searchButton");
		
		addToWishListButton.setBounds(92, 508, 125, 28);
		add(addToWishListButton);
		addToWishListButton.setActionCommand("addToWishListButton");
		
		addToCurrentListButton.setBounds(262, 508, 145, 28);
		add(addToCurrentListButton);
		addToCurrentListButton.setActionCommand("addToCurrentListButton");
		
		seeWishListButton.setBounds(580, 87, 145, 28);
		add(seeWishListButton);
		seeWishListButton.setActionCommand("seeWishListButton");
		
		seeCurrentListButton.setBounds(772, 87, 145, 28);
		add(seeCurrentListButton);
		seeCurrentListButton.setActionCommand("seeCurrentListButton");
		
		updateToCurrentListButton.setBounds(569, 487, 178, 28);
		add(updateToCurrentListButton);
		updateToCurrentListButton.setActionCommand("updateToCurrentListButton");
		
		deleteFromListButton.setBounds(797, 487, 145, 28);
		add(deleteFromListButton);
		deleteFromListButton.setActionCommand("deleteFromListButton");
		
		ModifiedJScrollPane scrollPane_1 = new ModifiedJScrollPane();
		scrollPane_1.setBounds(538, 126, 430, 350);
		add(scrollPane_1);
		table_1 = new ModifiedJTable(new DefaultTableModel(null, new Object[] {"Title", "Star Rate", "Genre", "Rating"}));
		scrollPane_1.setViewportView(table_1);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(200);
		
		ModifiedJScrollPane scrollPane_2 = new ModifiedJScrollPane();
		scrollPane_2.setBounds(45, 289, 406, 208);
		add(scrollPane_2);
		table_2 = new ModifiedJTable(new DefaultTableModel(null, new Object[] {"Title", "Star Rate", "Genre", "Rating" }));
		scrollPane_2.setViewportView(table_2);
		table_2.getColumnModel().getColumn(0).setPreferredWidth(200);

	}
}
