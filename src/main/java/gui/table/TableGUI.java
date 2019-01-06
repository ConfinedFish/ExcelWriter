package gui.table;


import XML.XMLParse;
import cards.CardSet;
import deckeditor.DeckEditor;
import gui.table.actions.CopyAction;
import gui.table.actions.FilterAction;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableGUI extends JFrame{
	private static XMLParse xmlParse;
	
	public static XMLParse getXmlParse(){
		return xmlParse;
	}
	
	private static final long serialVersionUID = 6779133700049319554L;
	
	TableGUI(String title, XMLParse xmlParse){
		super(title);
		TableGUI.xmlParse = xmlParse;
	}
	
	void configTable(JTable table, TableRowSorter<TableModel> rowSorter, TableGUI tableGUI){
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		table.setDefaultRenderer(Object.class, renderer);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		addCopyMenu(table);
		applySearch(table, new FilterAction(tableGUI), rowSorter);
		addClickListener(table);
		resizeTable(table);
	}
	
	private void applySearch(JTable table, ActionListener actionListener, TableRowSorter<TableModel> rowSorter){
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder("Search:"));
		JTextField jtfFilter = new JTextField(20);
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
	
	private void addClickListener(JTable jtable){
		jtable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				JTable table = (JTable) e.getSource();
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if (e.getClickCount() == 2 && table.getSelectedRow() != -1){
					if (table.getModel().getColumnName(table.getSelectedColumn()).equalsIgnoreCase("set")){
						CardSet set = xmlParse.getDictonary().findSet(table.getValueAt(row, col).toString());
						try{
							JOptionPane.showMessageDialog(new JFrame(),
									set.getName() + "\n" + set.getCode() + "\n" + set.getDate());
						} catch (Exception e1){
							DeckEditor.printException(table.getValueAt(row, col).toString(), e1);
						}
					} else{
						JOptionPane.showMessageDialog(new JFrame(),
								table.getValueAt(row, col).toString().replaceAll("; ", "\n"));
					}
				}
				if (e.getClickCount() == 1 && table.getSelectedRow() != -1){
					if (table.getModel().getColumnName(table.getSelectedColumn()).equalsIgnoreCase("cards")){
						CardSet set =
								xmlParse.getDictonary().findSet(table.getValueAt(row,
										table.getColumn("Code").getModelIndex()).toString());
						CardTableGUI gui = new CardTableGUI(set.getName(), set.getCards(), xmlParse);
						gui.setVisible(true);
					}
				}
			}
		});
	}
	
	private void resizeTable(JTable table){
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
	
	private void addCopyMenu(JTable table){
		final JPopupMenu pm = new JPopupMenu();
		pm.add(new CopyAction(table));
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if (e.isPopupTrigger()){
					highlightRow(e);
					doPopup(e);
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				if (e.isPopupTrigger()){
					highlightRow(e);
					doPopup(e);
				}
			}
			
			void doPopup(MouseEvent e){
				pm.show(e.getComponent(), e.getX(), e.getY());
			}
			
			void highlightRow(MouseEvent e){
				JTable table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);
				int col = table.columnAtPoint(point);
				
				table.setRowSelectionInterval(row, row);
				table.setColumnSelectionInterval(col, col);
			}
			
		});
	}
	
}

