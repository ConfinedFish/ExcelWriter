package gui.table.render;

import cards.Card;
import gui.table.CardTableGUI;
import gui.table.TableGUI;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class SetButtonEditor extends DefaultCellEditor{
	private static final long serialVersionUID = 1L;
	private final JButton button;
	private boolean isPushed;
	private String set;
	private Object value;
	
	public SetButtonEditor(JCheckBox checkBox){
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
				CardTableGUI table = new CardTableGUI(set, value, TableGUI.getXmlParse());
				table.setVisible(true);
				table.setLocationRelativeTo(editorComponent);
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
