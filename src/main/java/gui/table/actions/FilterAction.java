package gui.table.actions;

import gui.FilterCards;
import gui.table.TableGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterAction implements ActionListener{
	private TableGUI gui;
	
	public FilterAction(TableGUI gui){this.gui = gui;}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new FilterCards(TableGUI.getXmlParse());
		gui.dispose();
	}
}
