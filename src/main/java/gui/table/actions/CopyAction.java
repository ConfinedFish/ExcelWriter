package gui.table.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;

public class CopyAction extends AbstractAction{

	private JTable table;

	public CopyAction(JTable table) {
		this.table = table;
		putValue(NAME, "Copy");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(new CellTransferable(table.getValueAt(row, col)), null);
	}

}
