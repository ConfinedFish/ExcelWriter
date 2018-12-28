package jformdesign;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/*
 * Created by JFormDesigner on Wed Dec 12 01:07:11 PST 2018
 */

/**
 * @author Alex Zeigler
 */
class CardView extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 4341407364947051367L;
	public CardView() {
		initComponents();
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Alex Zeigler
		// ======== this ========
		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
				new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM,
				new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder()));
		addPropertyChangeListener(e -> {
			if ("border".equals(e.getPropertyName()))
				throw new RuntimeException();
		});
		setLayout(new MigLayout("hidemode 3",
				// columns
				"[fill]" + "[fill]",
				// rows
				"[]" + "[]" + "[]"));
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}
	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Alex Zeigler
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
