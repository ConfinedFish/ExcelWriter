package gui;


import cards.Card;
import cards.Deck;
import cards.type.Format;
import gui.table.CardTableGUI;
import gui.table.SetTableGUI;
import json.Jason;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame{
	private static final long serialVersionUID = -1066050764201645094L;
	private JLabel cardNumberField;
	private Deck deck;
	private JTable deckTable;
	
	public GUI() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		initComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	private JTable drawTable(JTable table) {
		ArrayList<String> colnames = new ArrayList<>();
		colnames.add("name");
		colnames.add("number");
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(colnames.toArray());
		if (deck != null) {
			try {
				for (ArrayList<Card> listofCards : deck) {
					ArrayList<Object> values = new ArrayList<>();
					values.add(listofCards.get(0).getName());
					values.add(listofCards.size());
					model.addRow(values.toArray());
					model.fireTableDataChanged();
				}
				table.setModel(model);
				model.fireTableDataChanged();
				int size = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					size += Integer.parseInt(table.getValueAt(i, 2) + "");
				}
				cardNumberField.setText(String.valueOf(size));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return table;
	}
	private void initComponents() {
		JMenuBar menuBar1 = new JMenuBar();
		JMenu file = new JMenu();
		JMenuItem open = new JMenuItem();
		JMenuItem close = new JMenuItem();
		JMenuItem save = new JMenuItem();
		JMenuItem saveas = new JMenuItem();
		JMenuItem importDeck = new JMenuItem();
		JMenuItem export = new JMenuItem();
		JMenuItem quit = new JMenuItem();
		JMenu cardDatabase = new JMenu();
		JMenuItem cards = new JMenuItem();
		JMenuItem sets = new JMenuItem();
		JPanel deckInfo = new JPanel();
		JLabel label1 = new JLabel();
		JTextField deckNameField = new JTextField();
		JLabel deckFormat = new JLabel();
		JComboBox<Format> deckFormatField = new JComboBox<>(Format.class.getEnumConstants());
		// Format format = (Format) deckFormatField.getSelectedItem();
		deckFormatField.setSelectedIndex(-1);
		JLabel deckDescription = new JLabel();
		JScrollPane scrollPane1 = new JScrollPane();
		JTextArea deckDescriptionField = new JTextArea();
		JPanel contents = new JPanel();
		JScrollPane scrollPane2 = new JScrollPane();
		deckTable = new JTable();
		JButton showCards = new JButton();
		JButton removeCards = new JButton();
		JButton addLands = new JButton();
		JButton showStats = new JButton();
		JPanel stats = new JPanel();
		JLabel cardnum = new JLabel();
		cardNumberField = new JLabel();
		JButton showSets = new JButton();
		// ======== this ========
		setTitle("Deck Editor");
		Container contentPane = getContentPane();
		// ======== menuBar1 ========
		{
			// ======== file ========
			{
				file.setText("File");
				// ---- open ----
				open.setText("open");
				open.setToolTipText("open a deck file");
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.dec", "dec");
				chooser.setFileFilter(filter);
				open.addActionListener(e -> {
					int returnVal = chooser.showOpenDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							deck = Deck.loadDeckFromFile(chooser.getSelectedFile());
							AbstractTableModel model = (AbstractTableModel) deckTable.getModel();
							model.fireTableDataChanged();
							deckTable = drawTable(deckTable);
							cardNumberField.setText(String.valueOf(deck.getDeckSize()));
							// TODO find out why there are doups
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(new JFrame(), e1);
							e1.printStackTrace();
						}
					}
				});
				file.add(open);
				// ---- close ----
				close.setText("close");
				file.add(close);
				// ---- save ----
				save.setText("save");
				file.add(save);
				saveas.setText("save as");
				saveas.addActionListener(e -> {
				});
				file.add(saveas);
				file.addSeparator();
				// ---- importDeck ----
				importDeck.setText("import");
				file.add(importDeck);
				// ---- export ----
				export.setText("export");
				file.add(export);
				file.addSeparator();
				// ---- quit ----
				quit.setText("quit");
				quit.addActionListener(e -> System.exit(0));
				file.add(quit);
			}
			menuBar1.add(file);
			// ======== cardDatabase ========
			{
				cardDatabase.setText("Card Database");
				// ---- cards ----
				cards.setText("show all cards");
				cards.addActionListener(e -> {
					ArrayList<Card> c = Jason.dictonary.getDictonary();
					CardTableGUI cardGUI = new CardTableGUI("All Cards", c);
					cardGUI.setVisible(true);
				});
				cardDatabase.add(cards);
				// ---- sets ----
				sets.setText("show all sets");
				sets.addActionListener(e -> {
					SetTableGUI setGUI = new SetTableGUI();
					setGUI.setVisible(true);
				});
				cardDatabase.add(sets);
			}
			menuBar1.add(cardDatabase);
		}
		setJMenuBar(menuBar1);
		// ======== deckInfo ========
		{
			deckInfo.setBorder(new TitledBorder("Deck"));
			label1.setText("Name:");
			// ---- deckFormat ----
			deckFormat.setText("Format:");
			// ---- deckDescription ----
			deckDescription.setText("Description(optional): ");
			// ======== scrollPane1 ========
			{
				scrollPane1.setViewportView(deckDescriptionField);
			}
			GroupLayout deckInfoLayout = new GroupLayout(deckInfo);
			deckInfo.setLayout(deckInfoLayout);
			deckInfoLayout.setHorizontalGroup(deckInfoLayout.createParallelGroup().addGroup(deckInfoLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(deckInfoLayout.createParallelGroup().addComponent(label1).addComponent(deckFormat))
					.addGap(22, 22, 22)
					.addGroup(deckInfoLayout.createParallelGroup()
							.addComponent(deckFormatField, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGroup(deckInfoLayout.createSequentialGroup()
									.addComponent(deckNameField, GroupLayout.PREFERRED_SIZE, 323,
											GroupLayout.PREFERRED_SIZE)
									.addGap(40, 40, 40).addComponent(deckDescription)))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE)));
			deckInfoLayout.setVerticalGroup(deckInfoLayout.createParallelGroup().addGroup(deckInfoLayout
					.createSequentialGroup()
					.addGroup(deckInfoLayout.createParallelGroup()
							.addGroup(deckInfoLayout.createSequentialGroup()
									.addGroup(deckInfoLayout.createParallelGroup()
											.addGroup(deckInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
													.addComponent(deckNameField, GroupLayout.PREFERRED_SIZE,
															GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addComponent(deckDescription))
											.addComponent(label1))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(deckInfoLayout.createParallelGroup()
											.addComponent(deckFormatField, GroupLayout.PREFERRED_SIZE,
													GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(deckFormat)))
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		}
		// ======== contents ========
		{
			// ======== scrollPane2 ========
			{
				// ---- deckTable ----
				scrollPane2.setViewportView(deckTable);
			}
			// ---- showCards ----
			showCards.setText("Add Card(s)");
			showCards.addActionListener(e -> new FilterCards());
			// ---- showSets ----
			removeCards.setText("Remove Card(s)");
			// ---- addLands ----
			addLands.setText("Add Land");
			// ---- showStats ----
			showStats.setText("View Stats");
			GroupLayout contentsLayout = new GroupLayout(contents);
			contents.setLayout(contentsLayout);
			contentsLayout.setHorizontalGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
							.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(contentsLayout.createParallelGroup()
									.addComponent(showCards, GroupLayout.PREFERRED_SIZE, 200,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(showSets, GroupLayout.PREFERRED_SIZE, 200,
											GroupLayout.PREFERRED_SIZE))
							.addComponent(addLands, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
							.addComponent(showStats, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18).addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
					.addContainerGap()));
			contentsLayout.setVerticalGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(contentsLayout.createParallelGroup()
							.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
							.addGroup(contentsLayout.createSequentialGroup().addComponent(showCards)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(showSets)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(addLands)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(showStats)))
					.addContainerGap(211, Short.MAX_VALUE)));
		}
		// ======== contents ========
		{
			// ======== scrollPane2 ========
			{
				// ---- deckTable ----
				deckTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
				scrollPane2.setViewportView(deckTable);
				deckTable = drawTable(deckTable);
			}
			// ---- showCards ----
			showCards.setText("Add Card(s)");
			// ---- showSets ----
			removeCards.setText("Remove Card(s)");
			// ---- addLands ----
			addLands.setText("Add Land");
			// ---- showStats ----
			showStats.setText("View Stats");
			GroupLayout contentsLayout = new GroupLayout(contents);
			contents.setLayout(contentsLayout);
			contentsLayout.setHorizontalGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
							.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(contentsLayout.createParallelGroup()
									.addComponent(showCards, GroupLayout.PREFERRED_SIZE, 200,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(removeCards, GroupLayout.PREFERRED_SIZE, 200,
											GroupLayout.PREFERRED_SIZE))
							.addComponent(addLands, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
							.addComponent(showStats, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18).addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
					.addContainerGap()));
			contentsLayout.setVerticalGroup(contentsLayout.createParallelGroup().addGroup(contentsLayout
					.createSequentialGroup().addContainerGap()
					.addGroup(contentsLayout.createParallelGroup().addComponent(scrollPane2)
							.addGroup(contentsLayout.createSequentialGroup().addComponent(showCards)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(removeCards)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(addLands)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(showStats)
									.addGap(0, 0, Short.MAX_VALUE)))
					.addContainerGap()));
		}
		// ======== stats ========
		{
			stats.setBorder(new TitledBorder("Stats"));
			// ---- cardnum ----
			cardnum.setText("Cards:");
			// ---- cardNumberField ----
			cardNumberField.setText("0");
			GroupLayout statsLayout = new GroupLayout(stats);
			stats.setLayout(statsLayout);
			statsLayout.setHorizontalGroup(statsLayout.createParallelGroup()
					.addGroup(statsLayout.createSequentialGroup().addContainerGap().addComponent(cardnum)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(cardNumberField)
							.addContainerGap(1060, Short.MAX_VALUE)));
			statsLayout.setVerticalGroup(statsLayout.createParallelGroup()
					.addGroup(statsLayout.createSequentialGroup()
							.addGroup(statsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(cardnum).addComponent(cardNumberField))
							.addGap(0, 60, Short.MAX_VALUE)));
		}
		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(contentPaneLayout.createParallelGroup().addGroup(contentPaneLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(contents, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(deckInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(stats, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addGap(0, 45, Short.MAX_VALUE)))
				.addContainerGap()));
		contentPaneLayout.setVerticalGroup(contentPaneLayout.createParallelGroup().addGroup(contentPaneLayout
				.createSequentialGroup()
				.addComponent(deckInfo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(stats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(contents,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		pack();
		setLocationRelativeTo(getOwner());
	}
}
