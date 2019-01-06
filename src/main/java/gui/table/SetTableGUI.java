package gui.table;

import XML.XMLParse;
import cards.CardSet;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.table.datamodel.SetDataModel;

import javax.swing.*;
import java.awt.*;
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
		ArrayList<String> colnames = new ArrayList<>(Arrays.asList("Cards", "Name", "Code", "Date", "Is Promo"));
		SetDataModel model = new SetDataModel(colnames.toArray(), 0, sets);
		JTable table = new JTable(model);
		configTable(table, null, this);
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		add(scrollPane);
	}
	
	
}
