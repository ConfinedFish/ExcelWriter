package gui.table;

import XML.XMLParse;
import cards.Card;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.table.datamodel.CardDataModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CardTableGUI extends TableGUI{
	private static final long serialVersionUID = -8543861956071891649L;
	
	@SuppressWarnings("unchecked")
	public CardTableGUI(String title, Object data, XMLParse xmlParse){
		super(title, xmlParse);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeckEditor.println("Building Deck List : " + title, Level.INFO);
		drawTable(data instanceof ArrayList ? (ArrayList<Card>) data : new ArrayList<>());
		setSize(new Dimension(1050, 600));
	}
	
	private void drawTable(ArrayList<Card> cards){
		ArrayList<String> colnames =
				new ArrayList<>(Arrays.asList("Name", "Type", "Set", "Ability", "Flavor Text", "Symbols",
						"Converted Mana Cost", "Power", "Toughness", "Loyalty", "Rarity", "Color", "Color Identity"));
		CardDataModel model = new CardDataModel(colnames.toArray(), 0, cards);
		JTable table = new JTable(model);
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
		rowSorter.setComparator(model.findColumn("Converted Mana Cost"), (Comparator<Integer>) Integer::compareTo);
		table.setRowSorter(rowSorter);
		configTable(table, rowSorter, this);
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		add(scrollPane);
	}
}
