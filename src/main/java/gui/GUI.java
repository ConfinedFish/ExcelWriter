package main.java.gui;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.cards.Card;
import main.java.json.Jason;

public class GUI extends JPanel {
	private static final long serialVersionUID = -1066050764201645094L;
	TextField field;
	JLabel l;
	public GUI() {
		Jason.readFileForSets("AllSets.json");
		JButton setButton = new JButton("Show Sets");
		setButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetTableGUI setGUI = new SetTableGUI();
				setGUI.setVisible(true);
			}
		});
		
		
		JButton cardButton = new JButton("Show Cards");
		cardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Card> c = Jason.dictonary.getList();
				CardTableGUI cardGUI = new CardTableGUI("All Cards", c);
				cardGUI.setVisible(true);
			}
		});
		add (cardButton);
		add (setButton);
		setSize(new Dimension(500, 600));
	}
	
	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Main GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI newContentPane = new GUI();
		newContentPane.setOpaque(true); 
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}

}
