package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;

class JNumberTextField extends JTextField{
	private static final long serialVersionUID = 1L;
	
	/**
	 * As the user is not even able to enter a dot ("."), only integers (whole
	 * numbers) may be entered.
	 */
	public Long getNumber(){
		Long result = null;
		String text = getText();
		if (text != null && !"".equals(text)){
			result = Long.valueOf(text);
		}
		return result;
	}
	
	@Override
	public void processKeyEvent(KeyEvent ev){
		if (Character.isDigit(ev.getKeyChar()) || getText().length() >= 2
				|| ev.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if (getText().length() >= 2 && !(ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
				ev.consume();
			} else{
				super.processKeyEvent(ev);
			}
		}
		ev.consume();
	}
}
