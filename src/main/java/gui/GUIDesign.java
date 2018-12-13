/*
 * Created by JFormDesigner on Wed Dec 12 17:59:56 PST 2018
 */

package main.java.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.*;

/**
 * @author Alex Zeigler
 */
public class GUIDesign extends JFrame {
	public GUIDesign() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Alex Zeigler
		menuBar1 = new JMenuBar();
		file = new JMenu();
		open = new JMenuItem();
		close = new JMenuItem();
		save = new JMenuItem();
		saveas = new JMenuItem();
		importDeck = new JMenuItem();
		export = new JMenuItem();
		quit = new JMenuItem();
		cardDatabase = new JMenu();
		cards = new JMenuItem();
		sets = new JMenuItem();
		deckInfo = new JPanel();
		label1 = new JLabel();
		deckNameField = new JTextField();
		deckFormat = new JLabel();
		deckFormatField = new JComboBox();
		deckDescription = new JLabel();
		scrollPane1 = new JScrollPane();
		DeckDescriptionField = new JTextArea();
		contents = new JPanel();
		scrollPane2 = new JScrollPane();
		deckTable = new JTable();
		showCards = new JButton();
		showSets = new JButton();
		addLands = new JButton();
		showStats = new JButton();
		stats = new JPanel();
		cardnum = new JLabel();
		cardNumberField = new JLabel();

		//======== this ========
		setTitle("Deck Editor");
		Container contentPane = getContentPane();

		//======== menuBar1 ========
		{

			//======== file ========
			{
				file.setText("File");

				//---- open ----
				open.setText("open");
				open.setToolTipText("open a deck file");
				file.add(open);

				//---- close ----
				close.setText("close");
				file.add(close);

				//---- save ----
				save.setText("save");
				file.add(save);

				//---- saveas ----
				saveas.setText("save as");
				file.add(saveas);
				file.addSeparator();

				//---- importDeck ----
				importDeck.setText("import");
				file.add(importDeck);

				//---- export ----
				export.setText("export");
				file.add(export);
				file.addSeparator();

				//---- quit ----
				quit.setText("quit");
				file.add(quit);
			}
			menuBar1.add(file);

			//======== cardDatabase ========
			{
				cardDatabase.setText("Card Database");

				//---- cards ----
				cards.setText("show all cards");
				cardDatabase.add(cards);

				//---- sets ----
				sets.setText("show all sets");
				cardDatabase.add(sets);
			}
			menuBar1.add(cardDatabase);
		}
		setJMenuBar(menuBar1);

		//======== deckInfo ========
		{
			deckInfo.setBorder(new TitledBorder("Deck"));

			// JFormDesigner evaluation mark
			deckInfo.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), deckInfo.getBorder())); deckInfo.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


			//---- label1 ----
			label1.setText("Name:");

			//---- deckFormat ----
			deckFormat.setText("Format:");

			//---- deckDescription ----
			deckDescription.setText("Description(optional): ");

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(DeckDescriptionField);
			}

			GroupLayout deckInfoLayout = new GroupLayout(deckInfo);
			deckInfo.setLayout(deckInfoLayout);
			deckInfoLayout.setHorizontalGroup(
				deckInfoLayout.createParallelGroup()
					.addGroup(deckInfoLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(deckInfoLayout.createParallelGroup()
							.addComponent(deckFormat)
							.addComponent(label1))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(deckInfoLayout.createParallelGroup()
							.addComponent(deckFormatField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGroup(deckInfoLayout.createSequentialGroup()
								.addComponent(deckNameField, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
								.addGap(40, 40, 40)
								.addComponent(deckDescription)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(56, Short.MAX_VALUE))
			);
			deckInfoLayout.setVerticalGroup(
				deckInfoLayout.createParallelGroup()
					.addGroup(deckInfoLayout.createSequentialGroup()
						.addGroup(deckInfoLayout.createParallelGroup()
							.addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGroup(GroupLayout.Alignment.TRAILING, deckInfoLayout.createSequentialGroup()
								.addGroup(deckInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(label1)
									.addComponent(deckNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(deckDescription))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(deckInfoLayout.createParallelGroup()
									.addComponent(deckFormat)
									.addComponent(deckFormatField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
		}

		//======== contents ========
		{

			//======== scrollPane2 ========
			{

				//---- deckTable ----
				deckTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				scrollPane2.setViewportView(deckTable);
			}

			//---- showCards ----
			showCards.setText("Add Card(s)");

			//---- showSets ----
			showSets.setText("Browse Sets");

			//---- addLands ----
			addLands.setText("Add Land");

			//---- showStats ----
			showStats.setText("View Stats");

			GroupLayout contentsLayout = new GroupLayout(contents);
			contents.setLayout(contentsLayout);
			contentsLayout.setHorizontalGroup(
				contentsLayout.createParallelGroup()
					.addGroup(contentsLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(contentsLayout.createParallelGroup()
							.addGroup(contentsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(contentsLayout.createParallelGroup()
									.addComponent(showCards, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addComponent(showSets, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
								.addComponent(addLands, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
							.addComponent(showStats, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
						.addContainerGap())
			);
			contentsLayout.setVerticalGroup(
				contentsLayout.createParallelGroup()
					.addGroup(contentsLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(contentsLayout.createParallelGroup()
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
							.addGroup(contentsLayout.createSequentialGroup()
								.addComponent(showCards)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(showSets)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(addLands)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(showStats)))
						.addContainerGap(211, Short.MAX_VALUE))
			);
		}

		//======== stats ========
		{
			stats.setBorder(new TitledBorder("Stats"));

			//---- cardnum ----
			cardnum.setText("Cards:");

			//---- cardNumberField ----
			cardNumberField.setText("cardnumber");

			GroupLayout statsLayout = new GroupLayout(stats);
			stats.setLayout(statsLayout);
			statsLayout.setHorizontalGroup(
				statsLayout.createParallelGroup()
					.addGroup(statsLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(cardnum)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cardNumberField)
						.addContainerGap(1084, Short.MAX_VALUE))
			);
			statsLayout.setVerticalGroup(
				statsLayout.createParallelGroup()
					.addGroup(statsLayout.createSequentialGroup()
						.addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(cardnum)
							.addComponent(cardNumberField))
						.addGap(0, 60, Short.MAX_VALUE))
			);
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(contents, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(stats, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(deckInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 10, Short.MAX_VALUE)))
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addComponent(deckInfo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(stats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(contents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Alex Zeigler
	private JMenuBar menuBar1;
	private JMenu file;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuItem save;
	private JMenuItem saveas;
	private JMenuItem importDeck;
	private JMenuItem export;
	private JMenuItem quit;
	private JMenu cardDatabase;
	private JMenuItem cards;
	private JMenuItem sets;
	private JPanel deckInfo;
	private JLabel label1;
	private JTextField deckNameField;
	private JLabel deckFormat;
	private JComboBox deckFormatField;
	private JLabel deckDescription;
	private JScrollPane scrollPane1;
	private JTextArea DeckDescriptionField;
	private JPanel contents;
	private JScrollPane scrollPane2;
	private JTable deckTable;
	private JButton showCards;
	private JButton showSets;
	private JButton addLands;
	private JButton showStats;
	private JPanel stats;
	private JLabel cardnum;
	private JLabel cardNumberField;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
