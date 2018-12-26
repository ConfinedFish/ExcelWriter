package main.java.gui.table;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import main.java.cards.CardSet;
import main.java.json.Jason;

public class SetTableGUI extends TableGUI{
	private static final long serialVersionUID = 2294119736240909555L;
	public SetTableGUI() {
		super("List of All Sets");
		setSize(new Dimension(1050, 600));
		drawTable();
	}
	private void drawTable() {
		ArrayList<CardSet> sets = Jason.sets;
		ArrayList<String> colnames = Jason.columnNames;
		DefaultTableModel model = new DefaultTableModel(colnames.toArray(), 0){
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
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);
		applySearch(table);
		try {
			for (int i = 0; i < sets.size(); i++) {
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (String name : colnames) {
					methods.add(
							CardSet.class.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)));
				}
				for (Method meth : methods) {
					values.add(meth.invoke(sets.get(i)));
				}
				model.addRow(values.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		table.getColumn("cards").setCellRenderer(new ButtonRenderer("cards"));
		table.getColumn("cards").setCellEditor(new SetButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
	}
}
