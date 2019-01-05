package gui.table;

import XML.XMLParse;
import cards.Card;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.FilterCards;
import gui.table.datamodel.CardDataModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class CardTableGUI extends TableGUI {
	private static final long serialVersionUID = -8543861956071891649L;

	@SuppressWarnings("unchecked")
	public CardTableGUI(String title, Object data, XMLParse xmlParse) {
		super(title, xmlParse);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeckEditor.println("Building Deck List : " + title, Level.INFO);
		drawTable(data instanceof ArrayList ? (ArrayList<Card>) data : new ArrayList<>());
		setSize(new Dimension(1050, 600));
	}

	private void drawTable(ArrayList<Card> cards) {
		ArrayList<String> colnames = new ArrayList<>(Arrays.asList("Name", "Type", "Set", "Ability", "Flavor Text", "Symbols",
				"Converted Mana Cost", "Power", "Toughness", "Loyalty", "Rarity", "Color", "Color Identity"));
		CardDataModel model = new CardDataModel(colnames.toArray(), 0, cards);
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		rowSorter.setComparator(model.findColumn("Converted Mana Cost"), new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		final JPopupMenu pm = new JPopupMenu();
		pm.add(new CopyAction(table));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isPopupTrigger()) {
					highlightRow(e);
					doPopup(e);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					highlightRow(e);
					doPopup(e);
				}
			}

			void doPopup(MouseEvent e) {
				pm.show(e.getComponent(), e.getX(), e.getY());
			}

			void highlightRow(MouseEvent e) {
				JTable table = (JTable) e.getSource();
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);
				int col = table.columnAtPoint(point);

				table.setRowSelectionInterval(row, row);
				table.setColumnSelectionInterval(col, col);
			}

		});
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		table.setDefaultRenderer(Object.class, renderer);
		table.setRowSorter(rowSorter);
		applySearch(table, new FilterAction(), rowSorter);
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		addClickListener(table);
		add(scrollPane);
	}

	private class FilterAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new FilterCards(getXmlParse());
			dispose();
		}
	}

	public class CopyAction extends AbstractAction {

		private JTable table;

		CopyAction(JTable table) {
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

	public static class CellTransferable implements Transferable {

		static final DataFlavor CELL_DATA_FLAVOR = new DataFlavor(Object.class, "application/x-cell-value");

		private Object cellValue;

		CellTransferable(Object cellValue) {
			this.cellValue = cellValue;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[]{CELL_DATA_FLAVOR};
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return CELL_DATA_FLAVOR.equals(flavor);
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if (!isDataFlavorSupported(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return cellValue;
		}
	}
}
