package gui.table.datamodel;

import cards.Card;
import cards.CardSet;
import deckeditor.DeckEditor;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CardDataModel extends DefaultTableModel{
	public CardDataModel(Object[] colnames, int rowCount, ArrayList<Card> cards){
		super(colnames, rowCount);
		setColumnIdentifiers(colnames);
		generateData(colnames, cards);
	}
	
	private void generateData(Object[] colnames, ArrayList<Card> cards){
		try{
			for (Card card : cards){
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (Object name : colnames){
					if (name instanceof String){
						String stringname = (String)name;
						stringname = stringname.replaceAll(" ", "");
						if (stringname.startsWith("is")){
							methods.add(Card.class.getMethod(stringname));
							
						} else{
							methods.add(
									Card.class.getMethod("get" + stringname.substring(0, 1).toUpperCase() + stringname.substring(1)));
						}
					}
				}
				for (Method meth : methods){
					if (meth.getName().equalsIgnoreCase("getset")){
						CardSet set = (CardSet) meth.invoke(card);
						values.add(set.getCode());
					} else{
						try {
							values.add(meth.invoke(card));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							DeckEditor.printException(card.getName() + ", " + meth.getName(), e);
						}
					}
				}
				addRow(values.toArray());
			}
		} catch (Exception e){
			DeckEditor.printException("GUITableModel", e);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	@Override
	public Class getColumnClass(int column){
		return Object.class;
	}
}
