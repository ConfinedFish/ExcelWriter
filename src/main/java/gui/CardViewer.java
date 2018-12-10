package main.java.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.java.cards.Card;

public class CardViewer extends JFrame {
	private static final long serialVersionUID = 6839301060826162665L;
	private Card card;
	public CardViewer(Card card) {
		super(card.getName());
		this.card = card;
		drawUI();
	}
	JPanel makePanel() {
		JPanel jp = new JPanel();
		jp.setBorder(new TitledBorder(card.getName()));
		return jp;
	}
	public void init() {
		Container cp = getContentPane();
	    cp.setLayout(new FlowLayout());
	    cp.add(makePanel());
	}
	private void drawUI() {
	    setSize(new Dimension(200, 300));
	    init();
	    setVisible(true);
	}
}
