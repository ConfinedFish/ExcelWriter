package gui.table;

import XML.XMLParse;
import cards.CardSet;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.FilterCards;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class SetTableGUI extends TableGUI{
	private static final long serialVersionUID = 2294119736240909555L;
	private XMLParse xmlParse;
	public SetTableGUI(String title, XMLParse xmlParse){
		super(title, xmlParse);
		DeckEditor.println("Building Set List : " + title, Level.INFO);
		this.xmlParse = xmlParse;
		setSize(new Dimension(1050, 600));
		drawTable();

	}
	
	private void drawTable(){
		ArrayList<CardSet> sets = xmlParse.getSetArrayList();
		ArrayList<Field> fields = new ArrayList<>(Arrays.asList(CardSet.class.getDeclaredFields()));
		ArrayList<String> colnames = new ArrayList<>();
		fields.forEach(c -> colnames.add(c.getName()));
		DefaultTableModel model = new DefaultTableModel(colnames.toArray(), 0){
			private static final long serialVersionUID = 4914153800432984346L;
			
			@Override
			public boolean isCellEditable(int row, int col){
				int cardCol = 0;
				for (int i = 0; i < columnIdentifiers.size(); i++){
					if (columnIdentifiers.get(i).equals("cards")){
						cardCol = i;
					}
				}
				return col == cardCol;
			}
		};
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		applySearch(table, new FilterAction(), rowSorter);
		try{
			for (CardSet set : sets){
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (String name : colnames){
					if (name.startsWith("is"))
						methods.add(CardSet.class.getMethod(name));
					else{
						methods.add(CardSet.class
								.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)));
					}
				}
				for (Method meth : methods){
					values.add(meth.invoke(set));
				}
				model.addRow(values.toArray());
			}
		} catch (Exception e){
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
	
	private class FilterAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			new FilterCards(xmlParse);
			dispose();
		}
	}
}
