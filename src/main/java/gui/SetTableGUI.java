package main.java.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import main.java.cards.Card;
import main.java.cards.CardSet;
import main.java.json.Jason;

public class SetTableGUI extends JFrame {
	private static final long serialVersionUID = 2294119736240909555L;
	public SetTableGUI() {
		super("sets");
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
				}else {
					return false;
				}
			}
		};
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames);
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
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
		table.getColumn("cards").setCellRenderer(new ButtonRenderer());
		table.getColumn("cards").setCellEditor(new ButtonEditor(new JCheckBox()));
			JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scrollPane);
		}
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		public ButtonRenderer() {
			setOpaque(true);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : "cards");
			return this;
		}
	}
	class ButtonEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;
		protected JButton button;
		private boolean isPushed;
		private Object set;
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			set = value;
			isPushed = true;
			return button;
		}
		public Object getCellEditorValue() {
			ArrayList<Card> cards = new ArrayList<>();
			if (isPushed) {
				if (set instanceof ArrayList) {
					cards = (ArrayList<Card>)set;
					CardTableGUI table = new CardTableGUI(set);
					table.setVisible(true);
				}
			}
			isPushed = false;
			return cards;
		}
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
	}
}
