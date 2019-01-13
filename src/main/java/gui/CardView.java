package gui;

import cards.Card;
import deckeditor.DeckEditor;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardView extends JFrame {
	private Card card;
	private final JPanel contentPanel = new JPanel();
	
	public CardView(Card card) {
		setTitle(card.getName());
		this.card = card;
		setBounds(100, 100, 450, 300);
		drawUI();
	}
	
	private void drawUI() {
		setBounds(100, 100, 559, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		infoPanel.setBounds(253, 0, 290, 306);
		contentPanel.add(infoPanel);
		infoPanel.setLayout(null);
		JPanel names = new JPanel();
		names.setBounds(10, 26, 64, 269);
		infoPanel.add(names);
		names.setLayout(null);
		JLabel CardName = new JLabel("Name:");
		CardName.setBounds(0, 0, 64, 14);
		names.add(CardName);
		JLabel ManaCost = new JLabel("Cost:");
		ManaCost.setBounds(0, 25, 64, 14);
		names.add(ManaCost);
		JLabel Ability = new JLabel("Ability:");
		Ability.setBounds(0, 50, 64, 14);
		names.add(Ability);
		JLabel Type = new JLabel("Type:");
		Type.setBounds(0, 75, 64, 14);
		names.add(Type);
		JLabel Rarity = new JLabel("Rarity:");
		Rarity.setBounds(0, 100, 64, 14);
		names.add(Rarity);
		JLabel FlavorText = new JLabel("Flavor:");
		FlavorText.setBounds(0, 125, 64, 14);
		names.add(FlavorText);
		JLabel Artist = new JLabel("Artist:");
		Artist.setBounds(0, 150, 64, 14);
		names.add(Artist);
		JLabel Set = new JLabel("Set:");
		Set.setBounds(0, 175, 64, 14);
		names.add(Set);
		JPanel fields = new JPanel();
		fields.setBounds(84, 25, 232, 270);
		infoPanel.add(fields);
		fields.setLayout(null);
		JLabel NameField = new JLabel(card.getName());
		NameField.setBounds(0, 0, 211, 14);
		fields.add(NameField);
		JLabel ManaCostField = new JLabel(card.getSymbols() + " (" + card.getConvertedManaCost() + ")");
		ManaCostField.setBounds(0, 25, 211, 14);
		fields.add(ManaCostField);
		JLabel AbilityField = new JLabel(card.getAbility());
		AbilityField.setBounds(0, 50, 211, 14);
		fields.add(AbilityField);
		JLabel TypeField = new JLabel(card.getType());
		TypeField.setBounds(0, 75, 211, 14);
		fields.add(TypeField);
		JLabel RarityField = new JLabel(card.getRarity().toString());
		RarityField.setBounds(0, 100, 211, 14);
		fields.add(RarityField);
		JLabel FlavorTextField = new JLabel(card.getFlavorText());
		FlavorTextField.setBounds(0, 125, 211, 14);
		fields.add(FlavorTextField);
		JLabel ArtistField = new JLabel(card.getArtist());
		ArtistField.setBounds(0, 150, 211, 14);
		fields.add(ArtistField);
		JLabel SetField = new JLabel(card.getSet().getName() + " (" + card.getSet().getCode() + ")");
		SetField.setBounds(0, 175, 211, 15);
		fields.add(SetField);
		
		boolean moveDown = false;
		if (!StringUtils.isBlank(card.getToughness()) && !StringUtils.isBlank(card.getPower())) {
			JLabel PT = new JLabel("P/T:");
			PT.setBounds(0, 200, 64, 14);
			names.add(PT);
			
			JLabel PTField = new JLabel(card.getPower() + " / " + card.getToughness());
			PTField.setBounds(0, 200, 211, 14);
			fields.add(PTField);
			
			moveDown = true;
		}
		if (!StringUtils.isBlank(card.getLoyalty())) {
			JLabel Loyalty = new JLabel("Loyalty:");
			Loyalty.setBounds(0, 200, 64, 15);
			names.add(Loyalty);
			
			JLabel LoyaltyField = new JLabel(card.getLoyalty());
			LoyaltyField.setBounds(0, 200, 211, 15);
			fields.add(LoyaltyField);
			
			moveDown = true;
		}
		if (card.getGeneratedMana() != null){
			int move = 0;
			if (moveDown)
				move += 25;
			JLabel GeneratedMana = new JLabel("Gen. Mana:");
			GeneratedMana.setBounds(0, 200 + move, 64, 15);
			names.add(GeneratedMana);
			
			JLabel GeneratedManaField = new JLabel(card.getGeneratedMana().toString());
			GeneratedManaField.setBounds(0, 200 + move, 211, 15);
			fields.add(GeneratedManaField);
		}
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(new TitledBorder(null, "Card", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		cardPanel.setBounds(0, 0, 243, 350);
		contentPanel.add(cardPanel);
		try {
			String path = "C:\\Program Files (x86)\\Gatherer Extractor\\pics\\" + card.getSet().getCode()+ "\\" + card.getName() + ".full.jpg";
			BufferedImage image = ImageIO.read(new File(path));
			DeckEditor.println(image.getWidth() + " x " + image.getHeight());
			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(cardPanel.getWidth() - 30, cardPanel.getHeight() - 30, Image.SCALE_SMOOTH)));
			cardPanel.add(label);
		} catch (IOException e) {
			DeckEditor.printException(card.getName(), e);
		}
	}
}
