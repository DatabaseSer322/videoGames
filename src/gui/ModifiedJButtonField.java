package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ModifiedJButtonField extends JButton {
	
	/*
	 * ModifiedButtonField(String text) takes in a string and sets up a
	 * 			JButton with the specific color and font
	 */
	public ModifiedJButtonField(String text){
		super(text);
		setBorder(new LineBorder(new Color(105,105,105),1));
		setContentAreaFilled(false);
		setForeground(new Color(173,216,230));
		setFont(new Font("Elephant", Font.PLAIN, 14));
	}
}
