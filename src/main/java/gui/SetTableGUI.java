package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.java.cards.Card;
import main.java.cards.CardSet;
import main.java.json.Jason;

public class SetTableGUI extends JFrame {
	private static final long serialVersionUID = 2294119736240909555L;
	public SetTableGUI() {
		super("List of All Sets");
		setSize(new Dimension(1050, 600));
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
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
		JTextField jtfFilter = new JTextField(50);
		table.setRowSorter(rowSorter);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Search:"), BorderLayout.WEST);
		panel.add(jtfFilter, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
		});
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
		resizeColumnWidth(table);
		table.getColumn("cards").setCellRenderer(new ButtonRenderer());
		table.getColumn("cards").setCellEditor(new ButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);
	}
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 30;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
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
		private Object value;
		private String set;
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
			this.value = value;
			TableModel model = table.getModel();
			int namecol = 0;
			for (int i = 0; i < model.getColumnCount(); i++)
				if (model.getColumnName(i).equals("name"))
					namecol = i;
			set = table.getModel().getValueAt(row, namecol).toString();
			isPushed = true;
			return button;
		}
		@SuppressWarnings("unchecked")
		public Object getCellEditorValue() {
			ArrayList<Card> cards = new ArrayList<>();
			if (isPushed) {
				if (value instanceof ArrayList) {
					cards = (ArrayList<Card>) value;
					CardTableGUI table = new CardTableGUI(set, value);
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
