package main.java.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import main.java.cards.CardSet;
import main.java.cards.type.Color;
import main.java.cards.type.Rarity;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;
import net.miginfocom.swing.MigLayout;

public class FilterCards extends JFrame{
	private static final long serialVersionUID = 1204232302120712013L;

	public FilterCards(String name) {
		super(name);
		initComponents();
		setVisible(true);
	}

	private void initComponents() {
		JPanel cardPanel = new JPanel();
		JLabel name = new JLabel();
		JTextField namefield = new JTextField();
		JLabel color = new JLabel();
		Color[] colors = Color.class.getEnumConstants();
		String[] colorNames = new String[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colorNames[i] = colors[i].getName();
		}
		JComboBox<String> colorCombo = new JComboBox<String>(colorNames);
		colorCombo.setSelectedIndex(-1);
		JLabel type = new JLabel();
		JComboBox<SuperType> typeCombo = new JComboBox<SuperType>(SuperType.class.getEnumConstants());
		typeCombo.setSelectedIndex(-1);
		JLabel subtype = new JLabel();
		JComboBox<SubType> subtypeCombo = new JComboBox<SubType>(SubType.class.getEnumConstants());
		subtypeCombo.setSelectedIndex(-1);
		JLabel cmc = new JLabel();
		JNumberTextField cmcField = new JNumberTextField();
		JLabel power = new JLabel();
		JNumberTextField powerField = new JNumberTextField();
		JLabel toughness = new JLabel();
		JNumberTextField toughnessField = new JNumberTextField();
		JLabel set = new JLabel();
		JComboBox<CardSet> setCombo = new JComboBox<CardSet>();
		JCheckBox reserved = new JCheckBox();
		JPanel setsPanel = new JPanel();
		JLabel block = new JLabel();
		JComboBox<String> blockCombo = new JComboBox<String>();
		blockCombo.setSelectedIndex(-1);
		JLabel rarity = new JLabel();
		JComboBox<Rarity> rarityCombo = new JComboBox<Rarity>(Rarity.class.getEnumConstants());
		rarityCombo.setSelectedIndex(-1);
		JPanel formatPanel = new JPanel();
		JCheckBox commander = new JCheckBox();
		JCheckBox penny = new JCheckBox();
		JCheckBox mtgo1v1 = new JCheckBox();
		JCheckBox vintage = new JCheckBox();
		JCheckBox dual = new JCheckBox();
		JCheckBox pauper = new JCheckBox();
		JCheckBox legacy = new JCheckBox();
		JCheckBox brawl = new JCheckBox();
		JCheckBox modern = new JCheckBox();
		JCheckBox future = new JCheckBox();
		JCheckBox frontier = new JCheckBox();
		JCheckBox standard = new JCheckBox();
		JButton resetButton = new JButton();
		JButton formatButton = new JButton();
		setTitle("Filter Cards");
		Container contentPane = getContentPane();
		cardPanel.setBorder(new TitledBorder("Card"));
		name.setText("Name:");
		color.setText("Color:");
		type.setText("Type:");
		subtype.setText("SubType:");
		cmc.setText("CMC:");
		set.setText("Sets:");
		toughness.setText("Toughness:");
		power.setText("Power:");
		reserved.setText("Is Reserved?:");

		resetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clearAll(cardPanel);
				clearAll(setsPanel);
				clearAll(formatPanel);
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
						.addComponent(set, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(setCombo).addComponent(reserved, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
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
												.addGap(0, 0, 0).addComponent(set).addGap(0, 0, 0)
												.addComponent(setCombo, GroupLayout.PREFERRED_SIZE,
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
		rarity.setText("Rarity:");
		GroupLayout setslayout = new GroupLayout(setsPanel);
		setsPanel.setLayout(setslayout);
		setslayout.setHorizontalGroup(setslayout.createParallelGroup()
				.addGroup(setslayout.createSequentialGroup().addContainerGap()
						.addGroup(setslayout.createParallelGroup()
								.addComponent(block, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(blockCombo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(rarity, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
								.addComponent(rarityCombo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		setslayout
				.setVerticalGroup(
						setslayout.createParallelGroup()
								.addGroup(setslayout.createSequentialGroup().addContainerGap().addComponent(block)
										.addGap(0, 0, 0)
										.addComponent(blockCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0).addComponent(rarity).addGap(4, 4, 4)
										.addComponent(rarityCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		formatPanel.setBorder(new TitledBorder("Format"));
		formatPanel.setLayout(
				new MigLayout("insets 0,hidemode 3", "[fill]" + "[fill]", "[fill]" + "[]" + "[]" + "[]" + "[]" + "[]"));
		commander.setText("Commander");
		formatPanel.add(commander, "cell 0 0");
		penny.setText("Penny");
		formatPanel.add(penny, "cell 1 0");
		mtgo1v1.setText("MTGO 1v1");
		formatPanel.add(mtgo1v1, "cell 0 1");
		vintage.setText("Vintage");
		formatPanel.add(vintage, "cell 1 1");
		dual.setText("Duel");
		formatPanel.add(dual, "cell 0 2");
		pauper.setText("Pauper");
		formatPanel.add(pauper, "cell 1 2");
		legacy.setText("Legacy");
		formatPanel.add(legacy, "cell 0 3");
		brawl.setText("Brawl");
		formatPanel.add(brawl, "cell 1 3");
		modern.setText("Modern");
		formatPanel.add(modern, "cell 0 4");
		future.setText("Future");
		formatPanel.add(future, "cell 1 4");
		frontier.setText("Frotntier");
		formatPanel.add(frontier, "cell 0 5");
		standard.setText("Standard");
		formatPanel.add(standard, "cell 1 5");
		resetButton.setText("Reset");
		formatButton.setText("Filter");
		GroupLayout cardLayout = new GroupLayout(contentPane);
		contentPane.setLayout(cardLayout);
		cardLayout.setHorizontalGroup(cardLayout.createParallelGroup()
				.addGroup(cardLayout.createSequentialGroup().addContainerGap().addGroup(cardLayout.createParallelGroup()
						.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(cardLayout.createSequentialGroup()
								.addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(formatButton,
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
												.addComponent(resetButton).addComponent(formatButton)))
								.addGroup(cardLayout.createSequentialGroup()
										.addComponent(setsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(formatPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(11, 11, Short.MAX_VALUE)));
		setSize(620, 620);
		setLocationRelativeTo(null);
	}

	private void clearAll(Container aContainer) {
		for (Component c : aContainer.getComponents()) {
			if (c instanceof JTextField || c instanceof JTextArea) {
				((JTextComponent) c).setText("");
			} else if (c instanceof JRadioButton) {
				((JRadioButton) c).setSelected(false);
			} else if (c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(false);
			} else if (c instanceof JComboBox<?>) {
				((JComboBox<?>) c).setSelectedIndex(-1);
			} else if (c instanceof Container) {
				clearAll((Container) c);
			}
		}
	}
}
