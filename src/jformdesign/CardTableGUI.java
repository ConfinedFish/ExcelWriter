/*
 * Created by JFormDesigner on Wed Dec 12 18:06:46 PST 2018
 */
package jformdesign;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alex Zeigler
 */
class CardTableGUI extends JFrame{
	/**
	 *
	 */
	private static final long serialVersionUID = -3561313244463911808L;
	
	public CardTableGUI() {
		initComponents();
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Alex Zeigler
		// JFormDesigner - End of variables declaration //GEN-END:variables
		// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Alex Zeigler
		JPanel panel1 = new JPanel();
		JScrollPane scrollPane1 = new JScrollPane();
		JTable cardTable = new JTable();
		// ======== this ========
		Container contentPane = getContentPane();
		// ======== panel1 ========
		{
			panel1.setBorder(null);
			// JFormDesigner evaluation mark
			panel1.setBorder(
					new javax.swing.border.CompoundBorder(
							new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
									"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
									javax.swing.border.TitledBorder.BOTTOM,
									new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red),
							panel1.getBorder()));
			panel1.addPropertyChangeListener(e -> {
				if ("border".equals(e.getPropertyName()))
					throw new RuntimeException();
			});
			// ======== scrollPane1 ========
			{
				scrollPane1.setViewportView(cardTable);
			}
			GroupLayout panel1Layout = new GroupLayout(panel1);
			panel1.setLayout(panel1Layout);
			panel1Layout.setHorizontalGroup(panel1Layout.createParallelGroup().addComponent(scrollPane1,
					GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE));
			panel1Layout.setVerticalGroup(panel1Layout.createParallelGroup()
					.addGroup(panel1Layout
							.createSequentialGroup().addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE,
									GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 0, Short.MAX_VALUE)));
		}
		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout.createParallelGroup().addComponent(panel1,
				GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		contentPaneLayout
				.setVerticalGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup().addContainerGap().addComponent(panel1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(130, Short.MAX_VALUE)));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}
}
