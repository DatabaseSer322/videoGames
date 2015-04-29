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
	protected ModifiedJButtonField submitSearchButton = new ModifiedJButtonField("Search");
	protected ModifiedJButtonField addGameButton = new ModifiedJButtonField("Add Game");
	protected ModifiedJTable table_1;
	protected ModifiedJTable table_2;
	
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
		
		submitSearchButton.setBounds(184, 230, 125, 28);
		add(submitSearchButton);
		submitSearchButton.setActionCommand("submitSearchButton");
		
		addGameButton.setBounds(461, 469, 125, 28);
		add(addGameButton);
		addGameButton.setActionCommand("addGameButton");
		
		
		ModifiedJScrollPane scrollPane_1 = new ModifiedJScrollPane();
		scrollPane_1.setBounds(538, 126, 430, 214);
		add(scrollPane_1);
		table_1 = new ModifiedJTable(new DefaultTableModel(null, new Object[] {"Title", "Star Rate", "Genre", "Rating"}));
		scrollPane_1.setViewportView(table_1);
		
		ModifiedJScrollPane scrollPane_2 = new ModifiedJScrollPane();
		scrollPane_2.setBounds(45, 289, 406, 208);
		add(scrollPane_2);
		table_2 = new ModifiedJTable(new DefaultTableModel(null, new Object[] {"Title", "Star Rate", "Genre", "Rating" }));
		scrollPane_2.setViewportView(table_2);

	}
}
