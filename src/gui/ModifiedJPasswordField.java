package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ModifiedJPasswordField extends JPasswordField {
	
	/*
	 * ModifiedJPasswordField() sets up a modified JPassword
	 * 			with specific color and font
	 */
	public ModifiedJPasswordField(){
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Elephant", Font.PLAIN,14));
		setForeground(Color.GRAY);
		setBackground(new Color(220,220,220));
		setBorder(new LineBorder(Color.GRAY, 1));
	}
}
