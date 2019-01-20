package gui;

import cards.Card;
import deckeditor.DeckEditor;

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
		setType(Type.POPUP);
		setResizable(false);
		Font fieldFont = new Font("Tahoma", Font.PLAIN, 11);
		Font labelFont = new Font("Tahoma", Font.BOLD, 11);
		setBounds(100, 100, 559, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(new TitledBorder(null, "Card", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		cardPanel.setBounds(0, 0, 210, 305);
		contentPanel.add(cardPanel);
		try {
			String path = "pics\\" + card.getSet().getCode() + "\\" + card.getName() + ".full.jpg";
			BufferedImage image = ImageIO.read(new File(path));
			JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(cardPanel.getWidth() - 30, cardPanel.getHeight() - 30, Image.SCALE_SMOOTH)));
			cardPanel.add(label);
		} catch (IOException e) {
			DeckEditor.printException(card.getName(), e);
		}
		JLabel Ability = new JLabel("Ability:");
		JLabel Artist = new JLabel("Artist:");
		JLabel ArtistField = new JLabel(card.getArtist());
		JLabel CardName = new JLabel("Card Name: ");
		JLabel ManaCost = new JLabel("Mana Cost:");
		JLabel ManaCostField = new JLabel(card.getSymbols());
		JLabel NameField = new JLabel(card.getName());
		JLabel Rarity = new JLabel("Rarity:");
		JLabel RarityField = new JLabel(card.getRarity().toString());
		JLabel Type = new JLabel("Type:");
		JLabel TypeField = new JLabel(card.getType());
		JPanel fields = new JPanel();
		JPanel infoPanel = new JPanel();
		JTextPane AbilityField = new JTextPane();
		Ability.setBounds(0, 80, 64, 14);
		Ability.setFont(labelFont);
		AbilityField.setBounds(0, 95, 306, 120);
		AbilityField.setEditable(false);
		AbilityField.setContentType("text/html");
		AbilityField.setOpaque(false);
		String ability = "<p style = \"font-family:Tahoma;font-size:11pt\">" +
				card.getAbility().replaceAll("; ", "\n").replaceAll("#_", "<i>").replaceAll("_#", "</i>") +
				"</p>";
		AbilityField.setText(ability);
		Artist.setBounds(115, 40, 45, 14);
		Artist.setFont(labelFont);
		Artist.setLabelFor(ArtistField);
		ArtistField.setBounds(160, 40, 146, 14);
		ArtistField.setFont(fieldFont);
		CardName.setBounds(0, 0, 75, 14);
		CardName.setFont(labelFont);
		CardName.setLabelFor(NameField);
		contentPanel.add(infoPanel);
		fields.setBounds(10, 15, 306, 280);
		fields.setLayout(null);
		
		infoPanel.add(fields);
		infoPanel.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		infoPanel.setBounds(217, 0, 326, 306);
		infoPanel.setLayout(null);
		
		
		ManaCost.setBounds(0, 20, 64, 14);
		ManaCost.setFont(labelFont);
		ManaCost.setLabelFor(ManaCostField);
		ManaCostField.setBounds(75, 20, 70, 14);
		ManaCostField.setFont(fieldFont);
		NameField.setBounds(75, 0, 221, 14);
		NameField.setFont(fieldFont);
		
		Rarity.setBounds(0, 40, 45, 14);
		Rarity.setFont(labelFont);
		Rarity.setLabelFor(RarityField);
		RarityField.setBounds(45, 40, 70, 14);
		RarityField.setFont(fieldFont);
		Type.setBounds(0, 60, 40, 14);
		Type.setFont(labelFont);
		Type.setLabelFor(TypeField);
		TypeField.setBounds(40, 60, 266, 14);
		TypeField.setFont(fieldFont);
		fields.add(Ability);
		fields.add(AbilityField);
		fields.add(Artist);
		fields.add(ArtistField);
		fields.add(CardName);

		fields.add(ManaCost);
		fields.add(ManaCostField);
		fields.add(NameField);
		fields.add(Rarity);
		fields.add(RarityField);
		fields.add(Type);
		fields.add(TypeField);
		if (card.getPower() != null && card.getToughness() != null) {
			JLabel PTField = new JLabel(card.getPower() == null && card.getToughness() == null ? null : card.getPower() + "/" + card.getToughness());
			JLabel PT = new JLabel("P/T:");
			PT.setBounds(145, 20, 40, 14);
			PT.setFont(labelFont);
			PT.setLabelFor(PTField);
			PTField.setBounds(185, 20, 40, 14);
			PTField.setFont(fieldFont);
			fields.add(PT);
			fields.add(PTField);
		}
		if (card.getLoyalty() != null) {
			JLabel LoyaltyField = new JLabel(card.getLoyalty().toString());
			JLabel Loyalty = new JLabel("Loyalty:");
			LoyaltyField.setBounds(280, 20, 34, 15);
			LoyaltyField.setFont(fieldFont);
			LoyaltyField.setLabelFor(LoyaltyField);
			Loyalty.setBounds(225, 20, 45, 14);
			Loyalty.setFont(labelFont);
			fields.add(LoyaltyField);
			fields.add(Loyalty);
		}
		if (card.getFlavorText() != null){
			JTextPane FlavorTextField = new JTextPane();
			JLabel FlavorText = new JLabel("Flavor Text");
			FlavorText.setBounds(0, 220, 64, 14);
			FlavorText.setFont(labelFont);
			FlavorTextField.setBackground(UIManager.getColor("Button.background"));
			FlavorTextField.setBounds(0, 235, 306, 45);
			FlavorTextField.setEditable(false);
			FlavorTextField.setFont(fieldFont);
			FlavorTextField.setOpaque(false);
			FlavorTextField.setContentType("text/html");
			String flavor = "<p style = \"font-family:Tahoma;font-size:11pt\">" +
					card.getFlavorText().replaceAll("; ", "\n").replaceAll("#_", "<i>").replaceAll("_#", "</i>") +
					"</p>";
			FlavorTextField.setText(flavor);
			fields.add(FlavorText);
			fields.add(FlavorTextField);
		}
	}
}
