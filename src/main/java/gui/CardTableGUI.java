package main.java.gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.java.cards.Card;
import main.java.cards.type.Color;
import main.java.json.Jason;

public class CardTableGUI extends JFrame {
	private static final long serialVersionUID = -8543861956071891649L;
	@SuppressWarnings("unchecked")
	public CardTableGUI(Object set) {
		super("cards");
		drawTable(set instanceof ArrayList ? (ArrayList<Card>)set : new ArrayList<>());
	}
	public CardTableGUI() {
		super("cards");
		drawTable(Jason.dictonary.getList());
	}
	private void drawTable(ArrayList<Card> cards) {
		Object[] colnames = Jason.cardColumnNames.toArray();
		DefaultTableModel model = new DefaultTableModel(colnames, 0);
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames);
		table.setModel(model);
		for (int i = 0; i < cards.size(); i++) {
			ArrayList<Color> colorIdentity = cards.get(i).getColorIdentity();
			Object[] data = {colorIdentity, cards.get(i).getCMC(), cards.get(i).getLegalities().toString(), cards.get(i).getName()};
			model.addRow(data);
		}
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		add(scrollPane);
	}
}
