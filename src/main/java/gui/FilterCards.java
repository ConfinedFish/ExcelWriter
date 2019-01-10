package gui;


import XML.XMLParse;
import cards.Card;
import cards.CardDictonary;
import cards.CardSet;
import cards.type.Color;
import cards.type.Format;
import cards.type.Rarity;
import cards.type.SuperType;
import deckeditor.DeckEditor;
import deckeditor.Level;
import gui.table.CardTableGUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FilterCards extends JFrame {
	private static final long serialVersionUID = 1204232302120712013L;
	private XMLParse xmlParse;
	private final FilterCards instance;
	private ArrayList<CardSet> sets;
	private ArrayList<Card> cardsToSort;
	private CardDictonary dict;
	private HashMap<String, Component> componentMap;


	private ArrayList<Card> nameFilter;
	private ArrayList<Card> filterFormat;
	private final Color[] COLORS = Color.class.getEnumConstants();
	private String[] colorNames = new String[COLORS.length];


	private JButton btnReset;
	private JButton btnSearch;
	private JCheckBox chckbxAbility;
	private JCheckBox chckbxB;
	private JCheckBox chckbxExcludeColors;
	private JCheckBox chckbxFlavorText;
	private JCheckBox chckbxG;
	private JCheckBox chckbxMatchExactColors;
	private JCheckBox chckbxMultiColorsOnly;
	private JCheckBox chckbxName;
	private JCheckBox chckbxR;
	private JCheckBox chckbxType;
	private JCheckBox chckbxU;
	private JCheckBox chckbxW;
	private JComboBox<Format> formatCombo;
	private JComboBox<String> cardSetCombo;
	private JComboBox<SuperType> typeCombo;
	private JLabel lblCardSet;
	private JLabel lblFormat;
	private JLabel lblSearchIn;
	private JLabel lblType;
	private JPanel advancedPanel;
	private JPanel color;
	private JPanel contentPane;
	private JPanel filter;
	private JPanel searchPanel;
	private JTabbedPane tabbedPane;
	private JTextField search;


	public FilterCards(XMLParse xmlParse) {
		super("Filter Cards");
		instance = this;
		this.xmlParse = xmlParse;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		preInit();
		drawUI();
		clearAll(instance);
	}


	private void preInit() {
		cardsToSort = new ArrayList<>();
		sets = xmlParse.getSetArrayList();
		dict = xmlParse.getDictonary();
		filterFormat = new ArrayList<>();
		nameFilter = new ArrayList<>();
		componentMap = new HashMap<>();
		initComponents();
		getNonEmptyComponents().forEach(c-> DeckEditor.println(c, Level.DEBUG));
	}


	private void initComponents() {
		JComboBox<Rarity> rarityCombo = new JComboBox<>(Rarity.class.getEnumConstants());
		String[] names = new String[sets.size()];
		for (int i = 0; i < sets.size(); i++) {
			names[i] = sets.get(i).getName() + " (" + sets.get(i).getCode() + ")";
		}
		advancedPanel = new JPanel();
		btnReset = new JButton("Reset");
		btnSearch = new JButton("Search");

		cardSetCombo = new JComboBox<>(names);
		componentMap.put("Set", cardSetCombo);

		chckbxAbility = new JCheckBox("Ability");
		componentMap.put("Ability", chckbxAbility);

		chckbxB = new JCheckBox("B");
		componentMap.put("B", chckbxB);

		chckbxExcludeColors = new JCheckBox("Exclude Selected Colors");
		componentMap.put("Exclude Colors", chckbxExcludeColors);

		chckbxFlavorText = new JCheckBox("Flavor Text");
		componentMap.put("Flavor Text", chckbxFlavorText);

		chckbxG = new JCheckBox("G");
		componentMap.put("G", chckbxG);

		chckbxMatchExactColors = new JCheckBox("Match Exact Colors");
		componentMap.put("Exact Colors", chckbxMatchExactColors);

		chckbxMultiColorsOnly = new JCheckBox("Match Multicolored cardsToSort only");
		componentMap.put("Multi Colors", chckbxMultiColorsOnly);

		chckbxName = new JCheckBox("Name");
		componentMap.put("Name", chckbxName);

		chckbxR = new JCheckBox("R");
		componentMap.put("R", chckbxR);

		chckbxType = new JCheckBox("Type");
		componentMap.put("Type", chckbxType);

		chckbxU = new JCheckBox("U");
		componentMap.put("U", chckbxU);

		chckbxW = new JCheckBox("W");
		componentMap.put("U", chckbxW);

		color = new JPanel();
		contentPane = new JPanel();
		filter = new JPanel();
		formatCombo = new JComboBox<>(Format.values());
		componentMap.put("Format", formatCombo);

		lblCardSet = new JLabel("Card Set:");
		lblFormat = new JLabel("Format:");
		lblSearchIn = new JLabel("Search In:");
		lblType = new JLabel("Type:");
		search = new JTextField();
		componentMap.put("Search Field", search);

		searchPanel = new JPanel();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		typeCombo = new JComboBox<>(SuperType.values());
		componentMap.put("Type Combo", typeCombo);
	}


	private void drawUI() {
		setBounds(100, 100, 450, 304);
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane);

		searchPanel.setBorder(null);
		tabbedPane.addTab("Simple", null, searchPanel, null);
		searchPanel.setLayout(null);

		search.setBounds(12, 8, 308, 20);
		searchPanel.add(search);
		search.setColumns(10);

		btnSearch.addActionListener(e -> search());
		btnSearch.setBounds(332, 5, 75, 26);
		searchPanel.add(btnSearch);

		btnReset.setBounds(332, 42, 75, 26);
		btnReset.addActionListener(e -> clearAll(instance));
		searchPanel.add(btnReset);

		chckbxName.setBounds(12, 46, 62, 24);
		searchPanel.add(chckbxName);

		lblSearchIn.setBounds(12, 28, 62, 20);
		searchPanel.add(lblSearchIn);

		chckbxType.setBounds(72, 46, 62, 24);
		searchPanel.add(chckbxType);

		chckbxAbility.setBounds(132, 46, 62, 24);
		searchPanel.add(chckbxAbility);

		chckbxFlavorText.setBounds(192, 46, 87, 24);
		searchPanel.add(chckbxFlavorText);

		color.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		color.setBounds(12, 78, 215, 139);
		searchPanel.add(color);
		color.setLayout(null);

		chckbxW.setBounds(8, 24, 37, 24);
		color.add(chckbxW);

		chckbxU.setBounds(50, 24, 37, 24);
		color.add(chckbxU);

		chckbxB.setBounds(88, 24, 37, 24);
		color.add(chckbxB);

		chckbxR.setBounds(126, 24, 37, 24);
		color.add(chckbxR);

		chckbxG.setBounds(164, 24, 37, 24);
		color.add(chckbxG);

		chckbxMatchExactColors.setBounds(8, 52, 150, 24);
		color.add(chckbxMatchExactColors);
		chckbxExcludeColors.setBounds(8, 80, 180, 24);
		color.add(chckbxExcludeColors);

		chckbxMultiColorsOnly.setBounds(8, 108, 200, 24);
		color.add(chckbxMultiColorsOnly);

		filter.setBorder(new TitledBorder(null, "Filter By", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		filter.setBounds(237, 78, 170, 138);
		searchPanel.add(filter);
		filter.setLayout(null);

		formatCombo.setBounds(12, 26, 148, 20);
		filter.add(formatCombo);

		cardSetCombo.setBounds(12, 63, 148, 20);
		filter.add(cardSetCombo);

		typeCombo.setBounds(12, 107, 148, 20);
		filter.add(typeCombo);

		lblFormat.setBounds(12, 12, 46, 14);
		filter.add(lblFormat);

		lblCardSet.setBounds(12, 50, 56, 14);
		filter.add(lblCardSet);

		lblType.setBounds(12, 90, 46, 14);
		filter.add(lblType);


		chckbxExcludeColors.addActionListener(arg0 -> {
			if (chckbxMatchExactColors.isSelected()) {
				chckbxMatchExactColors.setSelected(false);
			}
		});
		chckbxMatchExactColors.addActionListener(e -> {
			if (chckbxExcludeColors.isSelected()) {
				chckbxExcludeColors.setSelected(false);
			}
		});

		tabbedPane.addTab("Advanced", null, advancedPanel, null);
	}

	private void search() {
		if (isEmpty(instance, true)) {
			CardTableGUI gui = new CardTableGUI("Results : " + dict.getDictonary().size(), dict.getDictonary(), xmlParse);
			gui.setVisible(true);
			gui.setLocationRelativeTo(this);
			dispose();
		} else {

		}
	}

	private ArrayList<String> getNonEmptyComponents() {
		ArrayList<String> nonEmpty = new ArrayList<>();
		for (Map.Entry<String, Component> entry : componentMap.entrySet()) {
			Component c = (Component) ((Map.Entry) entry).getValue();
			if (c instanceof JTextField) {
				if (!((JTextField) c).getText().trim().equals("")) {
					nonEmpty.add(entry.getKey());
				}
			} else if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					nonEmpty.add(entry.getKey());
				}
			} else if (c instanceof JComboBox<?>) {
				if (((JComboBox<?>) c).getSelectedIndex() != -1) {
					nonEmpty.add(entry.getKey());
				}
			}
		}
		return nonEmpty;
	}

	private void clearAll(Container cont) {
		for (Component c : cont.getComponents()) {
			if (c instanceof JTextField || c instanceof JTextArea) {
				((JTextComponent) c).setText("");
			} else if (c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(false);
			} else if (c instanceof JComboBox<?>) {
				((JComboBox<?>) c).setSelectedIndex(-1);
			} else if (c instanceof Container) {
				clearAll((Container) c);
			}
		}
	}


	private boolean isEmpty(Container cont, boolean bool) {
		if (!bool)
			return false;
		boolean value = true;
		for (Component c : cont.getComponents()) {
			if (c instanceof JTextField) {
				if (!((JTextField) c).getText().trim().equals("")) {
					value = false;
				}
			} else if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					value = false;
				}
			} else if (c instanceof JComboBox<?>) {
				if (((JComboBox<?>) c).getSelectedIndex() != -1) {
					value = false;
				}
			} else if (c instanceof Container) {
				value = isEmpty((Container) c, value);
			}
		}
		return value;
	}
}
