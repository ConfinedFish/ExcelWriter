package gui.table;

import XML.XMLParse;
import cards.CardSet;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.FilterCards;
import gui.table.datamodel.SetDataModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
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
		SetDataModel model = new SetDataModel(colnames.toArray(), 0, sets);
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		applySearch(table, new FilterAction(), rowSorter);
		
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
