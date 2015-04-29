package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ModifiedJTextField extends JTextField {
	
	//private static final long serialVersionUID = 1L;

	/*
	 * ModifiedJTextField() sets the JTextFields to a specific
	 * 			color and font
	 */
	public ModifiedJTextField(){
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Elephant", Font.PLAIN, 14));
		setForeground(Color.GRAY);
		setBackground(new Color(220,220,220));
		setBorder(new LineBorder(Color.GRAY, 1));
	}
}
