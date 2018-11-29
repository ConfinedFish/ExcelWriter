package main.java.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.java.json.Jason;

public class GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField field;
	JLabel l;
	public GUI () {
		super(new GridLayout(1,0));
		ArrayList<String> names = Jason.readFile("AllCards.json");
		String[] colnames = {"names"};
		JTable table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(colnames);
		for (int i = 0; i < names.size(); i++) {
			Object[] rowData = new Object[4];
	        rowData[0] = names.get(i);
	        model.addRow(rowData);
		}

		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
		
	}
	public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Testing Cards");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUI newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
