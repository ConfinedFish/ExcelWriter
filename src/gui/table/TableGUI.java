package gui.table;


import cards.Card;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class TableGUI extends JFrame{
	private static final long serialVersionUID = 6779133700049319554L;
	TableGUI(String title){
		super(title);
	}
	void applySearch(JTable table, ActionListener actionListener){
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		JTextField jtfFilter = new JTextField(20);
		table.setRowSorter(rowSorter);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Search:"));
		panel.add(jtfFilter, BorderLayout.WEST);
		JButton button = new JButton("Filter");
		button.addActionListener(actionListener);
		panel.add(button, BorderLayout.EAST);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		add(new JScrollPane(table), BorderLayout.CENTER);
		jtfFilter.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0){
			}
			
			@Override
			public void insertUpdate(DocumentEvent e){
				String text = jtfFilter.getText();
				if (text.trim().length() == 0){
					rowSorter.setRowFilter(null);
				} else{
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent e){
				String text = jtfFilter.getText();
				if (text.trim().length() == 0){
					rowSorter.setRowFilter(null);
				} else{
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
	}
	void addClickListener(JTable jtable){
		jtable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				JTable table =(JTable) e.getSource();
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					JOptionPane.showMessageDialog(new JFrame(),
							table.getValueAt(row, col).toString().replaceAll(";", "\n"));
				}
			}
		});
	}
	void resizeTable(JTable table){
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++){
			int width = 15;
			for (int row = 0; row < table.getRowCount(); row++){
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}
			if (width > 150)
				width = 150;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
		table.setRowHeight(20);
	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer{
		private static final long serialVersionUID = 1L;
		private final String name;
		
		ButtonRenderer(String name){
			setOpaque(true);
			this.name = name;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
													   boolean hasFocus,
													   int row, int column){
			if (isSelected){
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else{
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText(value == null ? "" : name);
			return this;
		}
	}
}

class CardButtonEditor extends DefaultCellEditor{
	private static final long serialVersionUID = 1L;
	private final JButton button;
	private boolean isPushed;
	private Object value;
	
	CardButtonEditor(JCheckBox checkBox){
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(e -> fireEditingStopped());
	}
	
	static void setBackAndForground(JTable table, boolean isSelected, JButton button){
		if (isSelected){
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else{
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
	}
	
	@Override
	protected void fireEditingStopped(){
		super.fireEditingStopped();
	}
	
	@Override
	public Object getCellEditorValue(){
		ArrayList<Card> cards = new ArrayList<>();
		if (isPushed){
			if (value instanceof ArrayList){
				ArrayList<?> list = (ArrayList<?>) value;
				if (!list.isEmpty()){
					StringBuilder builder = new StringBuilder();
					for (Object o : list){
						builder.append(o.toString().toUpperCase()).append("\n");
					}
					JOptionPane.showMessageDialog(new JFrame(), builder.toString());
				}
			}
		}
		isPushed = false;
		return cards;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
												 int column){
		setBackAndForground(table, isSelected, button);
		this.value = value;
		isPushed = true;
		return button;
	}
	
	@Override
	public boolean stopCellEditing(){
		isPushed = false;
		return super.stopCellEditing();
	}
}

class SetButtonEditor extends DefaultCellEditor{
	private static final long serialVersionUID = 1L;
	private final JButton button;
	private boolean isPushed;
	private String set;
	private Object value;
	
	SetButtonEditor(JCheckBox checkBox){
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(e -> fireEditingStopped());
	}
	
	@Override
	protected void fireEditingStopped(){
		super.fireEditingStopped();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object getCellEditorValue(){
		ArrayList<Card> cards = new ArrayList<>();
		if (isPushed){
			if (value instanceof ArrayList){
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
												 int column){
		CardButtonEditor.setBackAndForground(table, isSelected, button);
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
	public boolean stopCellEditing(){
		isPushed = false;
		return super.stopCellEditing();
	}
	
}
