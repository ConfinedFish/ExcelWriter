package gui;

import cards.Card;
import cards.CardDictonary;
import cards.CardSet;
import cards.type.Color;
import cards.type.*;
import deckeditor.DeckEditor;
import gui.table.CardTableGUI;
import json.Jason;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;

public class FilterCards extends JDialog{
	private static final long serialVersionUID = 1204232302120712013L;
	private final FilterCards instance;
	private final ArrayList<Card> nameFilter;
	private final ArrayList<Card> filterFormat;
	private final ArrayList<Card> cards;
	private final Color[] colors = Color.class.getEnumConstants();
	private final ArrayList<CardSet> sets = Jason.sets;
	private final ArrayList<String> blocks = new ArrayList<>();
	private final ArrayList<String> setNames = new ArrayList<>();
	private final String[] colorNames = new String[colors.length];
	private final CardDictonary dict = Jason.dictonary.getInstance();
	
	public FilterCards(){
		initComponents();
		setVisible(true);
		cards = new ArrayList<>();
		filterFormat = new ArrayList<>();
		nameFilter = new ArrayList<>();
		instance = this;
		clearAll(instance);
	}
	
	private void preInit(){
//		for (int i = 0; i < colors.length; i++){
//			colorNames[i] = colors[i].getName();
//		}
//		for (CardSet s : sets){
//			if (!blocks.contains(s.getBlock()))
//				blocks.add(s.getBlock());
//			setNames.add(s.getName());
//		}
//		blocks.removeAll(Collections.singleton(null));
	}
	
