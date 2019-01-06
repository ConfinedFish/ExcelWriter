package gui.table.render;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
