package gui.table.datamodel;

import cards.CardSet;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SetDataModel extends DefaultTableModel{
	public SetDataModel(Object[] colnames, int rowCount, ArrayList<CardSet> sets){
		super(colnames, rowCount);
		setColumnIdentifiers(colnames);
		generateData(colnames, sets);
	}
	
	private void generateData(Object[] colnames, ArrayList<CardSet> sets){
		try{
			for (CardSet set : sets){
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (Object name : colnames){
					if (name instanceof String){
						String stringname = (String) name;
						if (stringname.equalsIgnoreCase("is promo"))
							methods.add(CardSet.class.getMethod(stringname.replaceAll(" ", "_").toLowerCase()));
						else{
							methods.add(CardSet.class
									.getMethod("get" + stringname.substring(0, 1).toUpperCase() +
											stringname.substring(1)));
						}
					}
				}
				for (Method meth : methods){
					if (meth.getName().equalsIgnoreCase("getcards")){
						values.add("View Cards");
					} else if (meth.getName().equalsIgnoreCase("is_promo")){
						values.add((boolean)meth.invoke(set) ? "Y" : "N");
					} else{
						values.add(meth.invoke(set));
					}
				}
				addRow(values.toArray());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
//		int cardCol = 0;
//		for (int i = 0; i < columnIdentifiers.size(); i++){
//			if (columnIdentifiers.get(i).equals("cards")){
//				cardCol = i;
//			}
//		}
//		return col == cardCol;
		return false;
	}
}
