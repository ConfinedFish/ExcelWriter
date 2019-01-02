package gui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ItalicsRenderer extends DefaultTableCellRenderer{
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
												   int row, int column){
		Component tableCellRendererComponent =
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (value instanceof String){
			String string = (String) value;
			if (string.contains("#_")){
				setText(getHTML(string));
			}
		}
		return tableCellRendererComponent;
	}
	
	private String getHTML(String string){
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		int index = 0;
		while (index < string.length()){
			int next = string.indexOf("#_", index);
			if (next > -1){
				int end = string.indexOf("_#", next);
				if (end > -1){
					next++;
					sb.append(string, index, next);
					sb.append("<i>");
					sb.append(string, next, end);
					sb.append("</i>");
					index = end;
				} else{
					break;
				}
			} else{
				break;
			}
		}
		String split = string.substring(index);
		sb.append(split);
		sb.append("</html>");
		return sb.toString();
	}
}