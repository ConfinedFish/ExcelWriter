package gui.table.render;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer{
	private static final long serialVersionUID = 1L;
	private final String name;
	
	public ButtonRenderer(String name){
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
