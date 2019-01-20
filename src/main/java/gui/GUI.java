package gui;


import XML.XMLParse;
import cards.Card;
import cards.Deck;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.table.CardTableGUI;
import gui.table.SetTableGUI;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GUI extends JFrame {
	private static final long serialVersionUID = -1066050764201645094L;
	private JLabel cardNumberField;
	private Deck deck;
	private JTable deckTable;
	private XMLParse xmlParse;
	private ArrayList<Card> c;
	
	public GUI(XMLParse xmlParse) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			DeckEditor.printException("GUI", e);
		}
		this.xmlParse = xmlParse;
		c = xmlParse.getCardArrayList();
		DeckEditor.println("Loading GUI...", Level.INFO);
		drawUI();
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
	
	private void drawUI() {
		JPanel contentPane = new JPanel();
		setForeground(SystemColor.controlShadow);
		Font fieldFont = new Font("Dialog", Font.PLAIN, 12);
		Color borderColor = new Color(153, 180, 209);
		Font buttonFont = new Font("Lucida Sans", Font.BOLD, 12);
		setTitle("DeckEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 429);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu File = new JMenu("File");
		menuBar.add(File);
		
		
		JMenuItem open = new JMenuItem("Open");
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.dec", "dec");
		chooser.setFileFilter(filter);
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						//deck = Deck.loadDeckFromFile(chooser.getSelectedFile());
						AbstractTableModel model = (AbstractTableModel) deckTable.getModel();
						model.fireTableDataChanged();
						//deckTable = drawTable(deckTable);
						//cardNumberField.setText(String.valueOf(deck.getDeckSize()));
						// TODO find out why there are doups
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(new JFrame(), e1);
						e1.printStackTrace();
					}
				}
			}
		});
		File.add(open);
		
		JMenuItem close = new JMenuItem("Close");
		File.add(close);
		
		JMenuItem save = new JMenuItem("Save");
		File.add(save);
		
		JMenuItem saveas = new JMenuItem("Save As...");
		File.add(saveas);
		
		JSeparator separator_1 = new JSeparator();
		File.add(separator_1);
		
		JMenuItem imprt = new JMenuItem("Import");
		File.add(imprt);
		
		JMenuItem export = new JMenuItem("Export");
		File.add(export);
		
		JSeparator separator_2 = new JSeparator();
		File.add(separator_2);
		
		JMenuItem quit = new JMenuItem("Quit");
		File.add(quit);
		
		JMenu Database = new JMenu("Database");
		menuBar.add(Database);
		
		JMenuItem showAllCards = new JMenuItem("show all cards");
		showAllCards.addActionListener(e -> {
			CardTableGUI cardGUI = new CardTableGUI("All Cards : " + c.size(), c, xmlParse);
			cardGUI.setVisible(true);
			cardGUI.setLocationRelativeTo(this);
		});
		Database.add(showAllCards);
		
		JMenuItem showAllSets = new JMenuItem("show all sets");
		showAllSets.addActionListener(e -> {
			SetTableGUI setGUI = new SetTableGUI("All Sets", xmlParse);
			setGUI.setVisible(true);
			setGUI.setLocationRelativeTo(this);
		});
		Database.add(showAllSets);
		
		JSeparator separator = new JSeparator();
		Database.add(separator);
		contentPane.setBackground(new Color(74, 74, 74));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(SystemColor.controlDkShadow);
		splitPane.setBounds(5, 95, 757, 258);
		
		splitPane.setUI(new BasicSplitPaneUI() {
			public BasicSplitPaneDivider createDefaultDivider() {
				return new BasicSplitPaneDivider(this) {
					public void setBorder(Border b) {
					}
					
					@Override
					public void paint(Graphics g) {
						g.setColor(borderColor);
						g.fillRect(0, 0, getSize().width, getSize().height);
						super.paint(g);
					}
				};
			}
		});
		splitPane.setBorder(null);
		
		contentPane.add(splitPane);
		splitPane.setDividerLocation(.5);
		
		deckTable = new JTable();
		deckTable.setModel(new DefaultTableModel(
				new Object[][]{
						{"Plains", "4"},
						{"Island", "5"},
						{"Forest", "2"},
						{"Swamp", "2"},
						{"Wastes", null},
				},
				new String[]{
						"Name", "Number"
				}
		) {
			boolean[] columnEditables = new boolean[]{
					true, false
			};
			
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		deckTable.getColumnModel().getColumn(1).setResizable(false);
		deckTable.setForeground(Color.WHITE);
		deckTable.setBackground(SystemColor.controlDkShadow);
		deckTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		splitPane.setLeftComponent(deckTable);
		
		deckTable.setSurrendersFocusOnKeystroke(true);
		
		JTable table = new JTable();
		table.setBackground(SystemColor.controlDkShadow);
		table.setBounds(0, 0, 0, 0);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowGrid(false);
		splitPane.setRightComponent(table);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(90, 90, 90));
		infoPanel.setBorder(new TitledBorder(new LineBorder(new Color(153, 180, 209), 2, true), "Add From", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.WHITE));
		infoPanel.setBounds(5, 5, 262, 78);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(buttonFont);
		searchButton.setForeground(Color.WHITE);
		searchButton.setBackground(SystemColor.textInactiveText);
		searchButton.setBounds(170, 25, 80, 39);
		infoPanel.add(searchButton);
		
		JButton colorButton = new JButton("Color");
		colorButton.setBounds(12, 25, 80, 20);
		colorButton.setFont(buttonFont);
		colorButton.setForeground(Color.WHITE);
		colorButton.setBackground(SystemColor.textInactiveText);
		infoPanel.add(colorButton);
		
		JButton typeButton = new JButton("Type");
		typeButton.setBounds(91, 25, 80, 20);
		typeButton.setFont(buttonFont);
		typeButton.setForeground(Color.WHITE);
		typeButton.setBackground(SystemColor.textInactiveText);
		infoPanel.add(typeButton);
		
		JButton setButton = new JButton("Set");
		setButton.setBounds(12, 44, 80, 20);
		setButton.setFont(buttonFont);
		setButton.setForeground(Color.WHITE);
		setButton.setBackground(SystemColor.textInactiveText);
		infoPanel.add(setButton);
		
		JButton formatButton = new JButton("Format");
		formatButton.setBounds(91, 44, 80, 20);
		formatButton.setFont(buttonFont);
		formatButton.setForeground(Color.WHITE);
		formatButton.setBackground(SystemColor.textInactiveText);
		infoPanel.add(formatButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(90, 90, 90));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(153, 180, 209), 2, true), "Statistics", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(255, 255, 255)));
		panel.setBounds(279, 5, 483, 78);
		contentPane.add(panel);
		
		JLabel creatutres = new JLabel("Creatures:");
		creatutres.setForeground(Color.WHITE);
		creatutres.setBounds(117, 25, 75, 16);
		panel.add(creatutres);
		
		JLabel creaturesField = new JLabel("0");
		creatutres.setLabelFor(creaturesField);
		creaturesField.setForeground(Color.WHITE);
		creaturesField.setFont(new Font("Dialog", Font.PLAIN, 12));
		creaturesField.setHorizontalAlignment(SwingConstants.LEFT);
		creaturesField.setBounds(192, 25, 30, 16);
		panel.add(creaturesField);
		
		JLabel main = new JLabel("Mainboard:");
		main.setForeground(Color.WHITE);
		main.setBounds(12, 25, 75, 16);
		panel.add(main);
		
		JLabel mainField = new JLabel("0");
		main.setLabelFor(mainField);
		mainField.setForeground(Color.WHITE);
		mainField.setFont(new Font("Dialog", Font.PLAIN, 12));
		mainField.setHorizontalAlignment(SwingConstants.LEFT);
		mainField.setBounds(87, 25, 30, 16);
		panel.add(mainField);
		
		JLabel sideboard = new JLabel("Sideboard:");
		sideboard.setForeground(Color.WHITE);
		sideboard.setBounds(12, 45, 75, 16);
		panel.add(sideboard);
		
		JLabel sideField = new JLabel("0");
		sideboard.setLabelFor(sideField);
		sideField.setForeground(Color.WHITE);
		sideField.setFont(new Font("Dialog", Font.PLAIN, 12));
		sideField.setHorizontalAlignment(SwingConstants.LEFT);
		sideField.setBounds(87, 45, 30, 16);
		panel.add(sideField);
		
		JLabel lands = new JLabel("Lands:");
		lands.setForeground(Color.WHITE);
		lands.setBounds(117, 45, 50, 16);
		panel.add(lands);
		
		JLabel landsField = new JLabel("0");
		lands.setLabelFor(landsField);
		landsField.setForeground(Color.WHITE);
		landsField.setFont(fieldFont);
		landsField.setHorizontalAlignment(SwingConstants.LEFT);
		landsField.setBounds(192, 45, 30, 16);
		panel.add(landsField);
		
		JButton tokens = new JButton("View Tokens");
		tokens.setBounds(371, 44, 100, 20);
		tokens.setFont(buttonFont);
		tokens.setForeground(Color.WHITE);
		tokens.setBackground(SystemColor.textInactiveText);
		tokens.setMargin(new Insets(0, 0, 0, 0));
		panel.add(tokens);
		
		JButton preview = new JButton("Image Preview");
		preview.setMargin(new Insets(0, 0, 0, 0));
		preview.setFont(buttonFont);
		preview.setForeground(Color.WHITE);
		preview.setBackground(SystemColor.textInactiveText);
		preview.setBounds(371, 25, 100, 20);
		panel.add(preview);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.activeCaption);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(112, 23, 5, 43);
		panel.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.activeCaption);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(217, 23, 5, 43);
		panel.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.activeCaption);
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(350, 23, 5, 43);
		panel.add(separator_5);
		searchButton.addActionListener(e -> {
			FilterCards filterCards = new FilterCards(xmlParse);
			filterCards.setVisible(true);
			filterCards.setLocationRelativeTo(this);
		});
	}
}