	private void initComponents(){
		preInit();
		JButton filterButton = new JButton();
		JButton resetButton = new JButton();
		JCheckBox brawl = new JCheckBox();
		JCheckBox commander = new JCheckBox();
		JCheckBox dual = new JCheckBox();
		JCheckBox frontier = new JCheckBox();
		JCheckBox future = new JCheckBox();
		JCheckBox legacy = new JCheckBox();
		JCheckBox modern = new JCheckBox();
		JCheckBox mtgo1v1 = new JCheckBox();
		JCheckBox pauper = new JCheckBox();
		JCheckBox penny = new JCheckBox();
		JCheckBox reserved = new JCheckBox();
		JCheckBox standard = new JCheckBox();
		JCheckBox vintage = new JCheckBox();
		JComboBox<Rarity> rarityCombo = new JComboBox<>(Rarity.class.getEnumConstants());
		JComboBox<String> blockCombo = new JComboBox<>(blocks.toArray(new String[0]));
		JComboBox<String> setsCombo = new JComboBox<>(setNames.toArray(new String[0]));
		JComboBox<SubType> subtypeCombo = new JComboBox<>(SubType.class.getEnumConstants());
		JComboBox<String> colorCombo = new JComboBox<>(colorNames);
		JComboBox<SuperType> typeCombo = new JComboBox<>(SuperType.class.getEnumConstants());
		JLabel block = new JLabel();
		JLabel cmc = new JLabel();
		JLabel power = new JLabel();
		JLabel rarity = new JLabel();
		JLabel set = new JLabel();
		JLabel subtype = new JLabel();
		JLabel toughness = new JLabel();
		JLabel color = new JLabel();
		JLabel name = new JLabel();
		JLabel type = new JLabel();
		JNumberTextField cmcField = new JNumberTextField();
		JNumberTextField powerField = new JNumberTextField();
		JNumberTextField toughnessField = new JNumberTextField();
		JPanel formatPanel = new JPanel();
		JPanel setsPanel = new JPanel();
		JPanel cardPanel = new JPanel();
		JTextField namefield = new JTextField();
		setTitle("Filter Cards");
		Container contentPane = getContentPane();
		cardPanel.setBorder(new TitledBorder("Card"));
		name.setText("Name:");
		namefield.setName("Name");
		color.setText("Color:");
		colorCombo.setName("color");
		type.setText("Type:");
		typeCombo.setName("type");
		subtype.setText("SubType:");
		subtypeCombo.setName("subtype");
		cmc.setText("CMC:");
		cmcField.setName("cmc");
		rarity.setText("Rarity:");
		rarityCombo.setName("rarity");
		toughness.setText("Toughness:");
		toughnessField.setName("toughness");
		power.setText("Power:");
		powerField.setName("power");
		reserved.setText("Is Reserved?:");
		reserved.setName("isReserved");
		resetButton.addActionListener(e -> clearAll(instance));
		filterButton.addActionListener(e -> {
			cards.clear();
			filterFormat.clear();
			nameFilter.clear();
			createFilter(instance);
			
			cards.addAll(dict.findAll(nameFilter, filterFormat));
			DeckEditor.println("NameFilter");
			
			nameFilter.forEach(c -> DeckEditor.print(c.getName() + ", "));
			DeckEditor.println("FilterFormat");
			filterFormat.forEach(c -> DeckEditor.print(c.getName() + ", "));
			if (cards.size() != 0){
				CardTableGUI gui = new CardTableGUI("Filter Cards : " + dict.size() + " results",
						dict.getDictonary());
				gui.setVisible(true);
				cards.clear();
				filterFormat.clear();
				nameFilter.clear();
				dispose();
			} else{
				if (isEmpty(instance)){
					CardTableGUI gui = new CardTableGUI("Filter Cards : " + dict.size() + " results",
							dict.getDictonary());
					gui.setVisible(true);
					DeckEditor.println("IsEmpty is true");
					cards.clear();
					filterFormat.clear();
					nameFilter.clear();
					dispose();
				} else
					JOptionPane.showMessageDialog(new JFrame(), "Your search has 0 results");
			}
		});
		GroupLayout cardPanelLayout = new GroupLayout(cardPanel);
		cardPanel.setLayout(cardPanelLayout);
		cardPanelLayout.setHorizontalGroup(cardPanelLayout.createParallelGroup().addGroup(cardPanelLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(cardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(namefield)
						.addComponent(color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(colorCombo)
						.addComponent(type, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(typeCombo)
						.addComponent(subtype, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(subtypeCombo)
						.addComponent(cmc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cmcField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(rarity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rarityCombo)
						.addComponent(reserved, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(power, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(powerField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(toughness, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(toughnessField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		cardPanelLayout
				.setVerticalGroup(
						cardPanelLayout.createParallelGroup()
								.addGroup(
										cardPanelLayout.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(name).addGap(0, 0, 0)
												.addComponent(namefield, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(color).addGap(0, 0, 0)
												.addComponent(colorCombo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(type).addGap(0, 0, 0)
												.addComponent(typeCombo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(subtype).addGap(0, 0, 0)
												.addComponent(subtypeCombo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(cmc).addGap(0, 0, 0)
												.addComponent(cmcField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(rarity).addGap(0, 0, 0)
												.addComponent(rarityCombo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(power)
												.addComponent(powerField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, 0).addComponent(toughness).addGap(0, 0, 0)
												.addComponent(toughnessField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(reserved)));
		setsPanel.setBorder(new TitledBorder("Set"));
		block.setText("Block:");
		blockCombo.setName("block");
		set.setText("Set:");
		setsCombo.setName("set");
		GroupLayout setslayout = new GroupLayout(setsPanel);
		setsPanel.setLayout(setslayout);
		setslayout.setHorizontalGroup(setslayout.createParallelGroup()
				.addGroup(setslayout.createSequentialGroup().addContainerGap()
						.addGroup(setslayout.createParallelGroup()
								.addComponent(block, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(blockCombo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(set, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(setsCombo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		setslayout
				.setVerticalGroup(
						setslayout.createParallelGroup()
								.addGroup(setslayout.createSequentialGroup().addContainerGap().addComponent(block)
										.addGap(0, 0, 0)
										.addComponent(blockCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0).addComponent(set).addGap(4, 4, 4)
										.addComponent(setsCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		formatPanel.setBorder(new TitledBorder("Format"));
		formatPanel.setLayout(
				new MigLayout("insets 0,hidemode 3", "[fill]" + "[fill]", "[fill]" + "[]" + "[]" + "[]" + "[]" +
						"[]"));
		commander.setText("Commander");
		commander.setName("commander");
		formatPanel.add(commander, "cell 0 0");
		penny.setText("Penny");
		penny.setName("penny");
		formatPanel.add(penny, "cell 1 0");
		mtgo1v1.setText("MTGO 1v1");
		mtgo1v1.setName("mtgo1v1");
		formatPanel.add(mtgo1v1, "cell 0 1");
		vintage.setText("Vintage");
		vintage.setName("vintage");
		formatPanel.add(vintage, "cell 1 1");
		dual.setText("Duel");
		dual.setName("duel");
		formatPanel.add(dual, "cell 0 2");
		pauper.setText("Pauper");
		pauper.setName("pauper");
		formatPanel.add(pauper, "cell 1 2");
		legacy.setText("Legacy");
		legacy.setName("legacy");
		formatPanel.add(legacy, "cell 0 3");
		brawl.setText("Brawl");
		brawl.setName("brawl");
		formatPanel.add(brawl, "cell 1 3");
		modern.setText("Modern");
		modern.setName("modern");
		formatPanel.add(modern, "cell 0 4");
		future.setText("Future");
		future.setName("future");
		formatPanel.add(future, "cell 1 4");
		frontier.setText("Frotntier");
		frontier.setName("frontier");
		formatPanel.add(frontier, "cell 0 5");
		standard.setText("Standard");
		standard.setName("standard");
		formatPanel.add(standard, "cell 1 5");
		resetButton.setText("Reset");
		filterButton.setText("Filter");
		GroupLayout cardLayout = new GroupLayout(contentPane);
		contentPane.setLayout(cardLayout);
		cardLayout.setHorizontalGroup(cardLayout.createParallelGroup()
				.addGroup(cardLayout.createSequentialGroup().addContainerGap().addGroup(cardLayout.createParallelGroup()
						.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(cardLayout.createSequentialGroup()
								.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(filterButton,
										GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
						.addGap(53, 53, 53)
						.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(formatPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(setsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		cardLayout.setVerticalGroup(cardLayout.createParallelGroup()
				.addGroup(cardLayout.createSequentialGroup().addContainerGap()
						.addGroup(cardLayout.createParallelGroup()
								.addGroup(cardLayout.createSequentialGroup()
										.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(cardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(resetButton).addComponent(filterButton)))
								.addGroup(cardLayout.createSequentialGroup()
										.addComponent(setsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(formatPanel, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(11, 11, Short.MAX_VALUE)));
		setSize(620, 620);
		setLocationRelativeTo(null);
	}
	
	private void clearAll(Container cont){
		for (Component c : cont.getComponents()){
			if (c instanceof JTextField || c instanceof JTextArea){
				((JTextComponent) c).setText("");
			} else if (c instanceof JCheckBox){
				((JCheckBox) c).setSelected(false);
			} else if (c instanceof JComboBox<?>){
				((JComboBox<?>) c).setSelectedIndex(-1);
			} else if (c instanceof Container){
				clearAll((Container) c);
			}
		}
	}
	
	private boolean isEmpty(Container cont){
		for (Component c : cont.getComponents()){
			if (c instanceof JTextField){
				if (!((JTextField) c).getText().trim().equals("")){
					DeckEditor.println(c.getName());
					return false;
				}
			} else if (c instanceof JCheckBox){
				if (((JCheckBox) c).isSelected()){
					DeckEditor.println(c.getName());
					return false;
				}
			} else if (c instanceof JComboBox<?>){
//				if (((JComboBox<?>) c).getSelectedIndex() != -1){
//					DeckEditor.println(c.getName());
//					return false;
//				}
				
			} else if (c instanceof Container){
				return isEmpty((Container) c);
			}
		}
		return true;
	}
	
	private void createFilter(Container cont){
		for (Component c : cont.getComponents()){
			if (c instanceof JTextField){
				if (!((JTextField) c).getText().trim().equals("")){
					nameFilter.addAll(dict.findCards(((JTextField) c).getText()));
					DeckEditor.println(c.getName() + " : " + ((JTextField) c).getText() + " : " + nameFilter.size());
					
				}
			} else if (c instanceof JCheckBox){
				if (((JCheckBox) c).isSelected()){
					if (!c.getName().equals("isReserved"))
						for (Format format : Jason.formats.keySet())
							if (format.name().equalsIgnoreCase(c.getName()))
								filterFormat.addAll(Jason.formats.get(format));
					DeckEditor.println(c.getName() + " : " + filterFormat.size());
				}
			} else if (c instanceof JComboBox<?>){
//				if (((JComboBox<?>) c).getSelectedIndex() != -1)
//					dict.findCards();
			} else if (c instanceof Container){
				createFilter((Container) c);
			}
		}
	}
}
