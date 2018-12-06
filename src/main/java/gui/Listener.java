package main.java.gui;

import java.awt.TextField;
import java.awt.event.ActionEvent;

public class Listener implements java.awt.event.ActionListener {
	private TextField butt;
	public Listener(TextField butt) {
		this.butt = butt;
	}
	public TextField getButt() {
		return butt;
	}
	public void TextField(TextField butt) {
		this.butt = butt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		butt.setText("Hello");
	}
}
