package main.java.gui.table;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.java.cards.Card;
import main.java.json.Jason;

public class CardTableGUI extends TableGUI {
	private static final long serialVersionUID = -8543861956071891649L;
	@SuppressWarnings("unchecked")
	public CardTableGUI(String title, Object set) {
		super(title);
		drawTable(set instanceof ArrayList ? (ArrayList<Card>) set : new ArrayList<>());
		setSize(new Dimension(1050, 600));
	}
	private void drawTable(ArrayList<Card> cards) {
		Object[] colnames = Jason.cardColumnNames.toArray();
		DefaultTableModel model = new DefaultTableModel(colnames, 0) {
			private static final long serialVersionUID = -6550280855835102010L;
			@Override
			public boolean isCellEditable(int row, int col) {
				int subCol = 0, legCol = 0;
				for (int i = 0; i < columnIdentifiers.size(); i++) {
					if (columnIdentifiers.get(i).equals("subtypes"))
						subCol = i;
					if (columnIdentifiers.get(i).equals("legalities"))
						legCol = i;
				}
				if (col == subCol || col == legCol) {
					return true;
				} else {
					return false;
				}
			}
		};
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames);
		table.setModel(model);
		applySearch(table);
		try {
			for (int i = 0; i < cards.size(); i++) {
				ArrayList<Method> methods = new ArrayList<>(Arrays.asList(Card.class.getMethods()));
				ArrayList<String> methodNames = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (Method meth : methods) {
					if (meth.getName().startsWith("get") && !meth.getName().equals("getClass")) {
						methodNames.add(meth.getName());
					}
				}
				Collections.sort(methodNames);
				methods.clear();
				for (String name : methodNames) {
					methods.add(Card.class.getMethod(name));
				}
				for (Method meth : methods) {
					values.add(meth.invoke(cards.get(i)));
				}
				model.addRow(values.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.getColumn("legalities").setCellRenderer(new ButtonRenderer("legalities"));
		table.getColumn("legalities").setCellEditor(new CardButtonEditor(new JCheckBox()));
		table.getColumn("subtypes").setCellRenderer(new ButtonRenderer("subtypes"));
		table.getColumn("subtypes").setCellEditor(new CardButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		add(scrollPane);
	}
}
