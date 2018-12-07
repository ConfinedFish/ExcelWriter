package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import main.java.cards.Card;
import main.java.json.Jason;

public class CardTableGUI extends JFrame {
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
				switch (col) {
					case 9:
					case 2:
						return true;
					default:
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
		for (int i = 0; i < cards.size(); i++) {
			Object[] data = { cards.get(i).getColorIdentity(), cards.get(i).getCMC(), cards.get(i).getLegalities(),
					cards.get(i).getManaCost(), cards.get(i).getName(), cards.get(i).getDesc(), cards.get(i).getPower(),
					cards.get(i).getPrintings(), cards.get(i).getRarity(),
					cards.get(i).getSubtype().isEmpty() ? "" : cards.get(i).getSubtype(), cards.get(i).getSuperType(),
					cards.get(i).getToughness() };
			model.addRow(data);
		}
		table.getColumn("legalities").setCellRenderer(new ButtonRenderer("legalities"));
		table.getColumn("legalities").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("subtypes").setCellRenderer(new ButtonRenderer("subtypes"));
		table.getColumn("subtypes").setCellEditor(new ButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		add(scrollPane);
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		private String name;
		public ButtonRenderer(String name) {
			setOpaque(true);
			this.name = name;
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
			setText((value == null) ? "" : name);
			return this;
		}
	}
	class ButtonEditor extends DefaultCellEditor {
		private static final long serialVersionUID = 1L;
		protected JButton button;
		private String label;
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
			label = (value == null) ? "" : value.toString();
			isPushed = true;
			return button;
		}
		public Object getCellEditorValue() {
			if (isPushed) {
				if (set instanceof ArrayList) {
					ArrayList<?> list = (ArrayList<?>) set;
					if (!list.isEmpty()) {
						StringBuilder builder = new StringBuilder();
						for (Object o : list) {
							builder.append(o.toString() + "\n");
						}
						JOptionPane.showMessageDialog(new JFrame(), builder.toString(), set.toString(), 1);
					}
				}
			}
			isPushed = false;
			return new String(label);
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
