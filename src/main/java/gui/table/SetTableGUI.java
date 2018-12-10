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

import main.java.cards.CardSet;
import main.java.json.Jason;

public class SetTableGUI extends TableGUI {
	private static final long serialVersionUID = 2294119736240909555L;
	public SetTableGUI() {
		super("List of All Sets");
		setSize(new Dimension(1050, 600));
		drawTable();
	}
	private void drawTable() {
		ArrayList<CardSet> sets = Jason.sets;
		Object[] colnames = Jason.columnNames.toArray();
		DefaultTableModel model = new DefaultTableModel(colnames, 0) {
			private static final long serialVersionUID = 4914153800432984346L;
			@Override
			public boolean isCellEditable(int row, int col) {
				int cardCol = 0;
				for (int i = 0; i < columnIdentifiers.size(); i++) {
					if (columnIdentifiers.get(i).equals("cards")) {
						cardCol = i;
					}
				}
				if (col == cardCol) {
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
		for (int i = 0; i < sets.size(); i++) {
			try {
				ArrayList<Method> methods = new ArrayList<>(Arrays.asList(CardSet.class.getMethods()));
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
					methods.add(CardSet.class.getMethod(name));
				}
				for (Method meth : methods) {
					values.add(meth.invoke(sets.get(i)));
				}
				model.addRow(values.toArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		table.getColumn("cards").setCellRenderer(new ButtonRenderer("cards"));
		table.getColumn("cards").setCellEditor(new SetButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
	}
	
}
