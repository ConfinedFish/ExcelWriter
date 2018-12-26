package main.java.gui.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.java.cards.Card;
import main.java.gui.FilterCards;

public class TableGUI extends JFrame{
	class ButtonRenderer extends JButton implements TableCellRenderer{
		private static final long serialVersionUID = 1L;
		private String name;
		public ButtonRenderer(String name) {
			setOpaque(true);
			this.name = name;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText(value == null ? "" : name);
			return this;
		}
	}
	class CardButtonEditor extends DefaultCellEditor{
		private static final long serialVersionUID = 1L;
		protected JButton button;
		private boolean isPushed;
		private Object value;
		public CardButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}
		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
		@Override
		public Object getCellEditorValue() {
			ArrayList<Card> cards = new ArrayList<>();
			if (isPushed) {
				if (value instanceof ArrayList) {
					if (value instanceof ArrayList) {
						ArrayList<?> list = (ArrayList<?>) value;
						if (!list.isEmpty()) {
							StringBuilder builder = new StringBuilder();
							for (Object o : list) {
								builder.append(o.toString().toUpperCase() + "\n");
							}
							JOptionPane.showMessageDialog(new JFrame(), builder.toString());
						}
					}
				}
			}
			isPushed = false;
			return cards;
		}
		@Override
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
			isPushed = true;
			return button;
		}
		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}
	class SetButtonEditor extends DefaultCellEditor{
		private static final long serialVersionUID = 1L;
		protected JButton button;
		private boolean isPushed;
		private String set;
		private Object value;
		public SetButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					fireEditingStopped();
				}
			});
		}
		@Override
		protected void fireEditingStopped() {
			super.fireEditingStopped();
		}
		@Override
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
		@Override
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
		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}
	private static final long serialVersionUID = 6779133700049319554L;
	public TableGUI(String name) {
		super(name);
	}
	void applySearch(JTable table) {
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		JTextField jtfFilter = new JTextField(20);
		table.setRowSorter(rowSorter);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Search:"));
		panel.add(jtfFilter, BorderLayout.WEST);
		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new FilterCards("Filter Cards");
				dispose();
			}
		});
		panel.add(button, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		jtfFilter.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
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
		});
	}
	JTable resizeTable(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}
			if (width > 150)
				width = 150;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
		table.setRowHeight(20);
		return table;
	}
}
