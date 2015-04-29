package gui;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ModifiedJScrollPane extends JScrollPane {
	
	/*
	 * ModifiedJScrollPane() is a JScrollPane modified to specific
	 * 			details
	 */
	public ModifiedJScrollPane(){
		setOpaque(false);
		getViewport().setOpaque(false);
		setBorder(new LineBorder(new Color(173,216,230), 1));

	}
}
