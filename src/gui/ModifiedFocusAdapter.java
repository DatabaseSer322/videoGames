package gui;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class ModifiedFocusAdapter extends FocusAdapter {

	//members
	protected JTextField textField = new JTextField();
	protected String caption;
	
	/*
	 * ModifiedFocusAdapter(JTextField text, String cap) constructor
	 * 				assigns members their values
	 */
	ModifiedFocusAdapter(JTextField text, String cap){
		this.textField = text;
		this.caption = cap;
	}
	
	/*
	 * focusGained(FocusEvent e) sets the text field to empty when
	 * 			clicked into it
	 */
	public void focusGained(FocusEvent e){
		if (textField.getText().equals(caption)){
			textField.setText("");
		}
	}
	
	/*
	 * focusLost(FocusEvent e) sets the text field to the caption
	 * 			when clicked out of it and nothing has been edited 
	 */
	public void focusLost(FocusEvent e){
		if (textField.getText().equals("")){
			textField.setText(caption);
		}
	}
}